package com.everis.alicante.courses.becajava.garage.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.everis.alicante.courses.becajava.garage.domain.Vehiculo;

public interface VehiculoDaoJDBC {
	
	List<Vehiculo> readVehiculos() throws IOException;
	
	void createVehiculo(Vehiculo vehiculo) throws IOException;
	Vehiculo readVehiculo(String matricula) throws IOException;
	void deleteVehiculo(String matricula) throws IOException;
	
	public Connection getConnection () throws IOException;

	public List<Vehiculo> readAll() throws IOException;


}
