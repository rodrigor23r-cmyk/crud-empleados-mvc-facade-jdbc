package com.example.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.example.dao.DBConexion;
import com.example.models.DetallesEmpleado;
import com.example.models.Empleado;
import com.example.models.Genero;

public class EmpleadoServiceImpl implements EmpleadoService  {

	private static final Logger LOG = Logger.getLogger("EmpleadoServiceImpl");

	
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
			e.printStackTrace();
		}
		
		return empleados;
	}
	
	
	@Override
	public void altaEmpleado(Empleado empleado, List<String> direccionesCorreo, List<String> numerosTelefono) {
		
		try (DBConexion dbConexion = new DBConexion("root", "Temp2026");
				Connection connection = dbConexion.getConexion();) {
			
			dbConexion.altaEmpleado(empleado, direccionesCorreo, numerosTelefono, connection);
			
		} catch (Exception e) {
			LOG.severe("!!error al dar de alta el empleado desde el servicio!! " + e.getMessage());
		}
		
	}


	@Override
	public DetallesEmpleado getDetallesEmpleado(int idEmpleado) {
		
		DetallesEmpleado detallesEmpleado = null;
		
		try (
		DBConexion dbConexion = new DBConexion("root", "Temp2026");
		Connection connection = dbConexion.getConexion();) {
			
			ResultSet rs = dbConexion.detallesEmpleado(idEmpleado, connection);
			
			String nombreDpto = null;
			Set<String> direccionesCorreo = new HashSet<String>();
			Set<String> numerosTelefono = new HashSet<String>();
			
			if (rs.next()) {
				
					nombreDpto = rs.getString("nombre");
			}
			
			while (rs.next()) {
					direccionesCorreo.add(rs.getString("email"));
					numerosTelefono.add(rs.getString("numero"));	
			}
			
			detallesEmpleado = new DetallesEmpleado(nombreDpto, direccionesCorreo, numerosTelefono);
			
			LOG.info("Detalles del empleado con id " + idEmpleado + ": " + detallesEmpleado);
			
		} catch (Exception e) {
			LOG.severe("!!error al recuperar los detalles del empleado desde el servicio!! " + e.getMessage());
			e.printStackTrace();
		}
		
		return detallesEmpleado;
	}
	
}