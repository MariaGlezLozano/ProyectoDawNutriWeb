/*
 * Entidad Paciente
 */
package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Maria
 */
@Entity
public class Paciente {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPaciente;
    @Column(length = 60, nullable = false, unique = true)
    private String email;
    @Column(length = 30, nullable = false)
    private String password;
    @Column(length = 20, nullable = false)
    private String nombre;
    @Column(length = 20, nullable = false)
    private String apellidos;
    @Column
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "dietista_id")
    private Dietista dietista;

    public Dietista getDietista() {
        return dietista;
    }

    public void setDietista(Dietista dietista) {
        this.dietista = dietista;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimientoFormateada() {
        return fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Long getId() {
        return idPaciente;
    }

    public void setId(Long idPaciente) {
        this.idPaciente = idPaciente;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idPaciente);
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
        final Paciente other = (Paciente) obj;
        return Objects.equals(this.idPaciente, other.idPaciente);
    }

    @Override
    public String toString() {
        return "Paciente{" + "id=" + idPaciente + ", email=" + email + ", password=" + password + ", nombre=" + nombre + ", apellidos=" + apellidos + '}';
    }

}
