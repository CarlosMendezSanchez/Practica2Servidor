<%-- 
    Document   : CrearComentario
    Created on : 5 mar 2025, 12:00:55
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
        <h1>Añadir un comentario</h1>
        <form method="post">
            <label for="contenido">Contenido del comentario</label><br>
            <textarea id="contenido" name="contenido" style="resize: none"></textarea><br>
            
            <button type="submit">Añadir comentario</button>
        </form>
        <a href="${pageContext.request.contextPath}/usuario/ControladorInicio">Volver a experiencias</a>
    </body>
</html>
