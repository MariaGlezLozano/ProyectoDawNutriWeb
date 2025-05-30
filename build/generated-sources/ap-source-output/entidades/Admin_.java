package entidades;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-05-30T11:10:07", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Admin.class)
public class Admin_ { 

    public static volatile SingularAttribute<Admin, Integer> idAdmin;
    public static volatile SingularAttribute<Admin, String> password;
    public static volatile SingularAttribute<Admin, String> tipo;
    public static volatile SingularAttribute<Admin, String> nombre;
    public static volatile SingularAttribute<Admin, String> email;

}