<%-- 
    Document   : NuevoMensaje
    Created on : 26 may 2025, 13:10:25
    Author     : Maria
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    .chatContenedor {
        max-width: 600px;
        margin: 40px auto;
        border: 1px solid #ccc;
        border-radius: 10px;
        padding: 20px;
        background-color: #f9f9f9;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
        font-family: Arial, sans-serif;
    }

    .chat-header {
        font-weight: bold;
        font-size: 18px;
        margin-bottom: 15px;
        color: #333;
    }

    .chat-input {
        margin-bottom: 15px;
    }

    .chat-input label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
        color: #555;
    }

    .chat-input input[type="text"],
    .chat-input textarea {
        width: 100%;
        padding: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
        resize: none;
        font-size: 14px;
    }

    .chat-button {
        display: block;
        width: 100%;
        background-color: green;
        color: white;
        font-size: 16px;
        border: none;
        padding: 10px;
        border-radius: 5px;
        cursor: pointer;
    }

    .chat-button:hover {
        background-color: #28a745;
    }
</style>

<div class="chatContenedor">
    <form action="${pageContext.request.contextPath}/ControladorEnviarMensaje" method="post">
         <input type="hidden" name="receiverId" value="${dietistaId}" />
         <input type="hidden" name="idPaciente" value="${paciente.idPaciente}" />
         <div class="chat-header">
        <label for="receiverId">Para: ${nombreDietista}</label>
         </div>
      
        <div class="chat-input">
        <label for="subject">Asunto:</label>
        <input type="text" id="subject" name="subject" required>
        </div>
         <div class="chat-input">
        <label for="body">Mensaje:</label>
        <textarea id="body" name="body" rows="5" required></textarea>
        </div>
        <button type="submit" class="chat-button">Enviar</button>
    </form>
</div>

