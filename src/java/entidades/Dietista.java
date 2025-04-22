/*
 * Entidad Dietista
 */
package entidades;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Maria
 */
@Entity
public class Dietista {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDietista;
    @Column(length = 60, nullable = false, unique = true)
    private String email;
    @Column(length = 30, nullable = false)
    private String password;
    @Column(length = 20, nullable = false)
    private String nombre;
    @Column(length = 9, unique = true)
    private String nif;
    @Column
    private String direccion;
    @Column(length = 20, nullable = false)
    private String profesional;
    @Column(name = "activo")
    private boolean activo = false; // Por defecto inactiva

    @OneToMany(mappedBy = "dietista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paciente> pacientes;

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public Long getidDietista() {
        return idDietista;
    }

    public void setidDietista(Long idDietista) {
        this.idDietista = idDietista;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProfesional() {
        return profesional;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.idDietista);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dietista other = (Dietista) obj;
        return Objects.equals(this.idDietista, other.idDietista);
    }

    @Override
    public String toString() {
        return "Dietista{" + "idDietista=" + idDietista + ", email=" + email + ", password=" + password + ", nombre=" + nombre + ", nif=" + nif + ", direccion=" + direccion + ", profesional=" + profesional + '}';
    }

}
