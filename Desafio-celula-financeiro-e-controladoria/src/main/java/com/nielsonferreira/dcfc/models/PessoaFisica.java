package com.nielsonferreira.dcfc.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("FISICA")
public class PessoaFisica extends Cliente{

	@NotNull
	private String nome;
	
	@NotNull(message = "CPF não pode ser nulo")
	@NotEmpty(message = "O campo CPF é obrigatório")
	private String cpf;
	
	@Transient
	private String tipo = "Pessoa Física";
}
