package hamburgermenu.demo.fragments;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by linus on 2017-10-08.
 */

@Root(name = "timeReservation")
public class TimeReservation
{
    @Element(name = "timeReservationId")
    private int timeResarvationId;
    @Element(name = "startTime")
    private String startTime;
    @Element(name = "stopTime")
    private String stopTime;
    @Element(name = "reservationDate")
    private String reservationDate;


    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getTimeResarvationId() {
        return timeResarvationId;
    }

    public void setTimeResarvationId(int timeResarvationId) {
        this.timeResarvationId = timeResarvationId;
    }
}
