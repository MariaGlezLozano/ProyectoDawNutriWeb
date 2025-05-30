/*
 * Entidad cita
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author Maria
 */
@Entity
public class Cita implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCita;
    
     @Column(nullable = false)
    private LocalDateTime fechaHora;
     
     @Transient
private String fechaFormateada;

@Transient
private String horaFormateada;

    @ManyToOne
    @JoinColumn(name = "dietista_id", nullable = false)
    private Dietista dietista;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    public Cita() {
    }

    public Cita(LocalDateTime fechaHora, Dietista dietista, Paciente paciente, EstadoCita estado) {
        this.fechaHora = fechaHora;
        this.dietista = dietista;
        this.paciente = paciente;
        this.estado = estado;
    }
    
    public LocalDate getFecha() {
        return fechaHora.toLocalDate();
    }

    public LocalTime getHora() {
        return fechaHora.toLocalTime();
    }

    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Dietista getDietista() {
        return dietista;
    }

    public void setDietista(Dietista dietista) {
        this.dietista = dietista;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public String getFechaFormateada() {
        return fechaFormateada;
    }

    public void setFechaFormateada(String fechaFormateada) {
        this.fechaFormateada = fechaFormateada;
    }

    public String getHoraFormateada() {
        return horaFormateada;
    }

    public void setHoraFormateada(String horaFormateada) {
        this.horaFormateada = horaFormateada;
    }

    
}
