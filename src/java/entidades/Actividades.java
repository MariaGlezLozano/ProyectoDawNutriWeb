/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.ArrayList;
import java.util.List;
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
    private List<Paciente> pacientes = new ArrayList<>(); // Inicializamos la lista

    public Actividades() {
        this.pacientes = new ArrayList<>(); // Evita NullPointerException
    }

    public List<Paciente> getPacientes() {
        if (pacientes == null) {
            pacientes = new ArrayList<>(); // Inicializar si está en null
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

}
