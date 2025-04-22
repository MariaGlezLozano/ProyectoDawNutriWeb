<%-- 
    Document   : editarDietista
    Created on : 18 abr 2025, 18:32:40
    Author     : Maria
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Dietista</title>

      
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card shadow-lg border-0 p-4">
                        <h2 class="text-center mb-4 text-primary">Editar Dietista</h2>
                        <form method="post" action="ControladorEditarUsuarios">
                            <input type="hidden" name="id" value="${id}">
                            <div class="mb-3">
                                <label for="nombre" class="form-label fw-bold">Nombre</label>
                                <input type="text" name="nombre" class="form-control" maxlength="30" value="${nombre}" required>
                            </div>                           
                            <div class="mb-3">
                                <label for="email" class="form-label fw-bold">Email</label>
                                <input type="email" name="email" class="form-control" maxlength="40" value="${email}" required>
                            </div>                      
                            <div class="mb-3">
                                <label for="activo" class="form-label fw-bold">Activo</label>
                                <select name="activo" id="activo" class="form-select">
                                    <option value="true" ${activo ? "selected" : ""}>Sí</option>
                                    <option value="false" ${!activo ? "selected" : ""}>No</option>
                                </select>
                            </div>
                            <div class="d-flex justify-content-between mt-4">
                                <input type="submit" name="editar" value="Actualizar" class="btn btn-success px-3">
                                <input type="submit" name="eliminar" value="Eliminar" class="btn btn-danger px-3" onclick="return confirmarEliminacion()">
                                <a href="ControladorPrincipalAdmin" class="btn btn-secondary px-3">Volver</a>
                            </div>
                        </form>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger text-center mt-3">
                                ${error}
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function confirmarEliminacion() {
                return confirm("¿Estás seguro de que deseas eliminar el usuario?");
            }
        </script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body> 
</html>

