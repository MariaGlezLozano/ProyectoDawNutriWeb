/* 
 * Js para perfil
 */
   window.mostrarFormulario = function () {
        document.getElementById("perfilVista").style.display = "none";
        document.getElementById("perfilEditar").style.display = "block";
    };

    window.cancelarEdicion = function () {
        document.getElementById("perfilEditar").style.display = "none";
        document.getElementById("perfilVista").style.display = "block";
    };