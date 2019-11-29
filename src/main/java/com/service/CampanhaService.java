package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.Campanha;

@Service
public interface CampanhaService {
 
   List<Campanha> buscarCampanhas();
   Campanha buscarCampanhaPorId(Long idCampanha);
   void salvar(Campanha campanha);
   void deletar(Long idCampanha);
	
}
