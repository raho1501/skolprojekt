package hamburgermenu.demo.fragments;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albert on 17/10/12.
 */

@Root(name = "Appointments")
public class Appointments {

    @ElementList(name = "appointments", inline=true, required = false)
    private List<Appointment> AppointmentList  = new ArrayList<>();;
    public int size(){
        return AppointmentList.size();
    }
    public Appointment getAppointment(int index)
    {
        return AppointmentList.get(index);
    }
}
