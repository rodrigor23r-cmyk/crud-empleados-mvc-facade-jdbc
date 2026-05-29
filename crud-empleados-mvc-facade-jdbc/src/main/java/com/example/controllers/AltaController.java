package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.example.models.Departamento;
import com.example.services.DepartamentoService;
import com.example.services.DepartamentoServiceImpl;

/**
 * Servlet implementation class AltaController
 */
@WebServlet("/AltaController")
public class AltaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Se usa la interfaz como tipo y se instancia la clase implementadora
		DepartamentoService DepartamentoServicio = new DepartamentoServiceImpl();
		
		List<Departamento> departamentos = null;
		// falta un try catch
		try {
			departamentos = DepartamentoServicio.getDepartamentos();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		request.setAttribute("departamentos", departamentos);
		
		request.getRequestDispatcher("views/formularioAltaModificacion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
	}

}
