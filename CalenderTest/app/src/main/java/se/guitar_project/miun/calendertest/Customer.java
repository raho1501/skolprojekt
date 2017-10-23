package se.guitar_project.miun.calendertest;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Anton on 2017-10-11.
 */
@Root (name = "customer")
class Customer {
    @Element(name = "appointmentIdFk", required = false)
    private int appointmentIdFk;
    @Element(name = "customerId")
    private int customerId;
    @Element(name = "email")
    private String email;
    @Element(name = "firstName")
    private String firstName;
    @Element (name = "lastName")
    private String lastName;
    @Element(name = "phoneNr")
    private String phoneNr;


    public int getAppointmentIdFk() {
        return appointmentIdFk;
    }

    public void setAppointmentIdFk(int appointmentIdFk) {
        this.appointmentIdFk = appointmentIdFk;
    }
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }
}
