package com.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.model.ArquivoLog;
import com.repository.ArquivoLogRepository;
import com.service.ArquivoLogService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ArquivoLogControllerTest{

	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private ArquivoLogService arquivoLogService;
	
	@Autowired
    private ArquivoLogRepository arquivoLogRepository;
	
	
	@Test
	public void testWhenFindByIp_thenReturnNotEmpty() {
		ArquivoLog arquivo = new  ArquivoLog();
		arquivo.setIp("");
	 
	    // when
		List<ArquivoLog> found = arquivoLogService.buscarArquivoLogPorIP(arquivo.getIp());
	 
	    // then
		assertTrue(found.isEmpty());
	}
	
	
	 @Test
	 public void testInsert() {
		 
		ArquivoLog arquivoLog = new ArquivoLog();
	 	arquivoLog.setDtLog(new Date());
	 	arquivoLog.setIp("1234");
	 	arquivoLog.setRequest("teste");
	 	arquivoLog.setStatus("200");
	 	arquivoLog.setUserAgent("TESTE");
	 	
	 	entityManager.persist(arquivoLog);
	 	
		List<ArquivoLog> arquivoNew = arquivoLogRepository.findByIp(arquivoLog.getIp());
	        
		assertEquals(arquivoLog.getIp(), arquivoNew.get(0).getIp());
			
	   }
}
