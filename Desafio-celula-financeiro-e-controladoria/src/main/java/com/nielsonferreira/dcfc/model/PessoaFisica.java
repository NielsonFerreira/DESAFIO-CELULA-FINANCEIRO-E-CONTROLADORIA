package com.nielsonferreira.dcfc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaFisica extends Cliente{

	private String cpf;
	private String nome;
	
}
