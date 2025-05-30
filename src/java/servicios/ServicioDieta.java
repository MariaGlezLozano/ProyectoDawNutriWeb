/*
 * ServicioDieta
 */
package servicios;

import entidades.Dieta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


/**
 *
 * @author Maria
 */
public class ServicioDieta {
    private final EntityManagerFactory emf;

    public ServicioDieta(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Dieta> obtenerTodasLasDietas() {
        EntityManager em = emf.createEntityManager();
        List<Dieta> dietas = em.createQuery("SELECT d FROM Dieta d", Dieta.class).getResultList();
        em.close();
        return dietas;
    }
    
    public List<Dieta> obtenerDietasPorDietista(Long idDietista) {
    EntityManager em = emf.createEntityManager();
    List<Dieta> dietas = em.createQuery(
        "SELECT d FROM Dieta d WHERE d.dietista.idDietista = :id", Dieta.class)
        .setParameter("id", idDietista)
        .getResultList();
    em.close();
    return dietas;
}

}

