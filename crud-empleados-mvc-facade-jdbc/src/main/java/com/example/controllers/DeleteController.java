package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.services.EmpleadoService;
import com.example.services.EmpleadoServiceImpl;


@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public DeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
		
		// conectar con la capa de servicios
		EmpleadoService empleadoService = new EmpleadoServiceImpl();
		// Eliminar el empleado con el id obtenido del request
		empleadoService.deleteEmpleado(idEmpleado);
		// Redirigir a la vista principal (MainController) para mostrar el listado actualizado de empleados
		
		response.sendRedirect("MainController");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
