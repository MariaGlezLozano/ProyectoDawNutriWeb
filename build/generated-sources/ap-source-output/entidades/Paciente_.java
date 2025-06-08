package entidades;

import entidades.Actividades;
import entidades.Consulta;
import entidades.Dietista;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-06-07T21:11:52", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Paciente.class)
public class Paciente_ { 

    public static volatile SingularAttribute<Paciente, String> apellidos;
    public static volatile SingularAttribute<Paciente, Long> idPaciente;
    public static volatile SingularAttribute<Paciente, String> password;
    public static volatile SingularAttribute<Paciente, LocalDate> fechaNacimiento;
    public static volatile SingularAttribute<Paciente, Double> peso;
    public static volatile SingularAttribute<Paciente, Double> altura;
    public static volatile SingularAttribute<Paciente, String> direccion;
    public static volatile ListAttribute<Paciente, Consulta> consultas;
    public static volatile SingularAttribute<Paciente, Dietista> dietista;
    public static volatile ListAttribute<Paciente, Actividades> actividades;
    public static volatile SingularAttribute<Paciente, String> nombre;
    public static volatile SingularAttribute<Paciente, String> email;

}