<%-- 
    Document   : InicioSesionDietista
    Created on : 2 may 2025, 12:54:45
    Author     : Maria
--%>

<%@ page import="java.net.URLDecoder" %>
<%
    String mensaje = request.getParameter("mensaje");
    if (mensaje != null) {
%>
<div style="color: green; font-weight: bold;"><%= mensaje%></div>
<%
    }
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Menú Dietista</title>
        <!-- Estilos propios -->
        <link rel="stylesheet" href="../estilos/InicioSesionDietista.css">
        <link rel="stylesheet" href="../estilos/Footer.css"> 
        <link rel="stylesheet" href="../estilos/cabeceraPaciente.css"> 
        <!-- Librerías y estilos externos -->
        <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
        <link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css" rel="stylesheet">


    </head>
    <body>
        <jsp:include page="CabeceraDietista.jsp" />

        <div class="main-container">
            <div class="sidebar">
                <a href="#" onclick="cargarContenido('../dietistas/perfilDietista.jsp')"><i class="fas fa-user"></i> Perfil</a>
                <a href="${pageContext.request.contextPath}/dietista/ControladorPacientes?idDietista=${dietista.idDietista}"><i class="fas fa-users"></i>Ver Pacientes</a>
                <a href="${pageContext.request.contextPath}/dietistas/NuevaDieta.jsp"><i class="fas fa-user-plus"></i> Registrar Dieta</a>                
                <a href="${pageContext.request.contextPath}/dietista/ControladorNuevaDieta"><i class="fas fa-user-plus"></i> Ver Dietas</a>
                <a href="${pageContext.request.contextPath}/dietistas/Recetas.jsp"><i class="fas fa-user-plus"></i> Registrar Receta</a>
                <a href="${pageContext.request.contextPath}/dietista/ControladorNuevaReceta"><i class="fas fa-user-plus"></i> Ver Recetas</a>
                <a href="#" onclick="cargarContenido('${pageContext.request.contextPath}/dietista/ControladorBandejaDietista')"><i class="fas fa-comments"></i> Chat</a>
            </div>

            <div class="contenido" id="contenido">
                <h2>Bienvenido, ${dietista.nombre}</h2>
                <p>Selecciona una opción del menú para comenzar.</p>
            </div>
        </div>

        <jsp:include page="../Footer.jsp" />

        <script src="../js/menuDietista.js"></script>
        <script src="../js/perfil.js"></script>
        <script src="../js/agenda.js"></script>
    </body>


</html>