<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 	El patron MVC (Modelo-Vista-Controlador) es una arquitectura de software que
 separa una aplicación en tres componentes principales: el Modelo, la Vista y el Controlador. 
 Esta separación permite una mejor organización del código, facilita el mantenimiento y mejora 
 la escalabilidad de la aplicación. 
 
 El modelo: Comprende todo lo que tiene que ver con los datos
 La vista: Son los archivos JSP
 El controlador: Son los Servelets 
 
 El patrón de diseño FACADE se usa conjuntamente con MVC
 FACADE	 divide en capas el proyecto, facilmente sustituibles y que cada una ocultan las 
 complejidades de dicha implementación.
 En una aplicación tengamos las capas siguientes:
 
 Cada capa es un paquete y cada capa tiene una interfaz y una implementación, 
 de esta forma se pueden sustituir las implementaciones sin afectar a las demás capas.
 
 1.- Capa DAO (data access object), en el modelo que implementa el acceso a datos
con JDBC, Hibernate, JPA, etc. Esta capa se encarga de interactuar con la base de datos y 
realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los datos.
 2.- Capa de Servicios
 3.- Capa de los controladores, varios
 4.- Capa de implementaciones
 5.- Capa model
 6.- etc...  -->

	<h1>Bienvenido a la aplicación de gestión de empleados</h1>
	<div>
		<a href="MainController">Mostrar Listado de Empleados</a>
	</div>
</body>
</html>