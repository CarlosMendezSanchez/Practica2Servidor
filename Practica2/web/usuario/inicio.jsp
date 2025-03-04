<%-- 
    Document   : inicio
    Created on : 26 feb 2025, 10:58:36
    Author     : carlos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Practica2</title>
    </head>
    <body>
        <h1>Hola ${usuario.nombre}</h1>
        
        <h2>Experiencias de viaje</h2>
        <c:forEach var="experiencia" items="${experienciaViajes}">
            <c:if test="${experiencia.publicada == true || experiencia.usuario == usuario}">
                <table border="1">
                    <tr>
                        <th>Titulo</th>
                        <th>Descripcion</th>
                        <th>Fecha Inicio</th>
                        <th>Acciones</th>
                        <th>Crear actividad</th>
                        <th>Tabla de actividades</th>
                    </tr>
                    <tr>
                        <td>${experiencia.titulo}</td>
                        <td>${experiencia.descripcion}</td>
                        <td>
                            <c:if test="${not empty experiencia.fechaInicio}">
                                <fmt:formatDate value="${experiencia.fechaInicio}" pattern="dd-MM-yyyy" />
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${experiencia.usuario == usuario}">
                                <a href="../ControladorEditarExperiencia?id=${experiencia.id}">Editar</a> 
                                <a href="ControladorInicio?id=${experiencia.id}&accion=eliminar">Eliminar</a>
                            </c:if>
                        </td>
                        <td>
                            <a href="ControladorActividad?id=${experiencia.id}">Crear actividad</a>
                        </td>
                        <td>
                            <table border="1">
                                <tr>
                                    <th>Titulo</th>
                                    <th>Fecha inicio</th>
                                    <th>Descripcion</th>
                                </tr>
                                <c:forEach var="actividad" items="${experiencia.actividades}">
                                    <tr>
                                        <td>${actividad.titulo}</td>
                                        <td>${actividad.fecha}</td>
                                        <td>${actividad.descripcion}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
            </c:if>
        </c:forEach>
        
        <a href="ControladorInicio?accion=crear">Crear experiencia</a>
    </body>
</html>
