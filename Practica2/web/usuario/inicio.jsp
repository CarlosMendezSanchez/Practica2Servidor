<%-- 
    Document   : inicio
    Created on : 26 feb 2025, 10:58:36
    Author     : carlos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Practica2</title>
    </head>
    <body>
        <h1>Hola ${usuario.nombre}</h1>
        
        <h2>Experiencias de viaje</h2>
        
        <table border="1">
        <tr>
            <th>ID</th>
            <th>Descripcion</th>
            <th>FechaInicio</th>
            <th>Acciones</th>
        
        <c:forEach var="experiencia" items="${experienciaViajes}">
        
        <tr>
            <td>${experiencia.titulo}</td>
            <td>${experiencia.fechaInicio}</td>
            <td>${experiencia.descripcion}</td>
        </tr>
        </c:forEach >
    
    <a href="../ControladorCrearExperienciasViaje">
        <button>Crear experiencia</button>
    </a>
    </body>
</html>
