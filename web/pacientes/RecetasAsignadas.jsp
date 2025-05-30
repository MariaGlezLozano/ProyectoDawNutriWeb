<%-- 
    Document   : RecetasAsignadas
    Created on : 24 may 2025, 21:00:19
    Author     : Maria
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Mis Recetas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/estilos/bootstrap.min.css" />
</head>
<body class="container mt-4">
    <h2 class="mb-4">📋 Mis Recetas Asignadas</h2>

    <c:if test="${empty recetas}">
        <p>No tienes recetas asignadas por tu dietista.</p>
    </c:if>

    <c:forEach var="receta" items="${recetas}">
        <div class="card mb-3">
            <div class="card-body">
                <h4 class="card-title">${receta.nombre}</h4>
                <p><strong>Instrucciones:</strong> ${receta.instrucciones}</p>

                <h5>Ingredientes:</h5>
                <ul>
                    <c:forEach var="ing" items="${receta.ingredientes}">
                        <li>${ing}</li>
                    </c:forEach>
                </ul>

                <form method="post" action="${pageContext.request.contextPath}/paciente/ControladorListaCompra">
                    <input type="hidden" name="idReceta" value="${receta.idReceta}" />
                    <button class="btn btn-success">Generar Lista de la Compra</button>
                </form>
            </div>
        </div>
    </c:forEach>

    <a href="${pageContext.request.contextPath}/pacientes/menuPaciente" class="btn btn-secondary"> Volver al Menú</a>
</body>
</html>

