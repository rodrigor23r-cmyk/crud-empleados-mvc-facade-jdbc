package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import com.example.services.EmpleadoService;
import com.example.services.EmpleadoServiceImpl;


@WebServlet("/DetallesController")
public class DetallesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger("DetallesController");

    public DetallesController() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
		LOG.info("ID del empleado: " + idEmpleado);
		EmpleadoService empleadoService = new EmpleadoServiceImpl();
		empleadoService.getDetallesEmpleado(idEmpleado);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
