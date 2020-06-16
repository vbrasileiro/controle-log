package com.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.model.ArquivoLog;

@Transactional
@Service
public interface ArquivoLogService {
 
   List<ArquivoLog> buscarArquivoLog();
   List<ArquivoLog> buscarArquivoLogPorIP(String ip);
   void salvar(ArquivoLog arquivoLog);
   void deletar(Long idArquivoLog);
   void leArquivo(MultipartFile file) throws IOException;
   List<ArquivoLog> buscarArquivoLogPorRangeData(Date data1, Date data2);
	
}
