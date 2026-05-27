package com.example.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.example.dao.DBConexion;
import com.example.models.Empleado;
import com.example.models.Genero;

public class EmpleadoServiceImpl implements EmpleadoService {

	private static final Logger LOG = Logger.getLogger("EmpleadoServiceImpl");
	
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

	@Override
	public List<Empleado> getEmpleados() {
		
		List<Empleado> empleados = new ArrayList<Empleado>();
		
		try (DBConexion dbConexion = new DBConexion("root", "Temp2026");
				Connection connection = dbConexion.getConexion();) {
			
			ResultSet rs = dbConexion.getEmpleados(connection);
			
			while (rs.next()) {
				
				empleados.add(Empleado.builder()
						.id(rs.getInt("id"))
						.nombre(rs.getString("nombre"))
						.primerApellido(rs.getString("primerApellido"))
						.segundoApellido(rs.getString("segundoApellido"))
						.fechaAlta(rs.getDate("fechaAlta").toLocalDate())
						.genero(Genero.valueOf(rs.getString("genero")))
						.salario(new BigDecimal(rs.getDouble("salario")))
						.departamentos_id(rs.getInt("departamentos_id"))
						.build());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			LOG.severe("!!error al recuperar los empleados desde el servicio!!" + e.getMessage());

		}
		
		
		
		return empleados;
	}

}
