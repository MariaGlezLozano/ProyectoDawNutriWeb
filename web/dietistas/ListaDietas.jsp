<%-- 
    Document   : ListaDietas
    Created on : 14 may 2025, 14:01:54
    Author     : Maria
--%>
<%@page import="java.util.List"%>
<%@page import="entidades.Dieta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dietas Creadas</title>
        <link rel="stylesheet" href="../estilos/ListaDietas.css">
    </head>
    <body>
        <div class="contenido">
            <h1>Dietas creadas por el dietista: ${dietista.nombre}</h1>
            <table>
                <tr>
                    <th>Nombre</th>
                    <th>Acciones</th>
                </tr>
                <%
                    List<Dieta> dietas = (List<Dieta>) request.getAttribute("dietas");
                    if (dietas != null && !dietas.isEmpty()) {
                        for (Dieta dieta : dietas) {
                %>
                <tr>
                    <td><%= dieta.getNombre()%></td>
                    <td>
                      <a href="ControladorEditarDieta?id=<%= dieta.getIdDieta() %>" class="btn">Editar menú</a>
                    </td>

                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="2" class="no-data">No hay dietas registradas.</td>
                </tr>
                <% }%>
            </table>
            <div class="botones">
                <a href="../dietistas/InicioSesionDietista.jsp" class="btn">Volver al menú</a>
            </div>
        </div>
    </body>
</html>
