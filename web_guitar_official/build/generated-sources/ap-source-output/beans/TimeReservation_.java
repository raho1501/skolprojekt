package beans;

import beans.Appointment;
import beans.Leave;
import beans.Repair;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-05T23:06:12")
@StaticMetamodel(TimeReservation.class)
public class TimeReservation_ { 

    public static volatile CollectionAttribute<TimeReservation, Repair> repairCollection;
    public static volatile SingularAttribute<TimeReservation, Integer> timeReservationId;
    public static volatile SingularAttribute<TimeReservation, Date> startTime;
    public static volatile SingularAttribute<TimeReservation, Date> stopTime;
    public static volatile SingularAttribute<TimeReservation, Date> reservationDate;
    public static volatile CollectionAttribute<TimeReservation, Leave> leaveCollection;
    public static volatile CollectionAttribute<TimeReservation, Appointment> appointmentCollection;

}