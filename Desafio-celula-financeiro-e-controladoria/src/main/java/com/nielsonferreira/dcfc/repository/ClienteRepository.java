package com.nielsonferreira.dcfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nielsonferreira.dcfc.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
