package com.os.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import com.os.model.Tecnico;

public class TecnicoDTO implements Serializable{
	private static final long serialVersionUID = 1L; 

	private Integer id;
	
	@NotEmpty(message = "O campo nome é requerido")
	private String nome;
	
	@CPF
	@NotEmpty(message = "O campo cpf é requerido")
	private String cpf;
	
	@NotEmpty(message = "O campo telefone é requerido")
	private String telefone;
	

	
	public TecnicoDTO() {
		super();
	}

	public TecnicoDTO(Tecnico tecnico) {
		this.id = tecnico.getId();
		this.nome = tecnico.getNome();
		this.cpf = tecnico.getCpf();
		this.telefone = tecnico.getTelefone();

		
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
}
