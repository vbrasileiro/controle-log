package com.service.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Campanha;
import com.queue.CampanhaQueue;
import com.repository.CampanhaRepository;
import com.service.CampanhaService;

@Service
public class CampanhaServiceImpl<R> implements CampanhaService{

	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@Autowired
	private CampanhaQueue campanhaQueue;
	
	@Override
	public List<Campanha> buscarCampanhas() {		
		return campanhaRepository.findAll();
	}
	
	@Override
	public Campanha buscarCampanhaPorId(Long idCampanha) {		
		return campanhaRepository.findById(idCampanha).get();
	}

	@Override
	public void salvar(Campanha campanha) {
		campanhaQueue.enviar(campanha);
	}

	@Override
	public void deletar(Long idCampanha) {
		campanhaRepository.deleteById(idCampanha);		
	}
	
	
	public void processaFilaSalvar(Campanha campanha) {
		//verifica se existe alguma campanha com data igual
				List<Campanha> listCampanha = campanhaRepository.findBydtTerminoVigenciaAndIdCampanhaNot(campanha.getDtTerminoVigencia(),campanha.getIdCampanha());		
				
				if(listCampanha.isEmpty()) {
					campanhaRepository.save(campanha);	
				}else {
					//busca todas as campanhas
					listCampanha = campanhaRepository.findAll();
					
					Calendar c = Calendar.getInstance();
					
					List<Campanha> campanhas = null;
					//enquanto o (numero de campanhas que tiveram a tada ajustada - campanhas que tem data igual a nova) for diferente do numero total
					while(campanhas == null || campanhas.size() != listCampanha.size()) {
						
						campanhas =
						listCampanha.stream().map(x -> {
							//adiciona 1 dia a campanhas existentes
						   c.setTime(x.getDtTerminoVigencia());
						   c.add(Calendar.DAY_OF_MONTH, 1);
						   x.setDtTerminoVigencia(c.getTime());
						   return x;				
					    }).filter(value -> {
					    	//remove campanha que possue data igual
						   return !value.getDtTerminoVigencia().equals(campanha.getDtTerminoVigencia());          
						}).collect(Collectors.toList());;
						
					}
					//apos ajustar datas das campanhas adiciona a nova
					campanhas.add(campanha);
					campanhaRepository.saveAll(campanhas);
				}
	}

}
