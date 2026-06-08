<%@page import="com.example.controllers.AltaController"%>
<%@page import="com.example.models.Genero"%>
<%@page import="com.example.models.DetallesEmpleado"%>
<%@page import="com.example.models.Empleado"%>
<%@page import="com.example.models.Departamento"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<style>
		.formulario {
			max-width: 500px;
		}
        .input-grande {
            width: 100%;         /* Se adapta al ancho de la pantalla */
        }
    </style>
    
<meta charset="UTF-8">
<title>Formulario de empleado</title>
</head>

<body>
	<%
		Empleado empleado = (Empleado) request.getAttribute("empleado");
		DetallesEmpleado detallesEmpleado = (DetallesEmpleado) request.getAttribute("detallesEmpleado");
		List<Departamento> departamentos = (List<Departamento>) request.getAttribute("departamentos");
	%>
	<h1>Formulario de Modificación de empleado</h1>
	<fieldset class="formulario">

		<legend>Formulario de modificación de Empleado</legend>
		<form action="<%= empleado == null ? "AltaController" : "UpdateController"%>" method="post">
			<input type="hidden" id="idEmpleado" name="idEmpleado" value="<%=empleado.id() %>">
			<div><label for="nombre">Nombre: </label><input type="text" id="nombre" name="nombre" value="<%=empleado != null ? empleado.nombre() : ""%>" required placeholder="inserta nombre"></div><br>
	        <div><label for="primerApellido">Primer Apellido: </label><input type="text" id="primerApellido" name="primerApellido" required value="<%=empleado.primerApellido()%>"></div><br>
			<div><label for="segundoApellido">Segundo Apellido: </label><input type="text" id="segundoApellido" name="segundoApellido" value="<%=empleado.segundoApellido() == null ? "" : empleado.segundoApellido()%>"></div><br>	
			<div><label for="fechaAlta">Fecha de Alta: </label><input type="date" id="fechaAlta" name="fechaAlta" required value="<%=empleado.fechaAlta() %>"></div><br>
			<div><label for="salario">Salario: </label><input type="text" id="salario" name="salario" required value="<%=String.format("%.2f", empleado.salario()).replace(',', '.')%>"></div><br>
			<div><fieldset><legend>Género</legend>
				<label for="hombre">Hombre: </label>
				<input type="radio" id="hombre" required name="genero" value="HOMBRE" <%=empleado != null && empleado.genero().name().equals(Genero.HOMBRE) ? "checked" : "" %>>
				<label for="mujer">Mujer: </label>
				<input type="radio" id="mujer" required name="genero" value="MUJER" <%= empleado.genero().name().equals(Genero.MUJER) ? "checked" : "" %>>
				<label for="otro">Otro: </label>
				<input type="radio" id="otro" required name="genero" value="OTRO" <%= empleado.genero().name().equals(Genero.OTRO) ? "checked" : "" %>>
			</fieldset></div><br>
			
			<p>Departamento: <%= detallesEmpleado.nombreDpto() %></p>
			
			<div><label for="departamentos">Departamentos: </label>
			<select id="departamentos" name="departamentos" required>
			<%
				for (Departamento departamento : departamentos) {
			%>
				<option value= "<%=departamento.id()%>" 
				<%= empleado != null && departamento.nombre().equals(detallesEmpleado.nombreDpto()) ? "selected" : "" %>
				><%=departamento.nombre()%></option>
			<%
				}
			%>
			</select></div><br>
			
			<div><label for="correos">Correos: </label>
			<input type="text" id="correos" name="correos" class="input-grande" value="<%=
			detallesEmpleado.direccionesCorreo() != null && !detallesEmpleado.direccionesCorreo().isEmpty() && !detallesEmpleado.direccionesCorreo().contains(null) ? String.join(";", detallesEmpleado.direccionesCorreo()) : "" %>">
			</div><br>
			<div><label for="telefonos">Números de teléfono: </label>
			<input type="text" id="telefonos" name="telefonos" class="input-grande" value="<%=
			detallesEmpleado.numerosTelefono() != null && !detallesEmpleado.numerosTelefono().isEmpty() && !detallesEmpleado.numerosTelefono().contains(null) ? String.join(";", detallesEmpleado.numerosTelefono()): ""%>">
			</div><br>
			
			<input type="submit" value="Modificar empleado">
		</form>
		
		
	</fieldset>
</body>
</html>