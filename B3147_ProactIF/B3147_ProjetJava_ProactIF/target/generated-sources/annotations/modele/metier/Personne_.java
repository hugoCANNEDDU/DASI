package modele.metier;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-04-29T15:30:17")
@StaticMetamodel(Personne.class)
public abstract class Personne_ { 

    public static volatile SingularAttribute<Personne, Long> IDPersonne;
    public static volatile SingularAttribute<Personne, Double> lng;
    public static volatile SingularAttribute<Personne, String> mail;
    public static volatile SingularAttribute<Personne, String> adresse;
    public static volatile SingularAttribute<Personne, String> mdp;
    public static volatile SingularAttribute<Personne, String> numTel;
    public static volatile SingularAttribute<Personne, Double> lat;

}