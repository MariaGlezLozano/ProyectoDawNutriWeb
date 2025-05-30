<%-- 
    Document   : perfilDietista
    Created on : 17 may 2025, 19:46:26
    Author     : Maria
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>
<link rel="stylesheet" href="../estilos/Perfil.css">
<div class="container">
    <div id="perfilVista">
        <h3 hidden="true">${dietista.idDietista}</h3>
        <h3>Nombre: ${dietista.nombre}</h3>
        <h3>Dirección: ${dietista.direccion}</h3>
        <h3>email: ${dietista.email}</h3>
        <h3>Nif: ${dietista.nif}</h3>
        <button class="btn btn-success btn-sm" onclick="mostrarFormulario()">Editar</button>    
    </div>

    <!-- Formulario de edición oculto inicialmente -->
    <div id="perfilEditar" style="display: none;">
        <form method="post" action="ControladorEditarPerfil">
            <input type="hidden" name="id" value="${paciente.idPaciente}" />
            <div class="mb-2">
                <label>Nombre:</label>
                <input type="text" name="nombre" class="form-control" value="${paciente.nombre}" required />
            </div>
            <div class="mb-2">
                <label>Apellidos:</label>
                <input type="text" name="apellidos" class="form-control" value="${paciente.apellidos}" required />
            </div>
            <div class="mb-2">
                <label>Fecha de Nacimiento:</label>
                <input type="date" name="fechaNacimiento" class="form-control" value="${paciente.fechaNacimiento}" required />
            </div>
            <div class="mb-2">
                <label>Email:</label>
                <input type="email" name="email" class="form-control" value="${paciente.email}" required />
            </div>
            <button type="submit" name="editar" class="btn btn-success btn-sm">Guardar</button>
            <button type="button" class="btn btn-secondary btn-sm" onclick="cancelarEdicion()">Cancelar</button>
        </form>
    </div>
</div>
<script src="../js/perfil.js"></script>
