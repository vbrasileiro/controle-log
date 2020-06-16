package com.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.ArquivoLog;

@Repository
public interface ArquivoLogRepository extends JpaRepository<ArquivoLog, Long> {

	List<ArquivoLog> findAll();
	
	List<ArquivoLog> findByIp(String ip);
	
	@Query(value = "from ArquivoLog t where DT_LOG BETWEEN :dataInicial AND :dataFinal")
	List<ArquivoLog> getAllBetweenDates(@Param("dataInicial")Date dataInicial,@Param("dataFinal")Date dataFinal);
}
