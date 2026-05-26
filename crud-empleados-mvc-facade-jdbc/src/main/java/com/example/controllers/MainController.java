package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.example.services.EmpleadoService;
import com.example.services.EmpleadoServiceImpl;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger("MainController");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// conectar con la capa de servicio, que a su vez conectará con la capa DAO para hacer consulta SQL correspondiente
		// finalmente el servlet mostrará la respuesta renderizando una vista JSP.
		
		
		// comprobar si se hace la conexión
		EmpleadoService empleadoService = new EmpleadoServiceImpl();
		
		boolean resultadoConexion = false;
		
		try {
			resultadoConexion = empleadoService.isConnected();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (resultadoConexion) {
			LOG.info("Conexión exitosa BRAVO! desde el controlador");
		}	else {
			LOG.info("La conexión ha fallado, desde el controlador!");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
