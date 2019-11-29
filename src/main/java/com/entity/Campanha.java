package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "CAMPANHA")
public class Campanha implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_CAMPANHA")
	private Long idCampanha;

	@Column(name = "NM_CAMPANHA")
	private String nmCampanha;
	
	@Column(name = "ID_TIME_CORACAO")
	private int idTimeCoracao;
	
	@Column(name = "DT_INICIO_VIGENCIA")
	private Date dtInicioVigencia;
	
	@Column(name = "DT_TERMINO_VIGENCIA")
	private Date dtTerminoVigencia;
	
    
	public Campanha() {
		
	}

	public Campanha(Campanha campanha) {
		super();
		this.idCampanha = campanha.idCampanha;
		this.nmCampanha = campanha.nmCampanha;
		this.idTimeCoracao = campanha.idTimeCoracao;
		this.dtInicioVigencia = campanha.dtInicioVigencia;
		this.dtTerminoVigencia = campanha.dtTerminoVigencia;
	}

	public Long getIdCampanha() {
		return idCampanha;
	}

	public void setIdCampanha(Long idCampanha) {
		this.idCampanha = idCampanha;
	}

	public String getNmCampanha() {
		return nmCampanha;
	}

	public void setNmCampanha(String nmCampanha) {
		this.nmCampanha = nmCampanha;
	}

	public int getIdTimeCoracao() {
		return idTimeCoracao;
	}

	public void setIdTimeCoracao(int idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}

	public Date getDtInicioVigencia() {
		return dtInicioVigencia;
	}

	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public Date getDtTerminoVigencia() {
		return dtTerminoVigencia;
	}

	public void setDtTerminoVigencia(Date dtTerminoVigencia) {
		this.dtTerminoVigencia = dtTerminoVigencia;
	}
	
	

}
