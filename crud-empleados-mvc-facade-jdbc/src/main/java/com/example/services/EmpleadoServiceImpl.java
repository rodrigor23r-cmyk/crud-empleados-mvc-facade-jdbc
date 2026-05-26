package com.example.services;

import java.sql.Connection;

import com.example.dao.DBConexion;

public class EmpleadoServiceImpl implements EmpleadoService {

	@Override
	public boolean isConnected() throws Exception {
		
		// Conectar con la capa DAO
		
		
		//Para cerrar la conexión
		boolean conexionOK = false;
		
		
		try (DBConexion dbConexion = new DBConexion("root", "Temp2026");
				Connection conn = dbConexion.getConexion();) {
			
			if (conn != null) {
				conexionOK = true;
			}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return conexionOK;
	}

}
