package retrofit;

import org.simpleframework.xml.Element;

/**
 * Created by Albert on 17/10/16.
 */

public class Repair {
    @Element(name = "repairId")
    private int repairId;
    @Element(name = "timeReservationIdFk")
    private int timeReservationIdFk;
    @Element(name = "info")
    private String info;


    public int getRepairId() {
        return repairId;
    }
    public void setRepairId(int repairId) { this.repairId = repairId; }

    public int getTimeReservationIdFk() {
        return timeReservationIdFk;
    }
    public void setTimeReservationIdFk(int timeReservationIdFk) {
        this.timeReservationIdFk = timeReservationIdFk;
    }
    public String getInfo(){ return info; }
    public void setInfo(String info){ this.info = info; }

}
