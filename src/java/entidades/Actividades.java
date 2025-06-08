/*
 * Entidad Actividades
 */
package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Maria
 */
@Entity
public class Actividades {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idActividades;

    @Column(length = 20, nullable = false)
    private String nombre;

    @Column()
    private Double tiempo;

    @ManyToMany(mappedBy = "actividades")
    private List<Paciente> pacientes = new ArrayList<>();

    public Actividades() {
        this.pacientes = new ArrayList<>();
    }

    public List<Paciente> getPacientes() {
        if (pacientes == null) {
            pacientes = new ArrayList<>();
        }
        return pacientes;
    }

    public Long getIdActividades() {
        return idActividades;
    }

    public void setIdActividades(Long idActividades) {
        this.idActividades = idActividades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getTiempo() {
        return tiempo;
    }

    public void setTiempo(Double tiempo) {
        this.tiempo = tiempo;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Actividades that = (Actividades) o;
        return Objects.equals(idActividades, that.idActividades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActividades);
    }

}
