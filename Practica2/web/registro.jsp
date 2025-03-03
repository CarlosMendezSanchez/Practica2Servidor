<%-- 
    Document   : registro
    Created on : 1 mar 2025, 12:02:37
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
        <h2>Registro de Usuario</h2>
    
    <form method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>
        <br>
        
        <label for="apellidos">Apellidos:</label>
        <input type="text" id="apellidos" name="apellidos" required>
        <br>
        
        <label for="email">E-mail:</label>
        <input type="email" id="email" name="email" required>
        <br>
        
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" required>
        <br>

        <input type="hidden" id="tipo" name="tipo" value="normal">
        <br>
        
        <button type="submit">Registrarse</button>
        
        <a href="ControladorLogin">Ir a inicio</a>
    </form>
    </body>
</html>
