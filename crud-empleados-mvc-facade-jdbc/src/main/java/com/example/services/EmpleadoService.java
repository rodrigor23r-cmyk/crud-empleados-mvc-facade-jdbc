package com.example.services;


// import java.sql.SQLException;
import java.util.List;

import com.example.models.DetallesEmpleado;
import com.example.models.Empleado;

public interface EmpleadoService {

	
	// Método para comprobar la conexión a la base de datos
	// _______________podemos quitar "public abstract" _______________
	// public abstract boolean isConnected() throws SQLException, Exception;
	
	public abstract List<Empleado> getEmpleados();
	public abstract void altaEmpleado(Empleado empleado, List<String> direccionesCorreo, List<String> numerosTelefono) throws Exception;
	public abstract DetallesEmpleado getDetallesEmpleado(int idEmpleado);
	public abstract void modificarEmpleado(Empleado empleado, List<String> direccionesCorreo, List<String> numerosTelefono) throws Exception;
}
