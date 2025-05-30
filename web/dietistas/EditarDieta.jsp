<%-- 
    Document   : EditarDieta
    Created on : 17 may 2025, 18:34:15
    Author     : Maria
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, java.util.regex.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar Dieta</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 1em;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 0.5em;
            }
            th {
                background-color: #f0f0f0;
            }
            input[type="text"] {
                width: 100%;
                box-sizing: border-box;
            }
            h2 {
                margin-top: 0;
            }
            .form-container {
                max-width: 1000px;
                margin: auto;
            }
            .submit-btn {
                margin-top: 1em;
                display: block;
                padding: 0.6em 1.5em;
            }
        </style>
    </head>
    <body>

        <%
            entidades.Dieta dietaObj = (entidades.Dieta) request.getAttribute("dieta");
            String descripcion = (dietaObj != null) ? dietaObj.getDescripcion() : "";
            Map<String, Map<String, String>> datosDias = new LinkedHashMap<>();
            String notas = "";

            List<String> diasOrdenados = Arrays.asList("lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo");
            for (String dia : diasOrdenados) {
                datosDias.put(dia, new HashMap<>());
            }

            if (descripcion != null && !descripcion.isEmpty()) {
                for (String dia : diasOrdenados) {
                    Pattern bloqueDia = Pattern.compile("(?i)" + dia + ":\\s*([\\s\\S]*?)(?=\\n(?:lunes|martes|miércoles|jueves|viernes|sábado|domingo):|\\nNotas adicionales:|$)", Pattern.CASE_INSENSITIVE);
                    Matcher bloqueMatcher = bloqueDia.matcher(descripcion);

                    if (bloqueMatcher.find()) {
                        String bloque = bloqueMatcher.group(1).trim();
                        String[] lineas = bloque.split("\\n");
                        Map<String, String> comidas = datosDias.get(dia);
                        for (String l : lineas) {
                            if (l.contains(":")) {
                                String[] partes = l.split(":", 2);
                                String comida = partes[0].trim().toLowerCase();
                                String contenido = partes[1].trim();
                                comidas.put(comida, contenido);
                            }
                        }
                    }
                }

                int notasIndex = descripcion.toLowerCase().indexOf("notas adicionales:");
                if (notasIndex != -1) {
                    notas = descripcion.substring(notasIndex + "notas adicionales:".length()).trim();
                }
            }
        %>
        
        <div class="form-container">
            <h2>Editar Dieta</h2>

            <form method="post" action="${pageContext.request.contextPath}/dietista/ControladorEditarDieta">
                <input type="hidden" name="id" value="${dieta.idDieta}">

                <h3>Dieta relizada por: ${dietista.nombre}</h3>
                <label for="nombre">Nombre de la dieta:</label>
                <input type="text" id="nombre" name="nombre" value="${dieta.nombre}" required><br><br>

                <table>
                    <thead>
                        <tr>
                            <th>Día</th>
                            <th>Desayuno</th>
                            <th>Media mañana</th>
                            <th>Comida</th>
                            <th>Merienda</th>
                            <th>Cena</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (String dia : datosDias.keySet()) {
                                Map<String, String> comidas = datosDias.get(dia);
                        %>
                        <tr>
                            <td><%= dia%></td>
                            <td><textarea name="<%= dia%>_desayuno" rows="5" required><%= comidas.getOrDefault("desayuno", "").replace("\n", "&#10;")%></textarea></td>
                            <td><textarea name="<%= dia%>_almuerzo" rows="5" required><%= comidas.getOrDefault("media mañana", "")%></textarea></td>
                            <td><textarea name="<%= dia%>_comida" rows="5" required><%= comidas.getOrDefault("comida", "")%></textarea></td>
                            <td><textarea name="<%= dia%>_merienda" rows="5" required><%= comidas.getOrDefault("merienda", "")%></textarea></td>
                            <td><textarea name="<%= dia%>_cena" rows="5" required><%= comidas.getOrDefault("cena", "")%></textarea></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>

                </table>

                <label for="notas">Notas adicionales:</label><br>
                <textarea name="notas" id="notas" rows="4" style="width: 100%;"><%= notas%></textarea>

                <input type="submit" class="submit-btn" value="Actualizar Dieta">
                <button type="button" class="submit-btn" onclick="window.print()">Imprimir en PDF</button>
                <a href="../dietistas/InicioSesionDietista.jsp" class="btn">Volver</a>
            </form>
        </div>
    </body>
</html>
