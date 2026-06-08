package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.example.models.Departamento;
import com.example.models.DetallesEmpleado;
import com.example.models.Empleado;
import com.example.models.Genero;
import com.example.services.DepartamentoService;
import com.example.services.DepartamentoServiceImpl;
import com.example.services.EmpleadoService;
import com.example.services.EmpleadoServiceImpl;


@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger("UpdateController");

    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
		LOG.info("ID del empleado: " + idEmpleado);
		EmpleadoService empleadoServicio = new EmpleadoServiceImpl();
		empleadoServicio.getDetallesEmpleado(idEmpleado);
		
		// mostrar la vista de detalles del empleado, con los datos obtenidos del servicio
		List<Empleado> empleados = empleadoServicio.getEmpleados();
		
		Empleado empleado = empleados.stream()
				.filter(e -> e.id() == idEmpleado)
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + idEmpleado));
		
		request.setAttribute("empleado", empleado);
		DetallesEmpleado detallesEmpleado = empleadoServicio.getDetallesEmpleado(idEmpleado);
		
		request.setAttribute("detallesEmpleado", detallesEmpleado);
		
		DepartamentoService DepartamentoServicio = new DepartamentoServiceImpl();
		
		List<Departamento> departamentos = null;
		// falta un try catch
		try {
			departamentos = DepartamentoServicio.getDepartamentos();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		request.setAttribute("departamentos", departamentos);
		
		request.getRequestDispatcher("views/formularioModificacion.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
		String nombre = request.getParameter("nombre");
		String primerApellido = request.getParameter("primerApellido");
		String segundoApellido = request.getParameter("segundoApellido") == null ? "": request.getParameter("segundoApellido");
		LocalDate fechaAlta = LocalDate.parse(request.getParameter("fechaAlta"));
		Genero genero = Genero.valueOf(request.getParameter("genero"));
		BigDecimal salario = BigDecimal.valueOf(Double.valueOf(request.getParameter("salario")));
		int departamentos_id = Integer.parseInt(request.getParameter("departamentos"));
		
		List<String> direccionesCorreo = new ArrayList<>();
		List<String> numerosTelefono = new ArrayList<>();
				
		String cadenaCorreos = request.getParameter("correos").equals("") ? null : request.getParameter("correos");
		String cadenaTelefonos = request.getParameter("telefonos").equals("") ? null : request.getParameter("telefonos");
		
		if (cadenaCorreos != null) {
		    String[] arrayCorreos = cadenaCorreos.split(";");
		    direccionesCorreo = Arrays.asList(arrayCorreos);
		}
		
		if (cadenaTelefonos != null) {
		    String[] arrayTelefonos = cadenaTelefonos.split(";");
		    numerosTelefono =Arrays.asList(arrayTelefonos);		    
		}
		
		Empleado empleado = Empleado.builder()
				.id(idEmpleado)
				.nombre(nombre)
				.primerApellido(primerApellido)
				.segundoApellido(segundoApellido) 
				.fechaAlta(fechaAlta) 
				.genero(genero) 
				.salario(salario) 
				.departamentos_id(departamentos_id)
				.build();
	
		
		// llamar a la capa de servicios para que llame a DAO para insertar el nuevo empleado
		
		EmpleadoService empleadoService = new EmpleadoServiceImpl();
		
		try {
			empleadoService.modificarEmpleado(empleado, direccionesCorreo, numerosTelefono);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// como se va a redirigir a la vista del listado de empleados, se recupera el listado actualizado de 
		// empleados para mostrarlo en la vista.
		List<Empleado> empleados = empleadoService.getEmpleados();
		request.setAttribute("empleados", empleados);
		
		 request.getRequestDispatcher("views/listadoEmpleados.jsp").forward(request, response);
		// response.sendRedirect("views/listadoEmpleados.jsp");
	}

}
