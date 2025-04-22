/*
 * Servicio Administrador
 */
package servicios;

import entidades.Admin;
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
public class ServicioAdmin {
     public ServicioAdmin(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Admin admin) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(admin);
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

    public void edit(Admin admin) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(admin); 
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

    public void destroy(int idAdmin) throws IllegalStateException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Admin admin = em.find(Admin.class, idAdmin);

            em.remove(admin);

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

   
    public Admin findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Admin u WHERE u.email = :email", Admin.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // No se encontró un usuario con ese email
        } finally {
            em.close();
        }
    }

  
    public Admin findById(Long idAdmin) {
        EntityManager em = emf.createEntityManager();
        return em.find(Admin.class, idAdmin);
    }

    public void update(Admin admin) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(admin);
        em.getTransaction().commit();
    }
    
     public List<Admin> findAdminEntities() {
        return findAdminEntities(true, -1, -1);
    }
     
     public List<Admin> findAdminEntities(int maxResults, int firstResult) {
        return findAdminEntities(false, maxResults, firstResult);
    }

     private List<Admin> findAdminEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Admin.class));
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
     
    public Admin validarAdmin(String email, String password) {
        List<Admin> admin = findAdminEntities();
        for (Admin e : admin) {
            if (e.getEmail().equals(email) && e.getPassword().equals(password)) {
                return e;
            }
        }
        return null;
    }
}
