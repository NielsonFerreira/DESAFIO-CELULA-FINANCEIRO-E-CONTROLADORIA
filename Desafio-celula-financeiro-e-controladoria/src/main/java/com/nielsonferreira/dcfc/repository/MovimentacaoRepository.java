package com.nielsonferreira.dcfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nielsonferreira.dcfc.models.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

}
