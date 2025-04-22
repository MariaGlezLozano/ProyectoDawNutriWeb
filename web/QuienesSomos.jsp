<%-- 
    Document   : QuienesSomos
    Created on : 16 abr 2025, 19:30:29
    Author     : Maria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="estilos/QuienesSomos.css">
        <link rel="stylesheet" href="estilos/Cabecera.css">
        <link rel="stylesheet" href="estilos/Footer.css"> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
        <title>Quienes Somos</title>
    </head>
    <body>
        <jsp:include page="cabecera.jsp" />
        <section class="quienesSomos box">
            <h1>Quienes Somos</h1>
            <div class="contenido">
                <div class="imagen">
                    <img src="imagenes/dieta-saludable.png" alt="Imagen 1" />
                </div>
                <div class="texto">
                    <h1>Somos una empresa dedicada a la nutrición.</h1>
                    <p>
                        Brindamos asesoramiento personalizado y herramientas para mejorar tu salud y bienestar a través de una alimentación equilibrada. 
                        Podrás contactar con tu Nutricionista de confianza a través de nuestra plataforma, pedir cita, visualizar tu lista de la compra,
                        tu evolución de peso y muchas funcionalidades más.
                    </p>
                </div>
            </div>
        </section>

    </body>
    <jsp:include page="Footer.jsp" />
</html>
