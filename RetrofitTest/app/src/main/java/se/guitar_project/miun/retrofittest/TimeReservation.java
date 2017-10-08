package se.guitar_project.miun.retrofittest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Date;

/**
 * Created by linus on 2017-10-08.
 */

@Root(name = "timeReservation")
public class TimeReservation
{
    @Element(name = "timeReservationId")
    private int timeResarvationId;
    @Element(name = "reservationDate")
    private String reservationDate;
    @Element(name = "startTime")
    private String startTime;
    @Element(name = "stopTime")
    private String stopTime;


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

    public String getreservationDate() {
        return reservationDate;
    }

    public void setreservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getTimeResarvationId() {
        return timeResarvationId;
    }

    public void setTimeResarvationId(int timeResarvationId) {
        this.timeResarvationId = timeResarvationId;
    }
}
