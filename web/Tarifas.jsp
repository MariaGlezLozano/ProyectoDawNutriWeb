<%-- 
    Document   : Tarifas
    Created on : 30 may 2025, 6:52:46
    Author     : Maria
--%>
<!DOCTYPE html>
<html lang="es">
<head>
 <%@page contentType="text/html" pageEncoding="UTF-8"%>
  <title>Tarifas NutriWeb</title>
  <link rel="stylesheet" href="estilos/Footer.css"> 
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: rgb(245, 239, 232);
      margin: 0;
      padding: 0;
    }
    .container {
      max-width: 600px;
      margin: 80px auto;
      background-color: white;
      padding: 40px;
      text-align: center;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h1 {
      color: green;
    }
    .price {
      font-size: 48px;
      color: #27ae60;
      margin: 20px 0;
    }
    p {
      font-size: 18px;
      color: #555;
    }
  </style>
</head>
<body>
<jsp:include page="cabecera.jsp" />
  <div class="container">
    <h1>Tarifa para Dietistas</h1>
    <div class="price">60€ / mes</div>
    <p>Accede a todas las funciones premium de nuestra aplicación y gestiona tus clientes de forma eficiente.</p>
  </div>
   <jsp:include page="Footer.jsp" />
</body>
</html>
