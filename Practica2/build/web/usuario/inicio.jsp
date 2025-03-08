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
    <%-- fmt:setBundle se utiliza para la traduccion, la ruta del archivo se pasa a traves de basename
    y var="traduccion" sera la variable que se utilizara para asignar el idioma del texto
    --%>
    <fmt:setBundle var="traduccion" basename="traduccion.traduccion"></fmt:setBundle>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="../estilos/estiloPagina.css">
        <title>Practica2</title>
    </head>
    <body>
        <h1><fmt:message key="hola" bundle="${traduccion}"/> ${usuario.nombre}</h1>
        
        <h2><fmt:message key="experienciasViaje" bundle="${traduccion}"/></h2>
        <%-- Se recorre la lista de experiencias con var="experiencia".
        items="${experienciaViajes}" es el atributo que pasa el controlador al jsp para
        recoger esa lista
        --%>
        <c:forEach var="experiencia" items="${experienciaViajes}">
            
            <%-- Si la experiencia esta publica o pertenece a ese usuario, se mostrará la experiencia --%>
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
                            <%-- Comprueba que la fecha no esté vacía y se le da formato a la fecha --%>
                            <c:if test="${not empty experiencia.fechaInicio}">
                                <fmt:formatDate value="${experiencia.fechaInicio}" pattern="dd-MM-yyyy"/>
                            </c:if>
                        </td>
                        <td>
                            <%-- Si la experiencia de usuario coincide con el usuario en sesion, se mostraran los botones --%>
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
                                <%-- Se recorre la lista de actividad con var="actividad".
                                Las actividades que esten asociadas con la experiencia, se mostrarán
                                --%>
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
                                <%-- Se recorre la lista de opiniones con var="opinion".
                                Las opiniones que esten asociadas con la experiencia, se mostrarán
                                --%>
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
        
        <a href="${pageContext.request.contextPath}/ControladorLogin"><fmt:message key="menu" bundle="${traduccion}"/></a>
        <a href="${pageContext.request.contextPath}/usuario/ControladorInicio?accion=crear"><fmt:message key="crearEx" bundle="${traduccion}"/></a>
        <a href="${pageContext.request.contextPath}/usuario/ControladorConsultaExperiencia"><fmt:message key="filtrarEx" bundle="${traduccion}"/></a>
        <br>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
    </body>
</html>
