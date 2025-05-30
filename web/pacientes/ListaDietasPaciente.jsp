<%-- 
    Document   : ListaDietasPaciente
    Created on : 18 may 2025, 17:19:33
    Author     : Maria
--%>
<%@page import="java.util.List"%>
<%@page import="entidades.Dieta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Dietas</title>
        <link rel="stylesheet" href="../estilos/ListaDietas.css">
    </head>
    <body>
        <div class="contenido">
            <h1>Dietas asignadas a: ${paciente.nombre}</h1>
            <table>
                <tr>
                    <th style="background-color: green; color: white">Nombre</th>
                    <th style="background-color: green; color: white">Descripción</th>
                </tr>
                <%
                    List<Dieta> dietas = (List<Dieta>) request.getAttribute("dietas");
                    if (dietas != null && !dietas.isEmpty()) {
                        for (Dieta dieta : dietas) {
                %>
                <tr>
                    <td><%= dieta.getNombre()%></td>
                     <td><%= dieta.getDescripcion() %></td>
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
        </div>
    </body>
</html>
