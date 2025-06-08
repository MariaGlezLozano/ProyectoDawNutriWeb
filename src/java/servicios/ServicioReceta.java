/*
 * Servicio Receta
 */
package servicios;

import entidades.Receta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Maria
 */
public class ServicioReceta {

    private final EntityManagerFactory emf;

    public ServicioReceta(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Receta> obtenerTodasLasRecetas() {
        EntityManager em = emf.createEntityManager();
        List<Receta> recetas = em.createQuery("SELECT rFROM Receta r", Receta.class).getResultList();
        em.close();
        return recetas;
    }

    public List<Receta> obtenerRecetasAsignadas(Long idPaciente) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT r.receta FROM RecetaAsignada r WHERE r.paciente.id = :id", Receta.class)
                .setParameter("id", idPaciente)
                .getResultList();
    }

    public List<Receta> obtenerRecetasPorDietista(Long idDietista) {
        EntityManager em = emf.createEntityManager();
        List<Receta> recetas = em.createQuery(
                "SELECT r FROM Receta r WHERE r.autor.idDietista = :id", Receta.class)
                .setParameter("id", idDietista)
                .getResultList();
        em.close();
        return recetas;
    }

    public Receta findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Receta receta = null;
        try {
            receta = em.find(Receta.class, id);
        } finally {
            em.close();
        }
        return receta;
    }

    public void update(Receta receta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(receta);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e; // o maneja la excepción como prefieras
        } finally {
            em.close();
        }
    }

}
