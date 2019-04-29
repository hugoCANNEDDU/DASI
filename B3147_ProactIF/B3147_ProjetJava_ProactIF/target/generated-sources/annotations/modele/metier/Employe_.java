package modele.metier;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modele.metier.Intervention;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-29T15:30:17")
@StaticMetamodel(Employe.class)
public class Employe_ extends Personne_ {

    public static volatile SingularAttribute<Employe, Boolean> disponibilite;
    public static volatile ListAttribute<Employe, Intervention> interventionsClient;
    public static volatile SingularAttribute<Employe, Integer> heureFin;
    public static volatile SingularAttribute<Employe, Integer> heureDepart;

}