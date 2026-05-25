package com.example.services;

import java.sql.Connection;

import com.example.dao.DBConexion;

public class EmpleadoServiceImpl implements EmpleadoService {

	@Override
	public boolean isConnected() {
		
		// Conectar con la capa DAO
		DBConexion dbConexion = new DBConexion("root", "Temp2026");
		
		// TODO (tarea pendiente): cerrar la conexión
		
		Connection conn = null;
		
		try {
			conn = dbConexion.getConexion();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn == null? false : true;
	}

}
