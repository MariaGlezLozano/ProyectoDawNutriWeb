/* 
 * Js para agenda
 */

function inicializarAgenda() {
    const calendarEl = document.getElementById('calendar');
    if (!calendarEl) return;

    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'es',
        height: 550,
        events: obtenerEventos()
    });

    calendar.render();
}

// Esta función devuelve los eventos (citas) que se deben mostrar en el calendario
function obtenerEventos() {
   
    const eventos = [
        {
            title: 'Cita con Dietista',
            start: '2025-04-21',
            color: '#198754'
        },
        {
            title: 'Seguimiento nutricional',
            start: '2025-04-25',
            color: '#20c997'
        }
    ];

    
    if (typeof citasDesdeServidor !== 'undefined' && Array.isArray(citasDesdeServidor)) {
        citasDesdeServidor.forEach(cita => {
            eventos.push({
                title: cita.title,
                start: cita.start,
                color: cita.color || '#007bff' 
            });
        });
    }

    
    if (typeof citaRecienAgendada !== 'undefined' && citaRecienAgendada !== null) {
        eventos.push({
            title: citaRecienAgendada.title,
            start: citaRecienAgendada.start,
            color: citaRecienAgendada.color || '#198754'
        });
    }

    return eventos;
}


window.mostrarFormularioNuevaCita = function () {
    const form = document.getElementById("formularioCita");
    if (form) form.style.display = 'block';
};
