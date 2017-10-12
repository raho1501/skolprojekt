package beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-12T21:06:01")
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile SingularAttribute<Appointment, Integer> appointmentId;
    public static volatile SingularAttribute<Appointment, Integer> timeReservationIdFk;
    public static volatile SingularAttribute<Appointment, String> info;

}