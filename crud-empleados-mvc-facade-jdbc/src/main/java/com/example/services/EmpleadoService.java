package com.example.services;

import java.sql.SQLException;

public interface EmpleadoService {

	
	// Método para comprobar la conexión a la base de datos
	public abstract boolean isConnected() throws SQLException, Exception;
	
}
