package hamburgermenu.demo.fragments;

import org.simpleframework.xml.Element;

/**
 * Created by Albert on 17/10/16.
 */

public class Leave {

    @Element(name = "leaveId")
    private int leaveId;
    @Element(name = "timeReservationIdFk")
    private int timeReservationIdFk;


    public int getLeaveId() {
        return leaveId;
    }
    public void setLeaveId(int leaveId) { this.leaveId = leaveId; }

    public int getTimeReservationIdFk() {
        return timeReservationIdFk;
    }
    public void setTimeReservationIdFk(int timeReservationIdFk) {
        this.timeReservationIdFk = timeReservationIdFk;
    }

}
