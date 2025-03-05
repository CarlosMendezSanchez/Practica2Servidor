<%-- 
    Document   : crearExperienciaViajes
    Created on : 2 mar 2025, 20:09:43
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
        <h1>Crear experiencias de viaje</h1>
        
        <form method="post">
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required><br>

        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" required></textarea><br>

        <label for="fechaInicio">Fecha de Inicio:</label>
        <input type="date" id="fechaInicio" name="fechaInicio" required><br>
        
        <label for="publicada">¿Publicar?</label>
        <select id="publicada" name="publicada">
            <option value="true">Sí</option>
            <option value="false">No</option>
        </select><br>
        
        <button type="submit">Crear Experiencia</button>
        <a href="${pageContext.request.contextPath}/usuario/ControladorInicio">Volver a experiencias</a>
    </form>
    </body>
</html>
