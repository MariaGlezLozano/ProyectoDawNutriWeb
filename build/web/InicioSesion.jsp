<%-- 
    Document   : InicioSesion
    Created on : 18 abr 2025, 12:35:40
    Author     : Maria
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilos/Cabecera.css">
        <link rel="stylesheet" href="estilos/Footer.css"> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
        <link rel="stylesheet" href="estilos/Registro.css"> 
        <title>Inicio Sesión</title>

    </head>
    <body>
        <jsp:include page="cabecera.jsp" />
        <section class="registro box">
            <h1>Inicio de Sesión Paciente</h1>
            <div class="registro-formularios">
                <form id="formPaciente" action="ControladorInicioSesion" method="post" novalidate>
                    <input type="hidden" name="tipo" value="paciente"/>               
                    <input type="email" name="email" placeholder="Correo electrónico" required />
                    <div class="error-message" id="errorEmailPaciente"></div>
                    <input type="password" name="password" placeholder="Contraseña" required />
                    <div class="error-message" id="errorPassPaciente"></div>
                    <input type="password" name="repetirPassword" placeholder="Repetir contraseña" required />
                    <div class="error-message" id="errorRepetirPassPaciente"></div>
                    <button type="submit" name="iniciar">Iniciar Sesión</button>
                </form>
            </div>
        </section>                  
        <br>
        <section class="registro box">
            <h1>Inicio de Sesión Empresa</h1>
            <div class="registro-formularios">
                <form id="formEmpresa" action="ControladorInicioSesion" method="post" novalidate>
                    <input type="hidden" name="tipo" value="empresa"/>            
                    <input type="hidden" name="activo" value="inactivo"/>
                    <input type="email" name="email" placeholder="Correo electrónico" required />
                    <div class="error-message" id="errorEmailEmpresa"></div>
                    <input type="password" name="password" placeholder="Contraseña" required />
                    <div class="error-message" id="errorPassEmpresa"></div>
                    <input type="password" name="repetirPassword" placeholder="Repetir contraseña" required />
                    <div class="error-message" id="errorRepetirPassEmpresa"></div>
                    <button type="submit" name="iniciar">Iniciar Sesión</button>
                </form>
            </div>
        </section>  
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <jsp:include page="Footer.jsp" />
     <script src="${pageContext.request.contextPath}/js/validacion.js"></script>
    </body>
</html>
