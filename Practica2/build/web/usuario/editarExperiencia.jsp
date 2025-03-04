<%-- 
    Document   : editarExperiencia
    Created on : 3 mar 2025, 20:52:15
    Author     : carlos
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editar Experiencias</h1>
        <form method="post">
        <input type="hidden" name="id" value="${experiencia.id}">
        <label>Titulo:</label>
        <input type="text" name="titulo" value="${experiencia.titulo}" required><br>
        <label>Descricion:</label>
        <input type="text" name="descripcion" value="${experiencia.descripcion}" required><br>
        <label>Fecha inicio:</label>
        <input type="date" name="fechaInicio" value="${experiencia.fechaInicio}" required><br>
        <label>Publicada:</label>
        <input type="checkbox" name="publicada" 
               <c:if test="${experiencia.publicada == true}">
                   checked
               </c:if>
        >
        <br>
        
        <input type="submit" value="Guardar cambios">
        
        <a href="usuario/ControladorInicio">Volver a inicio</a>
        </form>
    </body>
</html>
