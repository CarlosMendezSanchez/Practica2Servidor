<%-- 
    Document   : graficaUsuarios
    Created on : 6 mar 2025, 19:00:51
    Author     : carlos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="../estilos/grafica.css">
        <link rel="stylesheet" type="text/css" href="../estilos/estiloPagina.css">
        <script>
            datos = [
                    <c:forEach var="usuario" items="${usuarios}" varStatus="status">
                       ['${usuario.nombre} ${usuario.apellidos}', ${fn:length(usuario.experiencias)} ] ${status.last?"":","}     
                    </c:forEach>
            ];
            
            datos2 = [
                    <c:forEach var="experiencias" items="${experiencias}" varStatus="status">
                       ['${experiencias.titulo}', ${fn:length(experiencias.actividades)} ] ${status.last?"":","}     
                    </c:forEach>
            ];
            
        </script>
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/highcharts-3d.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        <script src="https://code.highcharts.com/modules/export-data.js"></script>
        <script src="https://code.highcharts.com/modules/accessibility.js"></script>
        
        <title>Grafica Usuarios</title>
    </head>
    <body>
        <h1>Experiencias de los usuarios</h1>
        
        <figure class="highcharts-figure">
            <div id="container"></div>
            <p class="highcharts-description">
                Numero de experiencias de viajes de los usuarios.
            </p>
        </figure>
        
        <h2>Actividades de experiencias</h2>
        <figure class="highcharts-figure">
            <div id="container2"></div>
            <p class="highcharts-description">
                Actividades de experiencias entre 2 fechas.
            </p>
        </figure>        
        
        <h2>Introduzca 2 fechas para filtrar por fechas</h2>
        
        <form method="get">
           <label for="fechaInicio">Fecha 1:</label>
           <input type="date" id="fechaInicio" name="fechaInicio" required><br>
        
        <label for="fechaFin">Fecha 2:</label>
        <input type="date" id="fechaFin" name="fechaFin" required> <br>
        
        <input type="submit" value="Filtrar por 2 fechas">
        </form> 
        
        <a href="${pageContext.request.contextPath}/admin/ControladorInicioAdmin">Volver a la administración</a>
        
        <script src="${pageContext.request.contextPath}/js/grafica.js"></script>
        <script src="${pageContext.request.contextPath}/js/graficaActividades.js"></script>
    </body>
</html>
