package beans;

import beans.TimeReservation;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-04T10:20:46")
@StaticMetamodel(Leave.class)
public class Leave_ { 

    public static volatile SingularAttribute<Leave, Integer> leaveId;
    public static volatile SingularAttribute<Leave, TimeReservation> timeReservationIdFk;

}