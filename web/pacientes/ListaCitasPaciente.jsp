<%-- 
    Document   : ListaCitasPaciente
    Created on : 24 may 2025, 19:26:23
    Author     : Maria
--%>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mis Citas - Agenda</title>
    <!--  <link href="${pageContext.request.contextPath}/estilos/bootstrap.min.css" rel="stylesheet"> -->
     <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/cabeceraPaciente.css"> 
     <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/ListaPacientes.css"> 
    <style>
        body { padding: 20px; }
        table { width: 100%; }
        .agenda-table {
    width: 100%;
    border-collapse: collapse;
    background-color: rgb(245, 239, 232);
    box-shadow: 0 2px 6px rgba(0,0,0,0.1);
    border-radius: 8px;
    overflow: hidden;
}

.agenda-table th,
.agenda-table td {
    padding: 1rem;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

.agenda-table thead {
    background-color: #4caf50;
    color: white;
}

    </style>
</head>
<body>
    <jsp:include page="cabeceraPaciente.jsp" />
    <h2>Agenda de ${paciente.nombre} ${paciente.apellidos}</h2>
    <c:if test="${not empty mensaje}">
        <div class="alert">${mensaje}</div>
    </c:if>

    <table class="agenda-table">
        <thead>
            <tr>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Dietista</th>
                <th>Estado</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cita" items="${citasPaciente}">
                <tr>
                    <td>${cita.fechaFormateada}</td>
                    <td>${cita.horaFormateada}</td>
                    <td>${cita.dietista.nombre}</td>
                    <td>${cita.estado}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty citasPaciente}">
                <tr><td colspan="4" class="text-center">No tienes citas registradas.</td></tr>
            </c:if>
        </tbody>
    </table>
    <form action="${pageContext.request.contextPath}/paciente/ControladorPrincipalPaciente" method="get">
        <button type="submit" class="btn">Volver al Menú</button>
    </form>
</body>
</html>
