<%-- 
    Document   : NuevaDieta
    Created on : 6 may 2025, 11:36:48
    Author     : Maria
--%>

<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Crear Dieta</title>
    <style>
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
<div class="form-container">
    <h2>Registrar Nueva Dieta</h2>
    <form method="post" action="${pageContext.request.contextPath}/dietista/ControladorNuevaDieta">
        <label for="nombre">Nombre de la dieta:</label>
        <input type="text" id="nombre" name="nombre" required><br><br>

        <table>
            <input type="hidden" name="idPaciente" value="${idPaciente}">
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
                <c:forEach var="dia" items="${['Lunes','Martes','Miércoles','Jueves','Viernes','Sábado','Domingo']}">
                    <tr>
                        <td>${dia}</td>
                        <td><textarea name="${dia.toLowerCase()}_desayuno" rows="5" style="border: none; width:100%;" required></textarea></td>
                        <td><textarea name="${dia.toLowerCase()}_almuerzo" rows="5" style="border: none; width:100%;" required></textarea></td>
                        <td><textarea name="${dia.toLowerCase()}_comida" rows="5" style="border: none; width:100%;" required></textarea></td>
                        <td><textarea name="${dia.toLowerCase()}_merienda" rows="5" style="border: none; width:100%;" required></textarea></td>
                        <td><textarea name="${dia.toLowerCase()}_cena" rows="5" style="border: none; width:100%;" required></textarea> </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <label for="notas">Notas adicionales:</label><br>
        <textarea name="notas" id="notas" rows="4" style="width: 100%;"></textarea>
        <input type="submit" class="submit-btn" value="Guardar Dieta">
         <button class="btn" onclick="window.print()">Imprimir en PDF</button>
    </form>
</div>
            <script>
    function imprimirDieta(id) {
        const originalContent = document.body.innerHTML;
        const dietaDiv = document.getElementById('dieta-' + id).innerHTML;
        document.body.innerHTML = dietaDiv;
        window.print();
        document.body.innerHTML = originalContent;
    }
</script>
</body>
</html>
