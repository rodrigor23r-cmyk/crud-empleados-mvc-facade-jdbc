package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.example.models.DetallesEmpleado;
import com.example.models.Empleado;
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
		
		// mostrar la vista de detalles del empleado, con los datos obtenidos del servicio
		List<Empleado> empleados = empleadoService.getEmpleados();
		
		Empleado empleado = empleados.stream()
				.filter(e -> e.id() == idEmpleado)
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + idEmpleado));
		
		request.setAttribute("empleado", empleado);
		DetallesEmpleado detallesEmpleado = empleadoService.getDetallesEmpleado(idEmpleado);
		
		request.setAttribute("detallesEmpleado", empleadoService.getDetallesEmpleado(idEmpleado));
		request.getRequestDispatcher("views/detallesEmpleado.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
