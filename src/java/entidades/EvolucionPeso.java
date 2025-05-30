/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Maria
 */

@Entity
public class EvolucionPeso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvolucion;
    
    @ManyToOne
    private Paciente paciente;
    
    private LocalDate fecha;
    private double peso;

    public Long getIdEvolucion() {
        return idEvolucion;
    }

    public void setIdEvolucion(Long idEvolucion) {
        this.idEvolucion = idEvolucion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
 
    
}
