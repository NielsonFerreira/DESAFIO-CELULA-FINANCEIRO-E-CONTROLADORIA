package com.nielsonferreira.dcfc.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "conta")
@SequenceGenerator(name = "SEQ_CONTA", sequenceName = "SEQ_CONTA", initialValue = 1, allocationSize = 1 )
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTA")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE", foreignKey = @ForeignKey(name = "FK_CLIENTE_CONTA"))
	private Cliente cliente;
	
	private String numero;
	
	@Enumerated(EnumType.STRING)
	private tipoMovimentacao tipo;
	
}
