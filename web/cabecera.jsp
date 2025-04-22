<%-- 
    Document   : cabecera
    Created on : 16 abr 2025, 18:54:04
    Author     : Maria
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header class="header">
    <link rel="stylesheet" href="estilos/Cabecera.css">
    <div class="logo">
        <a href="index.html">
            <img src="imagenes/LogoWeb.png" alt="Logo Nutriweb" />
        </a>
    </div>
    <nav class="links">  
        <a href="QuienesSomos.jsp">Quienes somos</a>
        <a href="">Comentarios</a>
        <a href="Servicios.jsp">Servicios</a>
        <a href="">Tarifas</a>
        <a href="Contacto.jsp">Contacto</a>
    </nav>
    <div class="botones">
        <a href="Registro.jsp" class="btn">Registrarse</a>
        <a href="InicioSesion.jsp" class="btn">Iniciar Sesión</a>
        <a href="AdministradorInicio.jsp" class="btn">Administrador</a>
    </div>
</header>

