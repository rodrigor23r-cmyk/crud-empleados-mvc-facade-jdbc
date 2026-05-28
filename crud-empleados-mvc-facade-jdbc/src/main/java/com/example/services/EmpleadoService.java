package com.example.services;


// import java.sql.SQLException;
import java.util.List;

import com.example.models.Empleado;

public interface EmpleadoService {

	
	// Método para comprobar la conexión a la base de datos
	// _______________podemos quitar "public abstract" _______________
	// public abstract boolean isConnected() throws SQLException, Exception;
	
	public abstract List<Empleado> getEmpleados();
}
