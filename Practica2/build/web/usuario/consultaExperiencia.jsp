<%-- 
    Document   : consultaExperiencia
    Created on : 5 mar 2025, 20:47:20
    Author     : carlos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>      
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="../estilos/estiloPagina.css">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/filtrarExperiencias.js"></script>  
    </head>
    <body onload="filtrar()">
        <h1>Experiencia de viajes</h1>
        <br>
        <div>
            <label>Filtrar experiencias</label>
            <input type="text" name="filtro" id="filtro" onkeyup="filtrar()">
        </div>
        <div id="listado">
            
        </div>
        <br>
        <a href="${pageContext.request.contextPath}/usuario/ControladorInicio">Volver</a>
    </body>
</html>
