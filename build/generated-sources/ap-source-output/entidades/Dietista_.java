package entidades;

import entidades.Paciente;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-30T11:10:07", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Dietista.class)
public class Dietista_ { 

    public static volatile SingularAttribute<Dietista, Long> idDietista;
    public static volatile SingularAttribute<Dietista, String> password;
    public static volatile SingularAttribute<Dietista, String> direccion;
    public static volatile SingularAttribute<Dietista, String> profesional;
    public static volatile ListAttribute<Dietista, Paciente> pacientes;
    public static volatile SingularAttribute<Dietista, String> nif;
    public static volatile SingularAttribute<Dietista, String> nombre;
    public static volatile SingularAttribute<Dietista, String> email;
    public static volatile SingularAttribute<Dietista, Boolean> activo;

}