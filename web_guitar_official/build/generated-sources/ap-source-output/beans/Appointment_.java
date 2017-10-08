package beans;

import beans.Customer;
import beans.TimeReservation;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-06T22:39:59")
@StaticMetamodel(Appointment.class)
public class Appointment_ { 

    public static volatile CollectionAttribute<Appointment, Customer> customerCollection;
    public static volatile SingularAttribute<Appointment, Integer> appointmentId;
    public static volatile SingularAttribute<Appointment, TimeReservation> timeReservationIdFk;
    public static volatile SingularAttribute<Appointment, String> info;

}