package hamburgermenu.demo.fragments;

import com.alamkanak.weekview.WeekViewEvent;

/**
 * Created by linus on 2017-10-17.
 */

public class AppointmentEvent extends Event {
    private Customer customer = new Customer();
    private Appointment appointment = new Appointment();

    public AppointmentEvent()
    {
        super.setColor(100,80,3,255);
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    public String getFirstName()
    {
        return customer.getFirstName();
    }
    public void setFirstName(String firstName)
    {
         customer.setFirstName(firstName);
    }
    public String getLastName()
    {
        return customer.getLastName();
    }
    public void setLastName(String lastName)
    {
        customer.setLastName(lastName);
    }
    public String getEmail()
    {
        return customer.getEmail();
    }
    public void setEmail(String email)
    {
        customer.setEmail(email);
    }
    public String getPhoneNr()
    {
        return customer.getPhoneNr();
    }
    public void setPhoneNr(String phoneNr)
    {
        customer.setPhoneNr(phoneNr);
    }
    @Override
    public String getInfo()
    {
        return customer.getFirstName()+
                " " +
                customer.getLastName() +
                "\n" +
                appointment.getInfo() +
                " " +
                customer.getEmail();
    }
    @Override
    public void setInfo(String info)
    {
        appointment.setInfo(info);
    }
    @Override
    public String getTitle()
    {
        return "Konsultation";
    }

}
