package com.example.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.dao.DBConexion;

public class EmpleadoServiceImpl implements EmpleadoService {

	@Override
	public boolean isConnected() throws SQLException {
		
		// Conectar con la capa DAO
		DBConexion dbConexion = new DBConexion("root", "Temp2026");
		
		//Para cerrar la conexión
		boolean conexionOK = false;
		
		Connection conn = null;
		
		try {
			conn = dbConexion.getConexion();
			if (conn != null) {
				conexionOK = true;
			}
			else {
				conexionOK = false;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				
					conn.close();
				
			}
		}
		
		return conexionOK;
	}

}
