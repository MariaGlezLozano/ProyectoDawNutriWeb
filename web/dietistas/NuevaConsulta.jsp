<%-- 
    Document   : NuevaConsulta
    Created on : 5 may 2025, 13:00:10
    Author     : Maria
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrar Nueva Consulta</title>
        <link rel="stylesheet" href="../estilos/NuevaConsulta.css">
    </head>
    <body>
        <jsp:include page="CabeceraDietista.jsp" />
        <div class="container">
            <h1>Registrar Nueva Consulta para: ${paciente.nombre} ${paciente.apellidos}</h1>

            <!-- Formulario para registrar la nueva consulta -->
            <form action="ControladorNuevaConsulta" method="post">
                <!-- Información básica del paciente -->
                <input type="hidden" name="idPaciente" value="${paciente.idPaciente}" />
                <input type="hidden" name="idDietista" value="${dietista.idDietista}" />

                <!-- Fecha de la consulta -->
                <h3>Fecha de la Consulta</h3>
                <label for="fechaConsulta">Fecha:</label>
                <input type="date" name="fechaConsulta" value="${fechaActual}" readonly /><br><br>

                <h3>Registrar Peso de Hoy</h3>
                <label for="peso">Peso (kg):</label>
                <input type="number" name="peso" required step="0.1" /><br><br>

                <h3>Registrar Estatura</h3>
                <label for="estatura">Estatura (m):</label>
                <input type="number" name="estatura" required step="0.01" value="${paciente.altura}"/><br><br>

                <h3>Seleccionar Dieta</h3>
                <label for="dieta">Dieta:</label>
                <select name="idDieta">
                    <c:forEach var="dieta" items="${dietas}">
                        <option value="${dieta.idDieta}">${dieta.nombre}</option>
                    </c:forEach>
                </select><br><br>
                
                 <h3>Seleccionar Receta</h3>
                <label for="dieta">Receta:</label>
                <select name="idReceta">
                    <c:forEach var="receta" items="${recetas}">
                        <option value="${receta.idReceta}">${receta.nombre}</option>
                    </c:forEach>
                </select><br><br>
              <!-- Enviar el formulario -->
                <button type="submit">Guardar Consulta</button>
                <a href="${pageContext.request.contextPath}/dietista/ControladorPacientes?idDietista=${dietista.idDietista}" class="btn"><i class="fas fa-user-plus"></i>Ver Pacientes</a>
            </form>

            <a href="${pageContext.request.contextPath}/dietistas/NuevaDieta.jsp" class="btn"><i class="fas fa-user-plus"></i> Registrar Dieta</a>
        </div>
    </body>
</html>
