<%-- 
    Document   : EvolucionPeso
    Created on : 18 may 2025, 18:52:15
    Author     : Maria
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>

  <title>Gráfica de Evolución del Peso</title>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <style>
    #graficoPeso {
      width: 80vw;
      height: 400px;
      margin: 20px auto;
      display: block;
      border: 1px solid #ccc;
    }
    .btn{
    background-color: #4caf50 !important;
    color: white !important;
    padding: 0.5rem 1rem;
    border: none;
    border-radius: 5px;
    font-weight: bold;
    text-decoration: none;
    margin-right: 0.3rem;
    transition: background-color 0.3s ease;
    display: inline-block;
    margin: 2rem;
    }
    h2{
     text-align: center;
     color: green !important;        
    }
  </style>
</head>
<body>
     <jsp:include page="cabeceraPaciente.jsp" /> 
     <h2>Evolución del Peso</h2>

  <canvas id="graficoPeso"></canvas>

  <script>
    // Datos de ejemplo (los puedes modificar o generar desde tu backend y pasar por URL o fetch)
  const fechas = [
    <c:forEach var="consulta" items="${historialPeso}" varStatus="status">
      '${consulta.fechaConsulta}',<c:if test="${!status.last}"> </c:if>
    </c:forEach>
  ];
   const pesos = [
    <c:forEach var="consulta" items="${historialPeso}" varStatus="status">
      ${consulta.peso},<c:if test="${!status.last}"> </c:if>
    </c:forEach>
  ];

    const ctx = document.getElementById("graficoPeso").getContext("2d");

    new Chart(ctx, {
      type: "line",
      data: {
        labels: fechas,
        datasets: [{
          label: "Peso (kg)",
          data: pesos,
          fill: false,
          borderColor: "green",
          borderWidth: 2,
          tension: 0.1,
          pointRadius: 5
        }]
      },
      options: {
        responsive: true,
        scales: {
          x: {
            title: { display: true, text: "Fecha de consulta" }
          },
          y: {
            title: { display: true, text: "Peso (kg)" },
            beginAtZero: false
          }
        }
      }
    });
  </script>
    <a href="../pacientes/menuPaciente.jsp" class="btn">Volver al menú</a>
</body>
</html>
