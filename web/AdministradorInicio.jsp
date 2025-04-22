<%-- 
    Document   : AdministradorInicio
    Created on : 18 abr 2025, 13:24:55
    Author     : Maria
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilos/Cabecera.css">
        <link rel="stylesheet" href="estilos/Footer.css"> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
        <link rel="stylesheet" href="estilos/Registro.css"> 
        <title>Inicio Sesión Administrador</title>
    </head>
    <body>
       <jsp:include page="cabecera.jsp" />
        <section class="registro box">
            <h1>Inicio de Sesión Administrador</h1>
            <div class="registro-formularios">
                <form action="ControladorInicioAdmin" method="post">              
                    <input type="email" name="email" placeholder="Correo electrónico" required />
                    <input type="password" name="password" placeholder="Contraseña" required />
                    <input type="password" name="repetirPassword" placeholder="Repetir contraseña" required />
                    <button type="submit" name="iniciar">Iniciar Sesión</button>
                </form>
            </div>
        </section> <br>
        <c:if test="${not empty error}">
             <script>
                alert("${error}");
            </script>
        </c:if>
        <c:if test="${not empty mensaje}">
            <script>
                alert("${mensaje}");
            </script>
        </c:if>        
    </body>
</html>
