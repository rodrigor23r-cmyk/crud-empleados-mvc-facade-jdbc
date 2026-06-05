
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
	<h1>Formulario de Alta de empleado</h1>
	<fieldset class="formulario">

		<legend>Formulario de Gestión de Empleado</legend>
		<form action="AltaController" method="post">
			<div><label for="nombre">Nombre: </label><input type="text" id="nombre" name="nombre" required placeholder="introducir nombre"></div><br>
			<div><label for="primerApellido">Primer Apellido: </label><input type="text" id="primerApellido" name="primerApellido" required placeholder="introducir primer apellido"></div><br>
			<div><label for="segundoApellido">Segundo Apellido: </label><input type="text" id="segundoApellido" name="segundoApellido" placeholder="introducir segundo apellido"></div><br>
			<div><label for="fechaAlta">Fecha de Alta: </label><input type="date" id="fechaAlta" name="fechaAlta" required placeholder="introducir fecha aquí!"></div><br>
			<div><fieldset><legend>Género</legend>
				<label for="hombre">Hombre: </label>
				<input type="radio" id="hombre" required name="genero" value="HOMBRE">
				<label for="mujer">Mujer: </label>
				<input type="radio" id="mujer" required name="genero" value="MUJER">
				<label for="otro">Otro: </label>
				<input type="radio" id="otro" required name="genero" value="OTRO">
			</fieldset></div><br>
			<div><label for="salario">Salario: </label><input type="text" id="salario" name="salario" required></div><br>

			<%
				List<Departamento> departamentos = (List<Departamento>) request.getAttribute("departamentos");
			%>
			<div><label for="departamentos">Departamentos: </label>
			<select id="departamentos" name="departamentos" required>
			<option></option>
			<%
				for (Departamento departamento : departamentos) {
			%>
				<option value= "<%=departamento.id()%>"><%=departamento.nombre()%></option>
			<%
				}
			%>
			</select></div><br>
			
			<div><label for="correos">Correos: </label>
			<input type="text" id="correos" name="correos" class="input-grande" placeholder="uno o varios separados por (;)">
			</div><br>
			<div><label for="telefonos">Números de teléfono: </label>
			<input type="text" id="telefonos" name="telefonos" class="input-grande" placeholder="uno o varios separados por (;)">
			</div><br>
			
			<input type="submit" value="Enviémos el formulario">
		</form>
		
		
	</fieldset>
</body>
</html>