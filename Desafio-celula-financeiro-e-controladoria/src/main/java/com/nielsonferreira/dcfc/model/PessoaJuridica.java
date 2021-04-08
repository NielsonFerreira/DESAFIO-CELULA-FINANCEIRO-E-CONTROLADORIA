package com.nielsonferreira.dcfc.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("JURIDICA")
@JsonTypeName("PessoaJuridica")
public class PessoaJuridica extends Cliente{

	@NotNull
	private String razaoSocial;
	
	private String nomeFantasia;
	
	@NotNull
	@NotEmpty
	private String cnpj;
	
	@Transient
	private String tipo = "Pessoa Jurídica";
}
