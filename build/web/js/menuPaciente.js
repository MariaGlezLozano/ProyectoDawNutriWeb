/* 
 * Js para menuPaciente
 */
function cargarContenido(url) {
    fetch(url)
            .then(response => response.text())
            .then(html => {
                console.log("HTML recibido desde el servidor:", html);
                const contenidoDiv = document.getElementById("contenido");
                contenidoDiv.innerHTML = html;


                if (url.includes("ControladorAgenda")) {

                    cargarScript("https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js", () => {
                        cargarScript("../js/agenda.js", () => {
                            if (typeof inicializarAgenda === 'function') {
                                inicializarAgenda();
                            }
                        });
                    });
                }

                if (url.includes("perfil.jsp")) {
                    cargarScript("../js/perfil.js");
                }

                if (url.includes("CalculadoraIMC.jsp")) {
                    cargarScript("../js/calculadora.js");
                }

            })
            .catch(err => console.error("Error cargando contenido:", err));
}

function cargarScript(src, onloadCallback) {
    const script = document.createElement("script");
    script.src = src;
    script.onload = onloadCallback || function () { };
    script.onerror = function () {
        console.error("Error cargando script:", src);
    };
    document.body.appendChild(script);
}


