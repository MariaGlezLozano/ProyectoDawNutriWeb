<%-- 
    Document   : Registro
    Created on : 16 abr 2025, 20:56:39
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
        <title>Registro</title>
    </head>
    <body>
        <jsp:include page="cabecera.jsp" />
        <section class="registro box">
            <h1>Registro Paciente</h1>
            <div class="registro-formularios">
                <form id="formPaciente" action="ControladorRegistro" method="post" novalidate>
                <input type="hidden" name="tipo" value="paciente"/>
                <input type="text" name="nombre" placeholder="Nombre" required />
                <div class="error-message" id="errorNombrePaciente"></div>
                <input type="text" name="apellidos" placeholder="Apellidos" required />
                <div class="error-message" id="errorApellidosPaciente"></div>
                <input type="date" name="fechaNacimiento" placeholder="Fecha de nacimiento" required />
                <div class="error-message" id="errorFechaNacimientoPaciente"></div>
                <input type="email" name="email" placeholder="Correo electrónico" required />
                <div class="error-message" id="errorEmailPaciente"></div>
                <input type="password" name="password" placeholder="Contraseña" required />
                <div class="error-message" id="errorPassPaciente"></div>
                <input type="password" name="repetirPassword" placeholder="Repetir contraseña" required />
                 <div class="error-message" id="errorRepetirPassPaciente"></div>
                <button type="submit" name="registrarse">Registrarse</button>
            </form>
            </div>
        </section>
            <br>
             <section class="registro box">
            <h1>Registro Empresa</h1>   
            <div class="registro-formularios">
                <form id="formEmpresa" action="ControladorRegistro" method="post" novalidate>
                <input type="hidden" name="tipo" value="empresa"/>
                <input type="text" name="nombre" placeholder="Nombre Empresa" required />
                <div class="error-message" id="errorNombreEmpresa"></div>
                <input type="text" name="nif" placeholder="NIF" required />
                <div class="error-message" id="errorNIFEmpresa"></div>
                <input type="text" name="direccion" placeholder="Dirección" required />
                <div class="error-message" id="errorDireccionEmpresa"></div>
                <input type="text" name="profesional" placeholder="Nombre del Dietista" required />
                <div class="error-message" id="errorProfesionalEmpresa"></div>
                <input type="email" name="email" placeholder="Correo electrónico" required />
                <input type="password" name="password" placeholder="Contraseña" required />
                <input type="password" name="repetirPassword" placeholder="Repetir contraseña" required />
                <button type="submit" name="registrarse">Registrarse</button>
            </form>
            </div>
            <br>
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
        </section>
               <jsp:include page="Footer.jsp" /> 
            <script src="${pageContext.request.contextPath}/js/validacion.js"></script>
    </body>

</html>
