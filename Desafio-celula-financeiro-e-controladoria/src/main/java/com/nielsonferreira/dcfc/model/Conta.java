package com.nielsonferreira.dcfc.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Conta")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@ManyToOne
	private Cliente cliente;
	private String numero;
	
	@Enumerated(EnumType.STRING)
	private tipoMovimentacao tipo;
	
}
