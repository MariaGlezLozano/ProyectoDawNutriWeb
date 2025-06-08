<%-- 
    Document   : HistorialConsultas
    Created on : 15 may 2025, 9:47:25
    Author     : Maria
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<html>
<head>
    <title>Historial de Consultas</title>
      <link rel="stylesheet" href="../estilos/ListaPacientes.css">
</head>
<body>
     <jsp:include page="CabeceraDietista.jsp" />
    <h2>Historial de Consultas de: ${paciente.nombre} ${paciente.apellidos}</h2>
    <table border="1">
        <tr>
            <th>Fecha</th>
            <th>Peso</th>
            <th>Estatura</th>
            <th>Ver detalles</th>
        </tr>
        <c:forEach var="consulta" items="${consultas}">
            <tr>
                <td>${consulta.fechaConsulta}</td>
                <td>${consulta.peso}</td>
                <td>${consulta.estatura}</td>
                <td><a href="${pageContext.request.contextPath}/dietista/ControladorDetalleConsulta?idConsulta=${consulta.idConsulta}" class="btn">Ver</a>          
                </td>
            </tr>
        </c:forEach>
    </table>
         <a href="${pageContext.request.contextPath}/dietista/ControladorPacientes?idDietista=${dietista.idDietista}" class="btn">Volver</a>
</body>
</html>
