package com.nielsonferreira.dcfc.repository.cliente;

import java.util.List;

import com.nielsonferreira.dcfc.models.Cliente;
import com.nielsonferreira.dcfc.repository.filter.ClienteFilter;

public interface ClienteRepositoryQuery {

	public List<Cliente> filtrar(ClienteFilter clienteFilter);
}
