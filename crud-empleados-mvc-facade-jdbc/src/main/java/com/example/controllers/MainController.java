package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.example.models.Empleado;
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
		
		// conectar con la capa de servicios
		EmpleadoService empleadoService = new EmpleadoServiceImpl();
		
		List<Empleado> empleados = empleadoService.getEmpleados();
		
		// El listado de empleados se guarda como atributo en el request, para que pueda ser renderizado en la vista JSP.
		
		request.setAttribute("empleados", empleados);
		
		request.getRequestDispatcher("views/listadoEmpleados.jsp").forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
