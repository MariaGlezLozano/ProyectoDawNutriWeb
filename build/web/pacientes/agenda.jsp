<%-- 
    Document   : agenda
    Created on : 19 abr 2025, 17:27:09
    Author     : Maria
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container mt-3">
    <h3 class="mb-3 text-success">🗓️ Mi Agenda</h3>
    <%-- Mensaje de éxito o error --%>
<c:if test="${not empty mensaje}">
    <div class="alert alert-info">${mensaje}</div>
</c:if>

    <div id="calendar"></div>

    <div class="mt-4 text-end">
        <button class="btn btn-success" onclick="mostrarFormularioNuevaCita()">Pedir nueva cita</button>
    </div>

    <div id="formularioCita" style="display: none;" class="mt-3">
        <h5>Nueva Cita</h5>
        <form method="post" action="${pageContext.request.contextPath}/ControladorNuevaCita">
            <input type="hidden" name="id_paciente" value="${paciente.idPaciente}" />
            <label>Fecha:</label>
            <input type="date" name="fecha" required>

            <label>Hora:</label>
            <input type="time" name="hora" required>

            <label>Selecciona dietista:</label>
          
            <select name="id_dietista" required>
                <c:forEach var="dietista" items="${listaDietistas}">
                    <option value="${dietista.idDietista}">${dietista.nombre}</option>
                </c:forEach>
            </select>
            <button type="submit"  name="cita" class="btn btn-success mt-3">Reservar cita</button>
        </form>

    </div>
    <%
    String mensaje = request.getParameter("mensaje");
    if (mensaje != null && !mensaje.isEmpty()) {
%>
        <p><%= mensaje %></p>
<%
    }
%>
</div>
 <script>
  
    const citasDesdeServidor = ${citasDesdeServidor};
</script>

<script src="../js/agenda.js"></script>


