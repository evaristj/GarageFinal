package com.everis.alicante.courses.becajava.garage.interfaces.implementation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.everis.alicante.courses.becajava.garage.domain.Vehiculo;
import com.everis.alicante.courses.becajava.garage.jdbc.VehiculoDaoJDBC;

public class VehiculoDAOJDBCImpl implements VehiculoDaoJDBC{
	
//carga del driver y cadena de conexion
	private final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String JDBC_CADENA_CONEXION = "jdbc:mysql://localhost:3306/garaje";
	private final String user = "root";
	private final String pwd = "";

	@Override
	public List<Vehiculo> readAll() {
		Connection cn = null;
		PreparedStatement pst = null;
		Vehiculo vehiculo = null;
		List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
		
		try {
			
			String sql = "SELECT * FROM VEHICULOS";
			cn = this.getConnection();
			pst = cn.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery(sql);
//			boolean hasNext = rs.next();
			while(rs.next()) {
				vehiculo = new Vehiculo();
				vehiculo.setMatricula(rs.getString("matricula"));
				vehiculo.setTipoVehiculo(rs.getString("tipo_vehiculo"));
				listaVehiculos.add(vehiculo);
			}
			
		}catch(Exception e) {
			System.out.println("Error al leer vehiculos: " + e.getMessage());
		}finally {
			try {
				cn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaVehiculos;
	}

	@Override
	public void createVehiculo(Vehiculo vehiculo) throws IOException {
		
		Connection cn = null;
		Statement st = null;
		
		try {
			
			cn = this.getConnection();
			st = cn.createStatement();
			
			String sql = "INSERT INTO VEHICULOS(MATRICULA, TIPO_VEHICULO) "
					+ " VALUES ('"+ "9999" + "','" 
					+ 1 + "');";
			st.execute(sql);
			
			} catch (Exception e) {
				System.out.println("Error al insertar el vehiculo" + e.getMessage());		
				} finally {
					try {
						cn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		
	}

	@Override
	public Vehiculo readVehiculo(String matricula) throws IOException {
		
		Connection cn = this.getConnection();
		Vehiculo vehiculo = null;
		
		try {
			Statement st = cn.createStatement();
			String sql = " SELECT * FROM VEHICULOS, TIPOS_VEHIUCLOS WHERE MATRICULA = '" + matricula + "' tipo_vehiculo = id_tipo";
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
				vehiculo = new Vehiculo();
				vehiculo.setMatricula(rs.getString("matricula"));
				vehiculo.setTipoVehiculo(rs.getString("tipoVehiculo"));
				System.out.println("La matricula es: "
						+ vehiculo.getMatricula() + " tipo vehiculo "
						+ vehiculo.getTipoVehiculo()
						+". Y el vehiculo es un: " + rs.getString("nombre_tipo"));
			}
			
		} catch (SQLException e) {
			System.out.println("Error al " + e.getMessage());
		}finally {
			try {
				cn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vehiculo;
		
	}

	@Override
	public void deleteVehiculo(String matricula) throws IOException {
		
		
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
	public List<Vehiculo> readVehiculos() throws IOException {
		
		Connection cn = null;
		PreparedStatement pst = null;
		Vehiculo vehiculo = null;
		List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
		
		try {
			
			String sql = "SELECT *FROM VEHICULOS";
			
			cn = this.getConnection();
			pst = cn.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				vehiculo = new Vehiculo();
				vehiculo.setMatricula(rs.getString("matricula"));
				vehiculo.setTipoVehiculo(rs.getString("tipo_vehiculos"));
				listaVehiculos.add(vehiculo);
			}
			
		} catch (Exception e) {
			System.out.println("Error al leer vehiculos: " + e.getMessage());
		} finally {
				try {
					cn.close();
				} catch (SQLException e) {
						e.printStackTrace();
				}
		}
	
		
		return listaVehiculos;
	}

}
