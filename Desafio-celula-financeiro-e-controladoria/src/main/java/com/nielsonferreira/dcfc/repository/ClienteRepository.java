package com.nielsonferreira.dcfc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nielsonferreira.dcfc.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{


}
