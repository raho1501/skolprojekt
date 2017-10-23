package hamburgermenu.demo.fragments;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linus on 2017-10-08.
 */
@Root (name = "timeResarvations")
public class TimeReservations {
    @ElementList(name = "timeReservation", inline=true, required = false)
    private List<TimeReservation> timeReservationList  = new ArrayList<>();;

    public TimeReservation getTimeReservation(int index)
    {
        return timeReservationList.get(index);
    }
    public Integer size()
    {
        return timeReservationList.size();
    }
}
