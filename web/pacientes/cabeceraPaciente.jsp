<%-- 
    Document   : cabeceraPaciente
    Created on : 18 abr 2025, 20:25:26
    Author     : Maria
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>

    <header class="header">
    <title>Menú Paciente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../estilos/cabeceraPaciente.css">
        <div class="logo">
            <a href="ControladorPrincipalPaciente">
                <img src="../imagenes/LogoWeb.png" alt="Logo Nutriweb">
            </a>
        </div>

        <div class="welcome-text">
            Bienvenid@, ${paciente.nombre}
        </div>

        <div class="profile-menu">
            <img src="../imagenes/corazon-saludable.png" alt="Foto de perfil" class="profile-pic">
            <div class="dropdown-content">
                <a href="ControladorCerrarSesion">Cerrar sesión</a>
            </div>
        </div>
    </header>


