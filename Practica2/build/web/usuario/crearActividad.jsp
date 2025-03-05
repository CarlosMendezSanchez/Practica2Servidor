<%-- 
    Document   : crearActividad
    Created on : 4 mar 2025, 17:36:46
    Author     : carlos
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Crear actividad</h1>
        
        <form method="post">
        <label for="tituloAct">Título:</label>
        <input type="text" id="tituloAct" name="tituloAct" required><br>

        <label for="descripcionAct">Descripción:</label>
        <textarea id="descripcionAct" name="descripcionAct" required></textarea><br>

        <label for="fechaInicioAct">Fecha de Inicio:</label>
        <input type="date" id="fechaInicioAct" name="fechaInicioAct" required><br>
        
        <button type="submit">Crear Actividad</button>
        </form>
        <a href="${pageContext.request.contextPath}/usuario/ControladorInicio">Volver a experiencias</a>
    </body>
</html>
