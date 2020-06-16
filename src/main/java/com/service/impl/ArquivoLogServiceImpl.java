package com.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.model.ArquivoLog;
import com.repository.ArquivoLogRepository;
import com.service.ArquivoLogService;

@Service
public class ArquivoLogServiceImpl<R> implements ArquivoLogService{

	@Autowired
	private ArquivoLogRepository arquivoLogRepository;
	
	
	@Override
	public List<ArquivoLog> buscarArquivoLog() {		
		return arquivoLogRepository.findAll();
	}
	
	@Override
	public List<ArquivoLog> buscarArquivoLogPorIP(String ip) {		
		return arquivoLogRepository.findByIp(ip);
	}

	@Override
	public void salvar(ArquivoLog arquivoLog) {
		arquivoLogRepository.save(arquivoLog);
		
	}

	@Override
	public void deletar(Long idArquivoLog) {
		arquivoLogRepository.deleteById(idArquivoLog);		
	}
	
	@Override
	public void leArquivo(MultipartFile file) throws IOException {
		
		InputStreamReader reader = new InputStreamReader(file.getInputStream());
    	//@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(reader);
    	String linha = br.readLine();
    	ArquivoLog arquivoLog = new ArquivoLog();
    	String array[];
    	int inseridos = 0;
    	while(linha != null) {
    		arquivoLog = new ArquivoLog();
    		array = linha.split("\\|");
    		
    		arquivoLog.setDtLog(converteStringParaDate(array[0]));
    		arquivoLog.setIp(array[1]);
    		arquivoLog.setRequest(array[2]);
    		arquivoLog.setStatus(array[3]);
    		arquivoLog.setUserAgent(array[4]);
    		inseridos++;
    		System.out.println("Inserindo "+inseridos);
    		salvar(arquivoLog);
    		
    		linha = br.readLine();
    		
    	}
	}

	
	public Date converteStringParaDate(String data) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
        try {
			return formatter.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ArquivoLog> buscarArquivoLogPorRangeData(Date data1, Date data2) {
		return arquivoLogRepository.getAllBetweenDates(data1,data2);
		
	}

}
