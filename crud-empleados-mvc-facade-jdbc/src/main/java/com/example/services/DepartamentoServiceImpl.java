package com.example.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.example.dao.DBConexion;
import com.example.models.Departamento;
import com.example.models.Empleado;
import com.example.models.Genero;

public class DepartamentoServiceImpl implements DepartamentoService {

	private static final Logger LOG = Logger.getLogger("DepartamentoServiceImpl");
	
	
	@Override
	public List<Departamento> getDepartamentos() {

		
List<Departamento> departamentos = new ArrayList<Departamento>();
		
		try (DBConexion dbConexion = new DBConexion("root", "Temp2026");
				Connection connection = dbConexion.getConexion();) {
			
			ResultSet rs = dbConexion.getDptos(connection);
			
			while (rs.next()) {
				
				departamentos.add(Departamento.builder()
						.id(rs.getInt("id"))
						.nombre(rs.getString("nombre"))
						.build());
			}
			
		} catch (Exception e) {
			
			LOG.severe("!!error al recuperar los departamentos desde el servicio!! " + e.getMessage());

		}
		
		return departamentos;
	}

}
