package com.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.multipart.MultipartFile;

import com.model.ArquivoLog;
import com.service.ArquivoLogService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/log")
public class ArquivoLogController{

	
	@Autowired
	private ArquivoLogService arquivoLogService;
	
	@RequestMapping(value="busca-logs", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ArquivoLog>> buscarLogs(){
		List<ArquivoLog> arquivoLog = arquivoLogService.buscarArquivoLog();
		return new ResponseEntity<List<ArquivoLog>>(arquivoLog,HttpStatus.OK);
	}
	
	@RequestMapping(value="busca-log-ip", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ArquivoLog>> buscarLog(@RequestParam String ip){
		List<ArquivoLog> arquivoLog = arquivoLogService.buscarArquivoLogPorIP(ip);
		return new ResponseEntity<List<ArquivoLog>>(arquivoLog,HttpStatus.OK);
	}
	
	@RequestMapping(value="inserir-arquivo-log", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Inserir/Atualizar arquivoLog", response = ArquivoLog.class)
	@ResponseBody
	public ResponseEntity<String> inserirLog(@RequestBody ArquivoLog arquivoLog){
		arquivoLogService.salvar(arquivoLog);
			return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="inserir-document-arquivo-log", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Inserir documnento arquivo de log ", response = ArquivoLog.class)
	@ResponseBody
	public ResponseEntity<String> importContent(@RequestParam("file") MultipartFile file) throws Exception {
		arquivoLogService.leArquivo(file);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="deletar-log-id", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> deletarLog(@RequestParam Long idArquivoLog){
	         arquivoLogService.deletar(idArquivoLog);
		 return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
