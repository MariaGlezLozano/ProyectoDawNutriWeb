<%-- 
    Document   : Pacientes
    Created on : 3 may 2025, 13:20:08
    Author     : Maria
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <title>Lista de Pacientes</title>
        <link rel="stylesheet" href="../estilos/ListaPacientes.css">
        <link rel="stylesheet" href="../estilos/cabeceraPaciente.css"> 
    </head>
    <body>
        <jsp:include page="CabeceraDietista.jsp" />
        <div class="container">
            <h2>Lista de Pacientes</h2>
            <form method="get" action="${pageContext.request.contextPath}/dietista/ControladorPacientes">
                <input type="hidden" name="idDietista" value="${param.idDietista}">
                <label for="filtro">Buscar Pacientes: </label>
                <input type="text" name="filtro" id="filtro" value="${param.filtro}">
                <button type="submit">Buscar</button>
            </form>
            <c:choose>
                <c:when test="${not empty pacientesFiltrados}">
                    <table border="1">
                        <tr>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>Email</th>
                            <th>Dirección</th>
                            <th>Fecha Nacimiento</th>
                            <th>Peso</th>
                            <th>Altura</th>
                            <th>Acciones</th>
                        </tr>
                        <c:forEach var="paciente" items="${pacientesFiltrados}">
                            <tr>
                                <td>${paciente.nombre}</td>
                                <td>${paciente.apellidos}</td>
                                <td>${paciente.email}</td>
                                <td>${paciente.direccion}</td>
                                <td>${paciente.fechaNacimientoFormateada}</td>
                                <td>${paciente.altura}</td>
                                <td>${paciente.peso}</td> 
                                <td>
                                    <a href="ControladorNuevoPaciente?id=${paciente.idPaciente}" class="btn">Registrar nueva Consulta</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${not empty listaPacientes}">
                    <table>
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Apellidos</th>
                                <th>Email</th>
                                <th>Dirección</th>
                                <th>Fecha Nacimiento</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="paciente" items="${listaPacientes}">
                                <tr>
                                    <td hidden="true">${dietista.idDietista}</td>
                                    <td>${paciente.nombre}</td>
                                    <td>${paciente.apellidos}</td>
                                    <td>${paciente.email}</td>
                                    <td>${paciente.direccion}</td>
                                    <td>${paciente.fechaNacimientoFormateada}</td>                               
                                    <td>
                                        <a href="ControladorNuevaConsulta?id=${paciente.idPaciente}" class="btn">Registrar nueva Consulta</a>                             
                                        <a href="${pageContext.request.contextPath}/dietista/ControladorHistorialConsultas?idPaciente=${paciente.idPaciente}" class="btn">Historial Consultas</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="alert text-center">No hay pacientes registrados.</div>
                </c:otherwise>
            </c:choose>

            <div style="text-align:center; margin-top:2rem;">
                <!-- <a href="${pageContext.request.contextPath}/dietistas/NuevaConsulta.jsp" class="btn">Registrar Nuevo Paciente</a> --> 
                <a href="${pageContext.request.contextPath}/dietistas/InicioSesionDietista.jsp" class="btn">Volver</a>
            </div>
        </div>
    </body>
</html>
