/*
 * Servicio lista de la compra
 */
package servicios;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Maria
 */
public class ServicioListaCompra {
     private EntityManager em;

    public ServicioListaCompra(EntityManager em) {
        this.em = em;
    }

    public List<String> generarListaCompraSaludable(Long idPaciente) {
       
        List<String> lista = new ArrayList<>();
        lista.add("Brócoli");
        lista.add("Pechuga de pollo");
        lista.add("Avena");
        lista.add("Manzanas");
        lista.add("Yogur natural");
        lista.add("Aceite de oliva");
        return lista;
    }
}
