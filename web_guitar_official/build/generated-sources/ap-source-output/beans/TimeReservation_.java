package beans;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-17T10:33:38")
@StaticMetamodel(TimeReservation.class)
public class TimeReservation_ { 

    public static volatile SingularAttribute<TimeReservation, Integer> timeReservationId;
    public static volatile SingularAttribute<TimeReservation, Date> startTime;
    public static volatile SingularAttribute<TimeReservation, Date> stopTime;
    public static volatile SingularAttribute<TimeReservation, Date> reservationDate;

}