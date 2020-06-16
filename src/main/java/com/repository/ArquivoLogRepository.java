package com.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.ArquivoLog;

@Repository
public interface ArquivoLogRepository extends JpaRepository<ArquivoLog, Long> {

	List<ArquivoLog> findAll();
	
	List<ArquivoLog> findByIp(String ip);
}
