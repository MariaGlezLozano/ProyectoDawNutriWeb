<%-- 
    Document   : perfil
    Created on : 19 abr 2025, 17:22:39
    Author     : Maria
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>

<div class="container mt-3">
    <div id="perfilVista">
        <h3 hidden="true">${paciente.id}</h3>
        <h3>Nombre: ${paciente.nombre}</h3>
        <h3>Apellidos: ${paciente.apellidos}</h3>
        <h3>Fecha de Nacimiento: ${paciente.fechaNacimientoFormateada}</h3>
        <h3>email: ${paciente.email}</h3>
        <button class="btn btn-success btn-sm" onclick="mostrarFormulario()">Editar</button>    
    </div>

    <!-- Formulario de edición oculto inicialmente -->
    <div id="perfilEditar" style="display: none;">
        <form method="post" action="ControladorEditarPerfil">
            <input type="hidden" name="id" value="${paciente.id}" />
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



