package com.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.example.models.Empleado;

public class DBConexion implements AutoCloseable {
	
	private String username;
	private String password;
	
	// esta variable se inicializa con null:
	private Connection connection;
	// Logger para mostrar mensajes de conexión. println no es recomendable para mostrar mensajes de conexión, es mejor usar un logger porque si el servidor se cae,
	// el logger puede guardar los mensajes en un archivo de log para su posterior análisis, mientras que println solo muestra los mensajes en la consola y se perderían si el servidor se cae.
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
			// Cargar el driver de MySQL. Esto es necesario para que el DriverManager pueda encontrar el driver y establecer la conexión. Si no se carga el driver, 
			// el DriverManager no podrá establecer la conexión y lanzará una excepción.
			Class.forName("com.mysql.cj.jdbc.Driver"); // en la carpeta de librerías del proyecto, debe estar el conector de MySQL (mysql-connector-java-8.0.33.jar)
			// y dentro del conector, debe estar la clase com.mysql.cj.jdbc.Driver, que es la que se carga en esta línea de código.
			this.connection = DriverManager.getConnection(urlConexion, info);
			LOG.info("Conectado exitosamente desde DAO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.info("!!error de conexión a la BD desde el DAO!!");
			e.printStackTrace();
		}
		
		return this.connection;
	}

	@Override
	public void close() throws Exception {

		this.connection.close();
		
	}
	
	// Método que recupera todos los registros de la tabla empleados.
	public ResultSet getEmpleados (Connection connection) {
		
		String query = "SELECT * FROM `empresa-crud-empleados`.empleados";
		
		ResultSet rs = null;
		
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			
			rs = stmt.executeQuery(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return rs;
	}
	
	public ResultSet getDptos(Connection connection) {
		
		ResultSet rs = null;
		String query = "SELECT * FROM `empresa-crud-empleados`.departamentos";
		
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			LOG.severe("Error recuperando departamentos porque: " + e.getMessage());
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void altaEmpleado(Empleado empleado, List<String> direccionesCorreo, List<String> numerosTelefono, Connection connection) throws SQLException {
		
		
		String query1 = "INSERT INTO empleados (`nombre`, `primerApellido`, `segundoApellido`, `fechaAlta`, `genero`, `salario`, `departamentos_id`)"
				+ " VALUES(?,?,?,?,?,?,?)";
		// Sentencias preparadas: prepared statement. Como los procedimientos almacenados
		String query2 = "INSERT INTO correos (`email`, `empleados_id`) VALUES (?,?)";
		String query3 = "INSERT INTO telefonos (`numero`, `empleados_id`) VALUES (?,?)";
		
		// todo debe hacerse en el marco de una transacción.
		try {
			// iniciar transacción
			connection.setAutoCommit(false);
			
			PreparedStatement stmt1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			
			stmt1.setString(1, empleado.nombre());
			stmt1.setString(2, empleado.primerApellido());
			stmt1.setString(3, empleado.segundoApellido());
			stmt1.setDate(4, Date.valueOf(empleado.fechaAlta()));
			stmt1.setString(5, empleado.genero().name());
			stmt1.setDouble(6, empleado.salario().doubleValue());
			stmt1.setInt(7, empleado.departamentos_id());
			// total de filas afectadas por la ejecución de la sentencia SQL. Si es 0, no se ha insertado ningún registro, 
			// lo que indica que ha habido un error en la inserción.
			int totalFilas = stmt1.executeUpdate();
			
			if (totalFilas != 0) {
				// recuperar el id del empleado recién insertado para usarlo en las tablas de correos y teléfonos
				long lastInsertedId = 0L;
				ResultSet rs = stmt1.getGeneratedKeys();
				if (rs.next()) { //es obligatorio llamar a next() para posicionar el cursor en el primer registro del ResultSet, que es donde se encuentra el id generado. Si no se llama a next(), el cursor estará antes del primer registro y no se podrá recuperar el id.
					lastInsertedId = rs.getLong(1);
				}
				
				if (direccionesCorreo != null && direccionesCorreo.size() > 0) {
					
					PreparedStatement stmt2 = connection.prepareStatement(query2);

					stmt2.setInt(2, Math.toIntExact(lastInsertedId));

// esto es ineficiente porque se ejecuta una sentencia SQL por cada correo electrónico, lo que puede generar una gran cantidad de sentencias SQL si el empleado tiene muchos correos electrónicos. Además, cada ejecución de la sentencia SQL implica una comunicación con la base de datos, lo que puede ralentizar el proceso de inserción.
//					for (String correo : direccionesCorreo) {
//						stmt2.setString(1, correo);
//						stmt2.executeUpdate();
//					}
					
					for(String correo : direccionesCorreo) {
						stmt2.setString(1, correo);
						stmt2.addBatch();
					}
					stmt2.executeBatch();
				}
				
					if (numerosTelefono != null && numerosTelefono.size() > 0) {
					
					PreparedStatement stmt3 = connection.prepareStatement(query3);

					stmt3.setInt(2, Math.toIntExact(lastInsertedId));
					
					for(String telefono : numerosTelefono) {
						stmt3.setString(1, telefono);
						stmt3.addBatch();
					}
					stmt3.executeBatch();
				}

				
				
			}
			
			connection.commit();
			
		} catch (Exception e) {
			LOG.severe("Error en la transacción de alta de empleado porque: " + e.getMessage());
			e.printStackTrace();
			connection.rollback();
			LOG.info("Transacción de alta de empleado revertida");
		} finally {
			connection.setAutoCommit(true);
		}
		
	}
	
	public ResultSet detallesEmpleado(int idEmpleado, Connection connection) {
		
		ResultSet rs = null;
		
		String query = "SELECT d.nombre, t.numero, c.email "
				+ "FROM empleados e "
				+ "LEFT JOIN departamentos d ON e.departamentos_id = d.id "
				+ "LEFT JOIN correos c ON c.empleados_id = e.id "
				+ "LEFT JOIN telefonos t ON t.empleados_id = e.id "
				+ "WHERE e.id = ?";
		
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, idEmpleado);
			rs = stmt.executeQuery();
			
		} catch (SQLException e) {
			LOG.severe("Error preparando la consulta de detalles de empleado porque: " + e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}
	
}
