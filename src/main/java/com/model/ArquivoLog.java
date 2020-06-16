package com.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "ARQUIVO_LOG")
@Getter
@Setter
@NoArgsConstructor
public class ArquivoLog implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="seq_arquivo_log", sequenceName="seq_arquivo_log")
	@GeneratedValue(generator="seq_arquivo_log", strategy=GenerationType.AUTO)
	@Column(name = "ID_ARQUIVO_LOG", updatable = false, nullable = false)
	private Long idArquivoLog;

	@Column(name = "DT_LOG")
	private Date dtLog;
	
	@Column(name = "IP")	
	private String ip;		

	@Column(name = "REQUEST")	
	private String request;

	@Column(name = "STATUS")	
	private String status;

	@Column(name = "USER_AGENT")	
	private String userAgent;

}
