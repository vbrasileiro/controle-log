package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value="busca-log-data", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ArquivoLog>> buscarLog(@RequestParam String data1, @RequestParam String data2){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		List<ArquivoLog> arquivoLog;
		try {
			arquivoLog = arquivoLogService.buscarArquivoLogPorRangeData(formatter.parse(data1),formatter.parse(data2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
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
