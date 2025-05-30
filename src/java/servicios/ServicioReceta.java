/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

    public List<Receta> obtenerTodasLasRecetas(){
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
}
