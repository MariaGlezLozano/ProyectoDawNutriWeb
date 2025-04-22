/*
 * Entidad Admin
 */
package entidades;

/**
 *
 * @author Maria
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAdmin;
    @Column(length = 20, nullable = false)
    private String nombre;
    @Column(length = 60, nullable = false, unique = true)
    private String email;
    @Column(length = 30, nullable = false)
    private String password;
    @Column(length = 20, nullable = false)
    private String tipo; // Siempre será "admin"

    public int getId() {
        return idAdmin;
    }

    public void setId(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Admin{"
                + "id=" + idAdmin
                + ", nombre='" + nombre + '\''
                + ", email='" + email + '\''
                + ", tipo='" + tipo + '\''
                + '}';
    }
}
