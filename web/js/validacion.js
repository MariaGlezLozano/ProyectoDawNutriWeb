/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
document.addEventListener("DOMContentLoaded", function() {

    function validarEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }

    function limpiarErrores(formPrefix) {
        const campos = [
            "Nombre", "Apellidos", "FechaNacimiento", "Email", "Pass", "RepetirPass",
            "NIF", "Direccion", "Profesional"
        ];
        campos.forEach(campo => {
            const el = document.getElementById("error" + campo + formPrefix);
            if(el) el.textContent = "";
        });
    }

    function validarFormulario(formId, formPrefix, event) {
        limpiarErrores(formPrefix);

        const form = document.getElementById(formId);
        let valido = true;

        // Validaciones específicas por formulario
        if(formPrefix === "Paciente") {
            if(form.nombre.value.trim() === "") {
                document.getElementById("errorNombre" + formPrefix).textContent = "El nombre es obligatorio.";
                valido = false;
            }
            if(form.apellidos.value.trim() === "") {
                document.getElementById("errorApellidos" + formPrefix).textContent = "Los apellidos son obligatorios.";
                valido = false;
            }
            if(form.fechaNacimiento.value === "") {
                document.getElementById("errorFechaNacimiento" + formPrefix).textContent = "La fecha de nacimiento es obligatoria.";
                valido = false;
            }
        }
        if(formPrefix === "Empresa") {
            if(form.nombre.value.trim() === "") {
                document.getElementById("errorNombre" + formPrefix).textContent = "El nombre de la empresa es obligatorio.";
                valido = false;
            }
            if(form.nif.value.trim() === "") {
                document.getElementById("errorNIF" + formPrefix).textContent = "El NIF es obligatorio.";
                valido = false;
            }
            if(form.direccion.value.trim() === "") {
                document.getElementById("errorDireccion" + formPrefix).textContent = "La dirección es obligatoria.";
                valido = false;
            }
            if(form.profesional.value.trim() === "") {
                document.getElementById("errorProfesional" + formPrefix).textContent = "El nombre del dietista es obligatorio.";
                valido = false;
            }
        }

        // Validar email
        if (!validarEmail(form.email.value.trim())) {
            document.getElementById("errorEmail" + formPrefix).textContent = "Por favor ingresa un correo válido.";
            valido = false;
        }

        // Validar password
        if(form.password.value.length < 5) {
            document.getElementById("errorPass" + formPrefix).textContent = "La contraseña debe tener al menos 5 caracteres.";
            valido = false;
        }

        if(form.password.value !== form.repetirPassword.value) {
            document.getElementById("errorRepetirPass" + formPrefix).textContent = "Las contraseñas no coinciden.";
            valido = false;
        }

        if(!valido) event.preventDefault();
    }

    document.getElementById("formPaciente").addEventListener("submit", function(event) {
        validarFormulario("formPaciente", "Paciente", event);
    });

    document.getElementById("formEmpresa").addEventListener("submit", function(event) {
        validarFormulario("formEmpresa", "Empresa", event);
    });

});
