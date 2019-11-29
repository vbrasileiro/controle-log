package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Campanha;
import com.queue.CampanhaQueue;
import com.service.CampanhaService;

import springfox.documentation.spring.web.json.Json;

@Controller
@RequestMapping("/campanha")
public class CampanhaController {

	
	@Autowired
	private CampanhaService campanhaService;
	
	@RequestMapping(value="busca-campanha", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Campanha>> buscarCampanhas(){
		List<Campanha> campanhas = campanhaService.buscarCampanhas();
		return new ResponseEntity<List<Campanha>>(campanhas,HttpStatus.OK);
	}
	
	@RequestMapping(value="busca-campanha-id", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Campanha> buscarCampanhas(@RequestParam Long idCampanha){
		Campanha campanha = campanhaService.buscarCampanhaPorId(idCampanha);
		return new ResponseEntity<Campanha>(campanha,HttpStatus.OK);
	}
	
	@RequestMapping(value="inserir-atualizar-campanha", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> inserirCampanha(@RequestBody Campanha campanha){
		campanhaService.salvar(campanha);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="deletar-campanha", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> deletarCampanha(@RequestParam Long idCampanha){
		campanhaService.deletar(idCampanha);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
