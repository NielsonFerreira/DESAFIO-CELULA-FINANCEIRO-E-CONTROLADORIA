package com.nielsonferreira.dcfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nielsonferreira.dcfc.models.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
