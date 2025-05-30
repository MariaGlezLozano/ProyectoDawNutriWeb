/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import com.github.javafaker.Faker;
import entidades.Dietista;
import entidades.Paciente;
import java.time.ZoneId;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 *
 * @author Maria
 */
public class datosPrueba {
    public static void insertarDatosFicticios() {
        Faker faker = new Faker();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NutriWebBackendPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Crear un dietista ficticio
        Dietista dietista = new Dietista();
        dietista.setEmail(faker.internet().emailAddress());
        dietista.setPassword("12345");
        dietista.setNombre(faker.name().firstName());
        dietista.setNif(generarDniEspañol());
        dietista.setDireccion(faker.address().fullAddress());
        dietista.setProfesional("Dietista");
        dietista.setActivo(true);

        em.persist(dietista);

        // Crear varios pacientes asociados al dietista
        for (int i = 0; i < 10; i++) {
            Paciente paciente = new Paciente();
            paciente.setEmail(faker.internet().emailAddress());
            paciente.setPassword("1234");
            paciente.setNombre(faker.name().firstName());
            paciente.setApellidos(faker.name().lastName());
            paciente.setFechaNacimiento(
                    faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            );
            paciente.setDietista(dietista);

            em.persist(paciente);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();

        System.out.println("Datos ficticios insertados correctamente.");
    }

    
    // Método para generar un DNI español ficticio
    private static String generarDniEspañol() {
        int numero = (int)(Math.random() * 100000000);  // Generamos un número de 8 dígitos
        char letra = calcularLetraDni(numero);          // Calculamos la letra correspondiente
        return String.format("%08d%c", numero, letra);  // Formateamos y devolvemos el DNI
    }
    
     // Método para calcular la letra del DNI español
    private static char calcularLetraDni(int numero) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";  // Letras del DNI español
        return letras.charAt(numero % 23);  // Obtenemos la letra que corresponde al número
    } 
    
    
    public static void main(String[] args) {
        insertarDatosFicticios();
    }
}
