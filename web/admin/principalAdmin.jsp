<%-- 
    Document   : principalAdmin
    Created on : 18 abr 2025, 17:11:47
    Author     : Maria
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../estilos/Footer.css"> 
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
        <title>Panel de Administrador</title>

    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <h1 class="mb-4">Menú de Administración</h1>

            <!-- Gestión de Dietistas -->
            <h2 class="mt-4">Gestión de Dietistas</h2>
            <div id="gestionDietistas" class="contenido mt-3">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered text-center">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Email</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dietista" items="${listaDietistas}">
                                <tr>
                                    <td>${dietista.idDietista}</td>
                                    <td>${dietista.nombre}</td>
                                    <td>${dietista.email}</td>
                                    <td>
                                        <a href="/ControladorEditarDietistas?id=${dietista.idDietista}" class="btn btn-warning btn-sm">Editar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Gestión de Pacientes -->
            <h2 class="mt-4">Gestión de Pacientes</h2>
            <div id="gestionPacientes" class="contenido mt-3">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered text-center">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Email</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="paciente" items="${listaPacientes}">
                                <tr>
                                    <td>${paciente.id}</td>
                                    <td>${paciente.nombre}</td>
                                    <td>${paciente.email}</td>
                                    <td>
                                        <a href="ControladorEditarPaciente?id=${paciente.id}" class="btn btn-warning btn-sm">Editar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </form>
    </div>

    <!-- Mensaje -->
    <c:if test="${not empty mensaje}">
        <div class="alert alert-success text-center mt-4">
            ${mensaje}
        </div>
    </c:if>
</div>
<a href="../ControladorPrincipal" class="btn btn-secondary px-3">Volver</a>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
<jsp:include page="/Footer.jsp" />
</html>
