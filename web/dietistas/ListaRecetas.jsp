<%-- 
    Document   : ListaRecetas
    Created on : 25 may 2025, 11:52:49
    Author     : Maria
--%>
<%@page import="java.util.List"%>
<%@page import="entidades.Receta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Recetas Creadas</title>
        <link rel="stylesheet" href="../estilos/ListaDietas.css">
    </head>
    <body>
        <div class="contenido">
            <h1>Recetas creadas por el dietista: ${dietista.nombre}</h1>
            <table>
                <tr>
                    <th>Nombre</th>
                    <th>Acciones</th>
                </tr>
                <%
                    List<Receta> recetas = (List<Receta>) request.getAttribute("recetas");
                    if (recetas != null && !recetas.isEmpty()) {
                        for (Receta receta : recetas) {
                %>
                <tr>
                    <td><%= receta.getNombre()%></td>
                    <td>
                      <a href="ControladorEditarDieta?id=<%= receta.getIdReceta() %>" class="btn">Editar receta</a>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="2" class="no-data">No hay recetas registradas.</td>
                </tr>
                <% }%>
            </table>
            <div class="botones">
                <a href="../dietistas/InicioSesionDietista.jsp" class="btn">Volver al menú</a>
            </div>
        </div>
    </body>
</html>
