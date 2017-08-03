package com.everis.alicante.courses.becajava.garage.interfaces.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.text.Document;

import com.everis.alicante.courses.becajava.garage.domain.Cliente;
import com.everis.alicante.courses.becajava.garage.interfaces.ClienteDAO;

public class ClienteDAOFileImpl implements ClienteDAO {
	
 // private static String FILE = "c:/temp/FirstPdf.pdf";
	
	 private static void addEmptyLine() {

 }


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
		
		//falta metodo para a�adir linea vacia al pdf y m�s cosas
//		 Document document = new Document("c:/temp/FirstPdf.pdf");
//     PdfWriter writer.getInstance(document, new FileOutputStream(FILE));
		
		
		
		 File file= new File("src/main/resources/Clientes.txt");
		 FileWriter writer= new FileWriter(file,true);
		 BufferedWriter  buffer= new BufferedWriter(writer);
		
		 buffer.newLine();		 
		 
		 buffer.write(cliente.convierteAFormatoTxt());		
		
		 buffer.close();
		
	}

	@Override
	public Cliente readCliente(String nif) throws IOException {
		 
		 Cliente clienteTemp=null;			
		 String linea;
		 
		 
		 File file= new File("src/main/resources/Clientes.txt");
		 FileReader reader= new FileReader(file);
		 BufferedReader  buffer= new BufferedReader(reader);
		 		 
		 while((linea=buffer.readLine())!=null){				  
			
			if(!linea.contains("NIF")||linea.isEmpty()){
			
				String[] temp= linea.split(";");
			
				if(nif.equals(temp[0])){
										
					clienteTemp= new Cliente();						
					clienteTemp.setNif(temp[0]);
					clienteTemp.setNombreCompleto(temp[1]);			
					
				}
						
			}
			
		 }
			 
		 reader.close();		
		 	  	
		return  clienteTemp;	
	}

	@Override
	public void deleteCliente(String nif) {

	}


	@Override
	public Connection getConnection() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
