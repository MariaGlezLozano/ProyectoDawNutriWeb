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


    public boolean verificarDisponibilidad(LocalDateTime fechaHora, Long idDietista) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(c) FROM Cita c WHERE c.dietista.idDietista = :idDietista AND c.fechaHora = :fechaHora", Long.class);
        query.setParameter("idDietista", idDietista);
        query.setParameter("fechaHora", fechaHora);
        return query.getSingleResult() == 0L;
    }

   public boolean pacienteTieneCitaActiva(Long idPaciente) {
    LocalDateTime ahora = LocalDateTime.now();
    TypedQuery<Long> query = em.createQuery(
        "SELECT COUNT(c) FROM Cita c WHERE c.paciente.idPaciente = :idPaciente AND c.estado = :estado AND c.fechaHora > :ahora", Long.class);
    query.setParameter("idPaciente", idPaciente);
    query.setParameter("estado", EstadoCita.ACTIVA);
    query.setParameter("ahora", ahora);
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

