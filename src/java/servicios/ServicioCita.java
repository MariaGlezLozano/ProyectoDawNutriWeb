/*
 * Servicio cita
 */
package servicios;

import entidades.Cita;
import entidades.Dietista;
import entidades.EstadoCita;
import entidades.Paciente;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author Maria
 */
public class ServicioCita {

    private final EntityManager em;

    public ServicioCita(EntityManager em) {
        this.em = em;
    }

    // En todos los métodos, usar directamente "em" sin abrir ni cerrar
    public boolean verificarDisponibilidad(LocalDateTime fechaHora, Long idDietista) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(c) FROM Cita c WHERE c.dietista.idDietista = :idDietista AND c.fechaHora = :fechaHora", Long.class);
        query.setParameter("idDietista", idDietista);
        query.setParameter("fechaHora", fechaHora);
        return query.getSingleResult() == 0L;
    }

    public boolean pacienteTieneCitaActiva(Long idPaciente) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(c) FROM Cita c WHERE c.paciente.idPaciente = :idPaciente AND c.estado = :estado", Long.class);
        query.setParameter("idPaciente", idPaciente);
        query.setParameter("estado", EstadoCita.ACTIVA);
        return query.getSingleResult() > 0;
    }

    public List<Cita> obtenerCitasPaciente(Long idPaciente) {
        TypedQuery<Cita> query = em.createQuery(
                "SELECT c FROM Cita c WHERE c.paciente.idPaciente = :idPaciente", Cita.class);
        query.setParameter("idPaciente", idPaciente);
        return query.getResultList();
    }

    public void asignarCita(LocalDateTime fechaHora, Long idDietista, Long idPaciente) {
        if (!verificarDisponibilidad(fechaHora, idDietista)) {
            throw new IllegalArgumentException("La hora no está disponible para el dietista.");
        }
        if (pacienteTieneCitaActiva(idPaciente)) {
            throw new IllegalArgumentException("El paciente ya tiene una cita activa.");
        }

        Dietista dietista = em.find(Dietista.class, idDietista);
        Paciente paciente = em.find(Paciente.class, idPaciente);
        if (dietista == null || paciente == null) {
            throw new EntityNotFoundException("Dietista o paciente no encontrados.");
        }

        Cita nueva = new Cita();
        nueva.setFechaHora(fechaHora);
        nueva.setDietista(dietista);
        nueva.setPaciente(paciente);
        nueva.setEstado(EstadoCita.ACTIVA);

        em.persist(nueva);
    }
}

/*
    private final EntityManager emf;

    public ServicioCita(EntityManager entityManager) {
        this.emf = entityManager;
    }

    // Verificar si el dietista tiene disponibilidad para una fecha y hora
    public boolean verificarDisponibilidad(LocalDateTime fechaHora, Long idDietista) {
        TypedQuery<Cita> query = emf.createQuery(
             "SELECT c FROM Cita c WHERE c.idDietista = :idDietista AND c.fecha = :fecha AND c.hora = :hora", Cita.class);
        query.setParameter("idDietista", idDietista);
        query.setParameter("fecha", fechaHora.toLocalDate());
        query.setParameter("hora", fechaHora.toLocalTime());

        return query.getResultList().isEmpty();  // Si está vacío, significa que el dietista está libre
    }

    // Obtener todas las citas de un paciente
    public List<Cita> obtenerCitasPaciente(Long idPaciente) {
        TypedQuery<Cita> query = emf.createQuery(
                "SELECT c FROM Cita c WHERE c.idPaciente = :idPaciente", Cita.class);
        query.setParameter("idPaciente", idPaciente);
        return query.getResultList();
    }

    // Verificar si un paciente tiene una cita activa
    public boolean pacienteTieneCitaActiva(Long idPaciente) {
        String jpql = "SELECT c FROM Cita c WHERE c.paciente.id = :idPaciente AND c.estado = :estado";

        TypedQuery<Cita> query = emf.createQuery(jpql, Cita.class);
        query.setParameter("idPaciente", idPaciente);
        query.setParameter("estado", EstadoCita.ACTIVA);

        return !query.getResultList().isEmpty(); // true si ya tiene cita activa
    }

    // Asignar una nueva cita para un paciente y dietista
    public void asignarCita(LocalDateTime fechaHora, Long idDietista, Long idPaciente) {
        // Verificar disponibilidad del dietista
        if (!verificarDisponibilidad(fechaHora, idDietista)) {
            throw new IllegalArgumentException("La hora no está disponible para el dietista.");
        }

        // Verificar si el paciente ya tiene una cita activa
        if (pacienteTieneCitaActiva(idPaciente)) {
            throw new IllegalArgumentException("El paciente ya tiene una cita activa.");
        }

        // Buscar las entidades correspondientes
        Dietista dietista = emf.find(Dietista.class, idDietista);
        Paciente paciente = emf.find(Paciente.class, idPaciente);

        if (dietista == null || paciente == null) {
            throw new IllegalArgumentException("Dietista o paciente no encontrados.");
        }

        Cita nuevaCita = new Cita();
        nuevaCita.setFechaHora(fechaHora);
        nuevaCita.setDietista(dietista);
        nuevaCita.setPaciente(paciente);
        nuevaCita.setEstado(EstadoCita.ACTIVA);

        emf.getTransaction().begin();
        emf.persist(nuevaCita);
        emf.getTransaction().commit();
    }

}
 */
