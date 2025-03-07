<%-- 
    Document   : editarActividad
    Created on : 5 mar 2025, 10:12:22
    Author     : carlos
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="../estilos/estiloPagina.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editar Actividad</h1>
        <form method="post">
        <input type="hidden" name="id" value="${actividad.id}">
        <label>Titulo:</label>
        <input type="text" name="tituloAct" value="${actividad.titulo}" required><br>
        <label>Descricion:</label>
        <input type="text" name="descripcionAct" value="${actividad.descripcion}" required><br>
        <label>Fecha inicio:</label>
        <input type="date" name="fechaInicioAct" value="${actividad.fecha}" required><br>
        
        <input type="submit" value="Guardar cambios">
        
        <a href="${pageContext.request.contextPath}/usuario/ControladorInicio">Volver a inicio</a>
        </form>
    </body>
</html>
