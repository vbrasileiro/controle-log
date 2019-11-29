package com.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.entity.Campanha;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

	List<Campanha> findAll();
	List<Campanha> findBydtTerminoVigenciaAndIdCampanhaNot(Date dtTerminoVigencia,Long i);
}
