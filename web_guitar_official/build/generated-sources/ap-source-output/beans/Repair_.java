package beans;

import beans.TimeReservation;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-04T22:53:04")
@StaticMetamodel(Repair.class)
public class Repair_ { 

    public static volatile SingularAttribute<Repair, Integer> repairId;
    public static volatile SingularAttribute<Repair, TimeReservation> timeReservationIdFk;
    public static volatile SingularAttribute<Repair, String> info;

}