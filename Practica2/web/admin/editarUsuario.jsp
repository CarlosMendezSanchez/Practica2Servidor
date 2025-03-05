<%-- 
    Document   : editarUsuario
    Created on : 1 mar 2025, 19:57:49
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
        <h1>Editar usuario</h1>
        <form method="post">
        <input type="hidden" name="id" value="${usuariosLista.id}">
        <label>Email:</label>
        <input type="email" name="email" value="${usuariosLista.email}" required><br>
        <label>Tipo:</label>
        <input type="text" name="tipo" value="${usuariosLista.tipo}" required><br>
        <label>Contraseña:</label>
        <input type="password" name="password" value="${usuariosLista.password}" required><br>
        <label>Activar usuario:</label>
        <input type="checkbox" name="activar"
               <c:if test="${usuariosLista.activo == true}">
                   checked
               </c:if>
        >
        <input type="submit" value="Guardar cambios">
        
        <a href="${pageContext.request.contextPath}/usuario/ControladorInicioAdmin">Volver a inicio</a>
    </form>
    </body>
</html>
