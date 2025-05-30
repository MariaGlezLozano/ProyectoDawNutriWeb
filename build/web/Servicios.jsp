<%-- 
    Document   : Servicios
    Created on : 16 abr 2025, 19:37:17
    Author     : Maria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilos/Servicios.css">
        <link rel="stylesheet" href="estilos/Cabecera.css">
        <link rel="stylesheet" href="estilos/Footer.css"> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />   
        <title>Servicios</title>
    </head>
    <body>
        <jsp:include page="cabecera.jsp" />
        <section class="servicios box">
            <h1 class="titulo">Servicios</h1>
            <div class="contenidos">
                <div class="imagenes">
                    <img src="imagenes/dietaMetro.jpg" alt="Imagen 1" />
                </div>
                <div class="textos">
                    <h1>Servicio Profesional con Dietista</h1>
                    <p>Ofrecemos un servicio online completo con tu Profesional de confianza.</p>
                </div>
                <div class="imagenes">
                    <img src="imagenes/evolucionPeso.png" alt="Imagen 2" />
                </div>
                <div class="textos">
                    <h1>Visualización de evolución de tu peso</h1>
                    <p>¿Quieres ver una gráfica con la evolución de tu peso? En nuestra Web es posible.</p>
                </div>
                <div class="imagenes">
                    <img src="imagenes/chat.PNG" alt="Imagen 3" />
                </div>
                <div class="textos">
                    <h1>Chat con tu Dietista</h1>
                    <p>Podrás pregunarle tus dudas directamente.</p>
                </div>
                <div class="imagenes">
                    <img src="imagenes/agenda.png" alt="Imagen 4" />
                </div>
                <div class="textos">
                    <h1>Agenda</h1>
                    <p>Podrás coger una cita directamente, sin tener que llamar por teléfono.</p>
                </div>
            </div>
        </section>
    </body>
    <jsp:include page="Footer.jsp" />
</html>
