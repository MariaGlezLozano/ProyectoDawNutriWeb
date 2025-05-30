/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function cargarContenido(url) {
    fetch(url)
        .then(response => response.text())
        .then(html => {
            console.log("HTML recibido desde el servidor:", html);
            document.getElementById("contenido").innerHTML = html;

            const scripts = {
                "ControladorAgenda": [
                    "https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js",
                    "../js/agenda.js"
                ],
                "perfil.jsp": ["../js/perfil.js"],
              
            };

            Object.keys(scripts).forEach(key => {
                if (url.includes(key)) {
                    scripts[key].forEach(src => cargarScript(src, () => {
                        if (src === "../js/agenda.js" && typeof inicializarAgenda === 'function') {
                            inicializarAgenda();
                        }
                    }));
                }
            });
        })
        .catch(err => console.error("Error cargando contenido:", err));
}

function cargarScript(src, onloadCallback) {
    const script = document.createElement("script");
    script.src = src;
    script.onload = onloadCallback || (() => {});
    script.onerror = () => console.error("Error cargando script:", src);
    document.body.appendChild(script);
}

