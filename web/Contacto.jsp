<%-- 
    Document   : Contacto
    Created on : 16 abr 2025, 19:44:03
    Author     : Maria
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="estilos/Cabecera.css">
        <link rel="stylesheet" href="estilos/Footer.css"> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
        <link rel="stylesheet" href="estilos/Contacto.css">
        <title>Contacto</title>
        <style>
            .mensaje-exito {
                display: none;
                background-color: #d4edda;
                color: #155724;
                padding: 10px;
                border: 1px solid #c3e6cb;
                margin-top: 20px;
            }
        </style>

    </head>
    <body>
        <jsp:include page="cabecera.jsp" />
        <section class="contactos box">
            <h1 class="titulo-contacto">Contacto</h1>

            <div class="fila-contacto">
                <img src="imagenes/telefono.jpg" alt="Teléfono" />
                <h2>Teléfono de contacto: 954 160 861</h2>
            </div>
            <div class="fila-contacto">
                <img src="imagenes/email.png" alt="Email" />
                <h2>Email: nutriWeb@gmail.com</h2>
            </div>

            <form class="formulario-contacto" action="${pageContext.request.contextPath}/ControladorContacto" method="POST">
                <h2>Formulario de contacto</h2>

                <input type="text" name="nombre" placeholder="Tu nombre" required />
                <input type="email" name="email" placeholder="Tu email" required />
                <textarea name="mensaje" placeholder="Tu mensaje" required ></textarea>
                <button type="submit">Enviar</button>
            </form>
        </section>

        <% if (request.getAttribute("mensajeExito") != null) {%>
        <div style="color: green; margin-top: 1rem;">
            <%= request.getAttribute("mensajeExito")%>
        </div>
        <% }%>
    </body>
    <jsp:include page="Footer.jsp" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</html>

