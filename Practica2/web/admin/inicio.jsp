<%-- 
    Document   : inicio
    Created on : 1 mar 2025, 19:27:25
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
        <h1>Hola administrador ${usuario.nombre}</h1>
        <h1>Lista de Usuarios</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>Tipo</th>
            <th>Activo</th>
            <th>Acciones</th>
            
            <c:forEach var="usuariosLista" items="${usuarios}">
        <tr>
            <td>${usuariosLista.id}</td>
            <td>${usuariosLista.email}</td>
            <td>${usuariosLista.tipo}</td>
            <td>${usuariosLista.activo}</td>
            <td>
                <a href="ControladorInicioAdmin?id=${usuariosLista.id}&accion=editar">Editar</a> |
                <a href="ControladorInicioAdmin?id=${usuariosLista.id}&accion=eliminar">Eliminar</a>
            </td>
        </tr>
            </c:forEach >
    </table>
    </body>
</html>
