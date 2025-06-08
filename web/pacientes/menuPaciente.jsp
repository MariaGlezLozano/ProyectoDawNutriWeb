<%-- 
    Document   : menuPaciente
    Created on : 18 abr 2025, 20:05:44
    Author     : Maria
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Menú Paciente</title>
        <!-- Estilos y librerÃ­as -->
        <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
        <link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/main.min.css" rel="stylesheet">
        <!--  <link rel="stylesheet" href="../estilos/menuPaciente.css"> -->
        <link href="${pageContext.request.contextPath}/estilos/menuPaciente.css" rel="stylesheet">
        <link rel="stylesheet" href="../estilos/Footer.css"> 
        <link rel="stylesheet" href="../estilos/cabeceraPaciente.css"> 

    </head>
    <body>
        <jsp:include page="cabeceraPaciente.jsp" /> 
        <div class="hamburger" onclick="toggleSidebar()">☰</div>
        <div class="main-container">
            <div class="sidebar" style="background-color: rgb(245, 239, 232);">
                <a href="#" onclick="cargarContenido('${pageContext.request.contextPath}/pacientes/perfil.jsp')"><i class="fas fa-user"></i> Perfil</a>
                <a href="#" onclick="cargarContenido('${pageContext.request.contextPath}/paciente/ControladorListaDietas')"><i class="fas fa-utensils"></i> Dietas</a>
                <a href="#" onclick="cargarContenido('${pageContext.request.contextPath}/paciente/ControladorActividadesPaciente')"><i class="fas fa-dumbbell"></i> Actividades</a>
                <a href="#" onclick="cargarContenido('${pageContext.request.contextPath}/ControladorEnviarMensaje?idPaciente=${paciente.idPaciente}')"><i class="fas fa-envelope"></i> Mensajes</a>
               <!-- <a href="#" onclick="cargarContenido('${pageContext.request.contextPath}/paciente/ControladorListaMensajes?idPaciente=${paciente.idPaciente}')"><i class="fas fa-envelope"></i>Ver Mensajes</a>-->
                <a href="#" onclick="cargarContenido('${pageContext.request.contextPath}/ControladorAgenda')"><i class="fas fa-calendar-days"></i> Agenda</a>
                <%-- 
                <a href="#" onclick="cargarContenido('${pageContext.request.contextPath}/pacientes/agenda.jsp')"><i class="fas fa-calendar-days"></i> Agenda</a>
                --%>
                <a href="#" onclick="window.open('${pageContext.request.contextPath}/paciente/ControladorGrafica', '_blank')"><i class="fa-brands fa-think-peaks"></i> Evoluciónn de peso</a>
                <a href="#" onclick="cargarContenido('${pageContext.request.contextPath}/pacientes/CalculadoraIMC.jsp')"><i class="fas fa-calculator"></i> Calculadora IMC</a>

                <%-- 
                 <a href="#" onclick="cargarContenido('${pageContext.request.contextPath}/paciente/ControladorGrafica')"><i class="fa-brands fa-think-peaks"></i> EvoluciÃ³n de peso</a>
                --%>
                <a href="#" onclick="cargarContenido('${pageContext.request.contextPath}/paciente/ControladorListaRecetas')"><i class="fa-solid fa-plate-wheat"></i> Recetas</a>
            </div>

            <div class="contenido" id="contenido">
                <h2> Bienvenid@, ${paciente.nombre}</h2>
                <p>Selecciona una opción del menú para comenzar.</p>
            </div>
        </div>
        <div class="hamburger" onclick="toggleSidebar()">☰</div>
        <jsp:include page="../Footer.jsp" />

        <script src="../js/menuPaciente.js"></script>
        <script src="../js/perfil.js"></script>       
        <script src="../js/agenda.js"></script>
        <script>
            function toggleSidebar() {
                const sidebar = document.querySelector('.sidebar');
                sidebar.classList.toggle('visible');
            }
        </script>

    </body>
</html>
