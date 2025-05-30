<%-- 
    Document   : ListaCompra
    Created on : 24 may 2025, 20:48:09
    Author     : Maria
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de la Compra</title>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

</head>
<body class="container mt-5">
     <jsp:include page="cabeceraPaciente.jsp" /> 
     <div class="text-center mb-4">
    <h2>Lista de la Compra - ${nombreReceta}</h2>

    <ul class="list-group mb-4">
        <c:forEach var="item" items="${ingredientes}">
            <li class="list-group-item">${item}</li>
        </c:forEach>
    </ul>

    <a href="${pageContext.request.contextPath}/pacientes/menuPaciente.jsp" class="btn btn-success">Volver</a>
     </div>
</body>
</html>
