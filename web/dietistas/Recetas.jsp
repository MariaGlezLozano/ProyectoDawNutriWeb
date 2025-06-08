<%-- 
    Document   : Recetas
    Created on : 24 may 2025, 21:14:05
    Author     : Maria
--%>

<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Crear Receta</title>
        <style>
    body {
        font-family: Arial, sans-serif;
        background-color: rgb(245, 239, 232);
        margin: 0;
        padding: 0 1em;
    }
    h2 {
        margin-top: 0;
    }
    .form-container {
        max-width: 1000px;
        margin: 2em auto;
        background: white;
        padding: 2em;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    input[type="text"], textarea {
        width: 100%;
        box-sizing: border-box;
        padding: 0.5em;
        font-size: 1em;
        border: 1px solid #ccc;
        border-radius: 4px;
        resize: vertical;
    }
    label {
        font-weight: 600;
        display: block;
        margin-bottom: 0.3em;
    }
    .submit-btn {
        margin-top: 1.5em;
        display: inline-block;
        padding: 0.6em 1.5em;
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 1em;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    .submit-btn:hover {
        background-color: #218838;
    }
    button.btn {
        background-color: #6c757d;
        border: none;
        color: white;
        padding: 0.6em 1.5em;
        border-radius: 5px;
        cursor: pointer;
        font-size: 1em;
        margin-left: 1em;
        transition: background-color 0.3s ease;
    }
    button.btn:hover {
        background-color: #5a6268;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 1em;
    }
    table input, table textarea {
        border: none;
        width: 100%;
        padding: 0.3em;
        font-size: 1em;
        font-family: Arial, sans-serif;
    }
    @media (max-width: 768px) {
        .form-container {
            padding: 1em;
            margin: 1em;
        }
    }
</style>

    </head>
    <body>
        <div class="form-container">
            <h2>Registrar Nueva Receta</h2>
            <form method="post" action="${pageContext.request.contextPath}/dietista/ControladorNuevaReceta">
                <input type="hidden" name="idDietista" value="${idDietista}">
                <label for="nombre">Nombre de la receta:</label>
                <input type="text" id="nombre" name="nombre" required><br><br>
                <br>
                <label for="ingredientes">Ingredientes:</label>
                <textarea name="ingredientes" rows="5" style="width:100%;" required></textarea>
                <label>Preparación:</label>
                <textarea name="preparacion" rows="5" style="width:100%;" required></textarea>

                <label for="notas">Notas adicionales:</label><br>
                <textarea name="notas" id="notas" rows="4" style="width: 100%;"></textarea>
                <input type="submit" class="submit-btn" value="Guardar Receta">
                <button class="btn" onclick="window.print()">Imprimir en PDF</button>
            </form>
                <a href="${pageContext.request.contextPath}/dietista/ControladorInicioDietista" class="submit-btn"><i class="fas fa-user-plus"></i>Volver</a>
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

