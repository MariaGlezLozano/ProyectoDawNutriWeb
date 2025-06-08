<%-- 
    Document   : ListaActividades
    Created on : 18 may 2025, 18:05:49
    Author     : Maria
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="entidades.Actividades" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Mis Actividades</title>
              <link rel="stylesheet" href="../estilos/ListaDietas.css">
      
    </head>
    <body>
        <h2>Registrar Actividad Física</h2>
        <form method="post" action="${pageContext.request.contextPath}/paciente/ControladorActividadesPaciente">
            <label for="nombre">Nombre del ejercicio:</label>
            <select id="nombre" name="nombre" required>
                <option value="Correr">Correr</option>
                <option value="Andar">Andar</option>
                <option value="Natacion">Natación</option>
                <option value="Ciclismo">Ciclismo</option>
                <option value="Yoga">Yoga</option>
                <option value="Entrenamiento con pesas">Entrenamiento con pesas</option>
                <option value="Pilates">Pilates</option>
             </select>

            <label for="tiempo">Tiempo (en minutos):</label>
            <input type="number" id="tiempo" name="tiempo" min="1" required>

            <button type="submit">Añadir</button>
        </form>
            <br><br>
        <h2>Actividades Realizadas</h2>
        <%
            List<Actividades> actividades = (List<Actividades>) request.getAttribute("actividades");
            if (actividades == null || actividades.isEmpty()) {
        %>
        <p>No has registrado ninguna actividad todavía.</p>
        <%
        } else {
        %>
        <table>
            <thead>
                <tr>
                    <th style="background-color: green; color: white">Actividad</th>
                    <th style="background-color: green; color: white">Duración (min)</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Actividades a : actividades) {
                %>
                <tr>
                    <td><%= a.getNombre()%></td>
                    <td><%= a.getTiempo()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            }
        %>
    <a href="../pacientes/menuPaciente.jsp" class="btn">Volver al menú</a>
    </body>
</html>
