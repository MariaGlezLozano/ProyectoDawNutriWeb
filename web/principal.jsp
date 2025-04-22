<%-- 
    Document   : principal
    Created on : 16 abr 2025, 18:49:59
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
        <title>NutriWeb</title>
    </head>
    <body>
        <jsp:include page="cabecera.jsp" />

        <div class="carrusel">
            <div class="slides fade">
                <img src="imagenes/dieta-saludable.png" alt="Imagen 1">
            </div>
            <div class="slides fade">
                <img src="imagenes/dietaMetro.jpg" alt="Imagen 2">
            </div>
            <div class="slides fade">
                <img src="imagenes/informacion.png" alt="Imagen 3">
            </div>
            <!-- Flechas -->
            <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
            <a class="next" onclick="plusSlides(1)">&#10095;</a>
        </div>

        <!-- Enlaces a los archivos CSS y JS -->
        <link rel="stylesheet" href="estilos/Principal.css">
        <script src="js/Carrusel.js"></script>
        <br>
        <c:if test="${not empty error}">
             <script>
                alert("${error}");
            </script>
            
        </c:if> 
            <%--
        <c:if test="${not empty mensaje}">
            <script>
                alert("${mensaje}");
            </script>
        </c:if>          
            --%>
    </body>
    <jsp:include page="Footer.jsp" />
</html>
