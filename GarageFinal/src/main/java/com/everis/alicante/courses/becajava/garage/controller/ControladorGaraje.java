package com.everis.alicante.courses.becajava.garage.controller;

import java.util.Date;
import java.util.Map;

import com.everis.alicante.courses.becajava.garage.domain.GarajeException;
import com.everis.alicante.courses.becajava.garage.domain.Plaza;
import com.everis.alicante.courses.becajava.garage.domain.Vehiculo;

public interface ControladorGaraje {
	
	Map<Integer, Plaza> listarPlazasLibres() throws GarajeException;
	void listarPlazasOcupadas() throws GarajeException;
	boolean reservarPlaza() throws GarajeException;
	void listarClientes() throws  GarajeException;
	void listarReservas() throws GarajeException;
	void listarVehiculos() throws GarajeException;
	void listarReservasByFecha(Date fechaInicio,Date fechaFin) throws GarajeException;
	void insertarVehiculo() throws GarajeException;
	void readVehiculo() throws GarajeException;
	void readAllVehiculo() throws GarajeException;

}
