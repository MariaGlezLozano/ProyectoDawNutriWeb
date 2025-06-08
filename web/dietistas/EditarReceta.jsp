<%-- 
    Document   : EditarReceta
    Created on : 7 jun 2025, 20:02:57
    Author     : Maria
--%>

<%@page import="java.util.List"%>
<%@page import="entidades.Receta"%>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%
    Receta receta = (Receta) request.getAttribute("receta");
    if (receta == null) {
        response.sendRedirect("listaRecetas.jsp");
        return;
    }

    List<String> ingredientes = receta.getIngredientes();
    String ingredientesText = "";
    if (ingredientes != null) {
        ingredientesText = String.join("\n", ingredientes);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Receta</title>
    <link rel="stylesheet" href="../estilos/EditarRecetas.css">   
</head>
<body>
    <div class="contenido">
        <h1>Editar Receta: <%= receta.getNombre() %></h1>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form action="ControladorEditarReceta" method="post">
            <input type="hidden" name="id" value="<%= receta.getIdReceta() %>"/>

            <label for="nombre">Nombre:</label><br/>
            <input type="text" id="nombre" name="nombre" value="<%= receta.getNombre() %>" required/><br/><br/>

            <label for="ingredientes">Ingredientes (uno por línea):</label><br/>
            <textarea id="ingredientes" name="ingredientes" rows="6" cols="50" required><%= ingredientesText %></textarea><br/><br/>

            <label for="instrucciones">Instrucciones:</label><br/>
            <textarea id="instrucciones" name="instrucciones" rows="8" cols="50"><%= receta.getInstrucciones() %></textarea><br/><br/>

            <label for="notas">Notas:</label><br/>
            <textarea id="notas" name="notas" rows="4" cols="50"><%= receta.getNotas() %></textarea><br/><br/>

            <button type="submit" class="btn">Guardar Cambios</button>
        </form>

    </div>
</body>
</html>

