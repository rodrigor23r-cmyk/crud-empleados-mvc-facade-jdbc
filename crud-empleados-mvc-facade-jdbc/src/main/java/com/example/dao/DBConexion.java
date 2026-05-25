package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConexion {
	
	private String username;
	private String password;
	
	// esta variable se inicializa con null:
	private Connection connection;
	
	private static final Logger LOG = Logger.getLogger("DBConexion");
	
	
	// contructor con dos parámetros
	public DBConexion(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	// Método que establece con la BD
	public Connection getConexion () throws ClassNotFoundException {
		
//		Si estas utilizanso SSL quizas debas añadir: empresa-crud-empleados?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
// probar cambiar localhost por 127.0.0.1 => "jdbc:mysql://127.0.0.1:3306/empresa-crud-empleados"
		
		String urlConexion = "jdbc:mysql://localhost:3306/empresa-crud-empleados";
		Properties info = new Properties();
		
		info.put("user", this.username);
		info.put("password", this.password);
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(urlConexion, info);
			LOG.info("Conectado exitosamente");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.info("!!error de conexión a la BD!!");
			e.printStackTrace();
		}
		
		return this.connection;
	}
	
	
	
}
