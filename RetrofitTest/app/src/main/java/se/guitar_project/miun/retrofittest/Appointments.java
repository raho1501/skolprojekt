package se.guitar_project.miun.retrofittest;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Albert on 17/10/12.
 */

@Root(name = "Appointments")
public class Appointments {

    @ElementList(name = "appointments", inline=true)
    private List<Appointment> AppointmentList;
    public int size(){
        return AppointmentList.size();
    }
    public Appointment getAppointment(int index)
    {
        return AppointmentList.get(index);
    }
}
