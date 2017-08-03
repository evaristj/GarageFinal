package com.everis.alicante.courses.becajava.garage.jdbc;

import java.io.IOException;
import java.util.Map;

import com.everis.alicante.courses.becajava.garage.domain.Cliente;
import java.sql.*;

public interface ClientDaoJDBC {
	
	 Map<String,Cliente> readClientes() throws IOException;
	 void createCliente(Cliente cliente) throws IOException;
	 Cliente readCliente(String nif) throws IOException;
	 void deleteCliente(String nif);
	 
	 public Connection getConnection () throws IOException;
	 public Map<String,Cliente> readAll();
}
