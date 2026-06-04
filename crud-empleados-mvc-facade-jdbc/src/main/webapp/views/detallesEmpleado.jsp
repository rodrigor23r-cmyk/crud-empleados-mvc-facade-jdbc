<%@page import="com.example.models.DetallesEmpleado"%>
<%@page import="com.example.models.Empleado"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalles del empleado</title>
</head>
<body>
	<%
		Empleado empleado = (Empleado) request.getAttribute("empleado");
		DetallesEmpleado detallesEmpleado = (DetallesEmpleado) request.getAttribute("detallesEmpleado");
	%>
	<h1>Detalles del empleado</h1>
	<p>Nombre: <%= empleado.nombre() %></p>
	<p>Apellidos: <%= empleado.primerApellido() %> <%=empleado.segundoApellido()%></p>
	<p>Género: <%= empleado.genero().name() %></p>
	<p>Fecha de alta: <%=empleado.fechaAlta()%></p>
	<p>Departamento: <%= detallesEmpleado.nombreDpto() %></p>
	
	<p>Salario: <%= empleado.salario() %></p>
	
	
	<div>
		<h3>Teléfonos:</h3>
		<ul>
            <% for (String numero : detallesEmpleado.numerosTelefono()) { %>
                <li><%= numero %></li>
            <% } %>
        </ul>
	</div>
	
	<div>
		<h3>Correos:</h3>
		<ul>
            <% for (String email : detallesEmpleado.direccionesCorreo()) { %>
                <li><%=email%></li>
            <% } %>
        </ul>
	</div>
	
	
   
</body>
</html>