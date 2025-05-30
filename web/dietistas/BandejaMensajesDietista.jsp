<%-- 
    Document   : BandejaMensajesDietista
    Created on : 27 may 2025, 9:19:02
    Author     : Maria
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Bandeja de entrada</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            vertical-align: top;
        }
        .no-mensajes {
            color: gray;
            font-style: italic;
        }
        .respuesta-form input[type="text"],
        .respuesta-form textarea {
            width: 100%;
            margin: 5px 0;
        }
        @media (max-width: 768px) {
        table, thead, tbody, th, td, tr {
            display: block;
        }

        thead {
            display: none;
        }

        tr {
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            padding: 0.5rem;
            border-radius: 6px;
            background-color: #f9f9f9;
        }

        td {
            border: none;
            padding: 8px 0;
            position: relative;
        }

        td::before {
            content: attr(data-label);
            font-weight: bold;
            display: block;
            color: #333;
            margin-bottom: 4px;
        }

        .respuesta-form button {
            width: 100%;
        }
    }
    </style>
</head>
<body>

<h2>Mensajes recibidos</h2>

<c:if test="${empty mensajes}">
    <p class="no-mensajes">No tienes mensajes por el momento.</p>
</c:if>

<c:if test="${not empty mensajes}">
    <table>
        <tr>
            <th>De (ID)</th>
            <th>Asunto</th>
            <th>Mensaje</th>
            <th>Fecha</th>
            <th>Responder</th>
        </tr>
        <c:forEach var="msg" items="${mensajes}">
            <tr>
                <td>${remitenteNombres[msg.senderId]}</td>
                <td>${msg.subject}</td>
                <td>${msg.body}</td>
                <td>${msg.sentAt}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/dietista/ControladorBandejaDietista" method="post" class="respuesta-form">
                        <input type="hidden" name="receiverId" value="${msg.senderId}" />
                        <input type="hidden" name="replyToId" value="${msg.id}" />
                        <input type="text" name="subject" value="Re: ${msg.subject}" required />
                        <textarea name="body" rows="3" placeholder="Escribe tu respuesta..." required></textarea>
                        <button type="submit">Enviar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
