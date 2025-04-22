/*
 * Servicio Dietista
 */
package servicios;

import entidades.Dietista;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Maria
 */
public class ServicioDietista implements Serializable {

    public ServicioDietista(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dietista dietista) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(dietista);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void edit(Dietista dietista) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(dietista); 
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void destroy(Long id) throws IllegalStateException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Dietista dietista = em.find(Dietista.class, id);

            if (dietista != null) {
                if (dietista.getPacientes() != null && !dietista.getPacientes().isEmpty()) {
                    throw new IllegalStateException("No se puede eliminar el dietista porque tiene pacientes asignados.");
                }

                em.remove(dietista);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Dietista> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Dietista p", Dietista.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Dietista findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Dietista u WHERE u.email = :email", Dietista.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // No se encontró un usuario con ese email
        } finally {
            em.close();
        }
    }

    public List<Dietista> findAllInactivos() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT d FROM Dietista d WHERE d.activo = false", Dietista.class)
                .getResultList();
    }

    public Dietista findById(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Dietista.class, id);
    }

    public void update(Dietista dietista) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(dietista);
        em.getTransaction().commit();
    }
    
     public List<Dietista> findDietistaEntities() {
        return findDietistaEntities(true, -1, -1);
    }
     
     public List<Dietista> findDietistaEntities(int maxResults, int firstResult) {
        return findDietistaEntities(false, maxResults, firstResult);
    }

     private List<Dietista> findDietistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dietista.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
     
    public Dietista validarDietista(String email, String password) {
        List<Dietista> dietistas = findDietistaEntities();
        for (Dietista e : dietistas) {
            if (e.getEmail().equals(email) && e.getPassword().equals(password)) {
                return e;
            }
        }
        return null;
    }
}
