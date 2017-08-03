package com.everis.alicante.courses.becajava.garage.interfaces.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import com.everis.alicante.courses.becajava.garage.domain.Cliente;
import com.everis.alicante.courses.becajava.garage.jdbc.ClientDaoJDBC;

import java.sql.*;

public class ClienteDAOJDBCImpl implements ClientDaoJDBC {
	
	//carga del driver y cadena de conexion
	private final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String JDBC_CADENA_CONEXION = "jdbc:mysql://localhost:3306/garaje";
	private final String user = "root";
	private final String pwd ="";
	
 // private static String FILE = "c:/temp/FirstPdf.pdf";
	
	
	 @Override
	public Map<String,Cliente> readClientes() throws IOException {
			
		Map<String,Cliente> clientes;
		
		
		clientes= new TreeMap<String,Cliente>();		 
			
		String linea;
			 
		File file= new File("src/main/resources/Clientes.txt");
		FileReader reader= new FileReader(file);
		BufferedReader  buffer= new BufferedReader(reader);
			 		 
		while((linea=buffer.readLine())!=null){				  
				
			if(!linea.contains("NIF")||linea.isEmpty()){
					
				Cliente clienteTemp= new Cliente();	
					
				String[] temp= linea.split(";");
					
				clienteTemp.setNif(temp[0]);
				clienteTemp.setNombreCompleto(temp[1]);											
				clientes.put(clienteTemp.getNif(),clienteTemp);
				
			}
				
		 }
				 
		 reader.close();				 
	
		return  clientes;	
	}

	@Override
	public void createCliente(Cliente cliente) throws IOException {
		
		Connection cn = null;
		Statement st = null;
		
		try {
			
			cn = this.getConnection();
			st = cn.createStatement();
			
			String sql = "INSERT INTO CLIENTES "
					+ " VALUES ('"+cliente.getNif() + "','" + cliente.getNombreCompleto()+ "')";
			st.execute(sql);
			
			} catch (Exception e) {
				System.out.println("Error al insertar el cliente" + e.getMessage());		
				} finally {
					try {
						cn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		
	}

	@Override
	public Cliente readCliente(String nif) throws IOException {
		
		Connection cn = this.getConnection();
		Cliente cliente = null;
		
		try {
			Statement st = cn.createStatement();
			String sql = " SELECT * FROM CLIENTES WHERE DNI = '" + nif +"'";
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
//				cliente = new Cliente (rs.getString("dni"),rs.getString("nombre"),
//						rs.getString("ape_1"));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				cn.close();
			}catch(SQLException e) {
				System.out.println("Error al cerrar la conexión");
			}
		}
		
		return cliente;
	}

	@Override
	public void deleteCliente(String nif) {

	}


	@Override
	public Connection getConnection(){

		Connection cn = null;
		
		try {
			//carga del driver
			Class.forName(MYSQL_JDBC_DRIVER);
			//conexion a la BBDD
			cn = DriverManager.getConnection(JDBC_CADENA_CONEXION, user, pwd);
			
		} catch (Exception e) {
			System.out.println("Error al obtener la conexión");
		}
		return cn;
	}


	@Override
	public Map<String, Cliente> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
