<%-- 
    Document   : BandejaMensajes
    Created on : 26 may 2025, 13:06:16
    Author     : Maria
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>Bandeja</title>
    </head>
    <body>

        <h2>Mensajes recibidos</h2>

        <c:if test="${not empty param.enviado}">
            <p style="color:green">Mensaje enviado correctamente.</p>
        </c:if>
        <c:if test="${not empty param.error}">
            <p style="color:red">
                <c:choose>
                    <c:when test="${param.error == 'FaltanParametros'}">
                        Faltan campos obligatorios en el formulario.
                    </c:when>
                    <c:when test="${param.error == 'PacienteNoEncontrado'}">
                        No se encontró el paciente.
                    </c:when>
                    <c:when test="${param.error == 'ReceiverInvalido'}">
                        El destinatario no es válido.
                    </c:when>
                    <c:otherwise>
                       No tiene dietista asignado aún.
                    </c:otherwise>
                </c:choose>
            </p>
        </c:if>

        <c:choose>
            <c:when test="${not empty mensajes}">
                <table border="1" cellpadding="5">
                    <tr>
                        <th>De (ID)</th>
                        <th>Asunto</th>
                        <th>Mensaje</th>
                        <th>Fecha</th>
                        <th>Responder</th>
                    </tr>
                    <c:forEach var="msg" items="${mensajes}">
                        <tr>
                            <td>${msg.senderId}</td>
                            <td>${msg.subject}</td>
                            <td>${msg.body}</td>
                            <td>${msg.sentAt}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/ControladorEnviarMensaje" method="post">
                                    <input type="hidden" name="receiverId" value="${msg.senderId}"/>
                                    <input type="hidden" name="replyToId" value="${msg.id}"/>
                                    <input type="text" name="subject" value="Re: ${msg.subject}" required />
                                    <textarea name="body" placeholder="Tu respuesta..." required></textarea>
                                    <input type="hidden" name="idPaciente" value="${sessionScope.idPaciente}" />
                                    <button type="submit">Enviar</button>
                                </form>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <a href="../pacientes/menuPaciente.jsp" class="btn">Volver al menú</a>
            </c:when>
            <c:otherwise>
                <p>No tienes mensajes.</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
