<%@page import="com.example.models.Empleado"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
  table {
    width: 100%;
    border-collapse: collapse; /* Evita que las líneas se vean dobles */
  }
  th, td {
    border: 1px solid #ccd1d1; /* Grosor, estilo (sólido) y color de la raya */
    padding: 8px;              /* Espacio interno para que no se pegue el texto */
    text-align: left;
  }
  th {
    background-color: #f2f2f2; /* Color de fondo para los encabezados */
  }
</style>
<meta charset="UTF-8">
<title>Listado de empleados</title>
</head>
<body>
	<%
	List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
	
	
	%>
	<h1>Listado de empleados</h1>
	<table>
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Primer Apellido</th>
				<th>Segundo Apellido</th>
				<th>Fecha de alta</th>
				<th>Genero</th>
				<th>Salario</th>
			</tr>
		</thead>
		<tbody>
			<!-- Recorremos la lista de empleados y mostramos sus datos en la tabla -->
        	<% for (Empleado empleado : empleados) { %>
                <tr>
			<td><%= empleado.nombre() %></td>
            <td><%= empleado.primerApellido() %></td>
            <td><%= empleado.segundoApellido() != null ? empleado.segundoApellido() : "" %></td>
            <td><%= empleado.fechaAlta() %></td>
            <td><%= empleado.genero() %></td>
            <td><%= empleado.salario() %></td>
                </tr>
          <% } %>
          <!--  tr>td{<=empleado.() %>}*6 -->
		</tbody>
	</table>
</body>

</html>