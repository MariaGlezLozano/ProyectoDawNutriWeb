<%-- 
    Document   : DetalleConsulta
    Created on : 15 may 2025, 9:58:58
    Author     : Maria
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
<head>
    <title>Detalle de la Consulta</title>
      <link rel="stylesheet" href="../estilos/ListaPacientes.css">
</head>
<body>
     <jsp:include page="CabeceraDietista.jsp" />
    <h2>Detalle de Consulta del ${consulta.fechaConsulta}</h2>

    <p><strong>Peso:</strong> ${consulta.peso} kg</p>
    <p><strong>Estatura:</strong> ${consulta.estatura} m</p>

    <p><strong>Dieta asignada:</strong> ${consulta.dieta != null ? consulta.dieta.nombre : "No asignada"}</p>
    <p><strong>Cita asociada:</strong> ${consulta.cita != null ? consulta.cita.fechaCita : "No hay cita asociada"}</p>

    <a href="${pageContext.request.contextPath}/dietista/ControladorHistorialConsultas?idPaciente=${consulta.paciente.idPaciente}">Volver al historial</a>
</body>
</html>
