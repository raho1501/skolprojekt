package hamburgermenu.demo.fragments;

import org.simpleframework.xml.Element;

/**
 * Created by Albert on 17/10/12.
 */

public class Appointment {
    @Element(name = "appointmentId", required = false)
    private int appointmentId;
    @Element(name = "info")
    private String info;
    @Element(name = "timeReservationIdFk", required = false)
    private int timeReservationIdFk;

    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }

    public int getTimeReservationIdFk() {
        return timeReservationIdFk;
    }
    public void setTimeReservationIdFk(int timeReservationIdFk) {
        this.timeReservationIdFk = timeReservationIdFk;
    }
}
