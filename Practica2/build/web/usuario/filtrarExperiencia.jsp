<%-- 
    Document   : filtrarExperiencia
    Created on : 5 mar 2025, 20:45:17
    Author     : carlos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
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
                        <th>Añadir Comentarios</th>
                        <th>Comentarios</th>
                    </tr>
                    <tr>
                        <td>${experiencia.titulo}</td>
                        <td>${experiencia.descripcion}</td>
                        <td>
                            <c:if test="${not empty experiencia.fechaInicio}">
                                <fmt:formatDate value="${experiencia.fechaInicio}" pattern="dd-MM-yyyy"/>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${experiencia.usuario == usuario}">
                                <a href="${pageContext.request.contextPath}/usuario/ControladorEditarExperiencia?id=${experiencia.id}">Editar</a> 
                                <a href="${pageContext.request.contextPath}/usuario/ControladorInicio?id=${experiencia.id}&accion=eliminar">Eliminar</a>
                            </c:if>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/usuario/ControladorCrearActividades?id=${experiencia.id}&accion=crearAct">Crear actividad</a>
                        </td>
                        <td>
                            <table border="1">
                                <tr>
                                    <th>Titulo</th>
                                    <th>Fecha inicio</th>
                                    <th>Descripcion</th>
                                    <th>Acciones actividades</th>
                                </tr>
                                <c:forEach var="actividad" items="${experiencia.actividades}">
                                    <tr>
                                        <td>${actividad.titulo}</td>
                                        <td>
                                            <c:if test="${not empty actividad.fecha}">
                                                <fmt:formatDate value="${actividad.fecha}" pattern="dd-MM-yyyy"/>
                                            </c:if>
                                        </td>
                                        <td>${actividad.descripcion}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/usuario/ControladorEditarEA?id=${actividad.id}&idEx=${experiencia.id}">Editar</a> 
                                            <a href="${pageContext.request.contextPath}/usuario/ControladorActividad?id=${actividad.id}&idEx=${experiencia.id}&accionAct=eliminar">Eliminar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/usuario/ControladorComentario?idEx=${experiencia.id}">Añadir comentario</a>
                        </td>
                        <td>
                            <table border="1">
                                <tr>
                                    <th>Usuario</th>
                                    <th>Contenido</th>
                                </tr>
                                <c:forEach var="opinion" items="${listaOpiniones}">
                                    <tr>
                                        <c:if test="${opinion.experiencia == experiencia}">
                                        <td>${opinion.usuario}</td>
                                        <td>${opinion.contenido}</td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table>
            </c:if>
        </c:forEach>
        
        <a href="${pageContext.request.contextPath}/usuario/ControladorInicio?accion=crear">Crear experiencia</a>
        <br>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
    </body>
</html>
