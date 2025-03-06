<%-- 
    Document   : inicio
    Created on : 26 feb 2025, 10:58:36
    Author     : carlos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <fmt:setBundle var="traduccion" basename="traduccion.traduccion"></fmt:setBundle>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Practica2</title>
    </head>
    <body>
        <h1><fmt:message key="hola" bundle="${traduccion}"/> ${usuario.nombre}</h1>
        
        <h2><fmt:message key="experienciasViaje" bundle="${traduccion}"/></h2>
        <c:forEach var="experiencia" items="${experienciaViajes}">
            <c:if test="${experiencia.publicada == true || experiencia.usuario == usuario}">
                <table border="1">
                    <tr>
                        <th><fmt:message key="titulo" bundle="${traduccion}"/></th>
                        <th><fmt:message key="descripcion" bundle="${traduccion}"/></th>
                        <th><fmt:message key="fecha" bundle="${traduccion}"/></th>
                        <th><fmt:message key="acciones" bundle="${traduccion}"/></th>
                        <th><fmt:message key="creaAct" bundle="${traduccion}"/></th>
                        <th><fmt:message key="tablaAct" bundle="${traduccion}"/></th>
                        <th><fmt:message key="crearComentario" bundle="${traduccion}"/></th>
                        <th><fmt:message key="comentarios" bundle="${traduccion}"/></th>
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
                                <a href="${pageContext.request.contextPath}/usuario/ControladorEditarExperiencia?id=${experiencia.id}"><fmt:message key="editar" bundle="${traduccion}"/></a> 
                                <a href="${pageContext.request.contextPath}/usuario/ControladorInicio?id=${experiencia.id}&accion=eliminar"><fmt:message key="eliminar" bundle="${traduccion}"/></a>
                            </c:if>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/usuario/ControladorCrearActividades?id=${experiencia.id}&accion=crearAct"><fmt:message key="creaAct" bundle="${traduccion}"/></a>
                        </td>
                        <td>
                            <table border="1">
                                <tr>
                                    <th><fmt:message key="titulo" bundle="${traduccion}"/></th>
                                    <th><fmt:message key="fecha" bundle="${traduccion}"/></th>
                                    <th><fmt:message key="descripcion" bundle="${traduccion}"/></th>
                                    <th><fmt:message key="accionesAct" bundle="${traduccion}"/></th>
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
                                            <c:if test="${experiencia.usuario == usuario}">
                                            <a href="${pageContext.request.contextPath}/usuario/ControladorEditarEA?id=${actividad.id}&idEx=${experiencia.id}"><fmt:message key="editar" bundle="${traduccion}"/></a> 
                                            <a href="${pageContext.request.contextPath}/usuario/ControladorActividad?id=${actividad.id}&idEx=${experiencia.id}&accionAct=eliminar"><fmt:message key="eliminar" bundle="${traduccion}"/></a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/usuario/ControladorComentario?idEx=${experiencia.id}"><fmt:message key="crearComentario" bundle="${traduccion}"/></a>
                        </td>
                        <td>
                            <table border="1">
                                <tr>
                                    <th><fmt:message key="usu" bundle="${traduccion}"/></th>
                                    <th><fmt:message key="contenido" bundle="${traduccion}"/></th>
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
        
        <a href="${pageContext.request.contextPath}/usuario/ControladorInicio?accion=crear"><fmt:message key="crearEx" bundle="${traduccion}"/></a>
        <a href="${pageContext.request.contextPath}/usuario/ControladorConsultaExperiencia"><fmt:message key="filtrarEx" bundle="${traduccion}"/></a>
        <br>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
    </body>
</html>
