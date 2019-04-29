package modele.metier;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modele.metier.Intervention;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-29T15:30:17")
@StaticMetamodel(Client.class)
public class Client_ extends Personne_ {

    public static volatile SingularAttribute<Client, Date> dateNaissance;
    public static volatile ListAttribute<Client, Intervention> requetes;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> prenom;
    public static volatile SingularAttribute<Client, String> civilite;

}