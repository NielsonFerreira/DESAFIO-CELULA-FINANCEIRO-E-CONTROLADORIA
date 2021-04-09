package com.nielsonferreira.dcfc.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
@DiscriminatorColumn(name = "Tipo_Pessoa", discriminatorType = DiscriminatorType.STRING)
@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE", initialValue = 1, allocationSize = 1 )
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public abstract class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
	private Long id;
	
	@Embedded
	private Endereco endereco;
	
	@OneToMany
	private List<Conta> contas = new ArrayList<>();
	
	private String telefone;
	
}
