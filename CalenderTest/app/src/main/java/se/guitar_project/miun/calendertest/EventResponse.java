package se.guitar_project.miun.calendertest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linus on 2017-10-16.
 */

public class EventResponse {
    private Customers customers;
    private List<Appointment> appointments = new ArrayList<>();
    private List<TimeReservation> timeReservations = new ArrayList<>();

    void response() {

    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<TimeReservation> getTimeReservations() {
        return timeReservations;
    }

    public void setTimeReservations(List<TimeReservation> timeReservations) {
        this.timeReservations = timeReservations;
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void addTimeReservation(TimeReservation timeReservation) {
        timeReservations.add(timeReservation);
    }

    public Appointment getLastAppointment() {
        return appointments.get(appointments.size() - 1);
    }

    public List<Event> getEventList() {
        List<Event> list = new ArrayList<Event>();
        int sizeCustomer = customers.size();
        int sizeAppointment = appointments.size();
        int sizeTR = timeReservations.size();
        for (int i = 0; i < sizeAppointment && i < sizeTR && i < sizeCustomer; i++) {
            Event event = new Event();
            Customer customer = customers.getCustomer(i);
            Appointment appointment = appointments.get(i);
            TimeReservation timeReservation = timeReservations.get(i);

            event.setName(customer.getFirstName());
            event.setStartTime(timeReservation.getStartTime());
            event.setStopTime(timeReservation.getStopTime());
            event.setDate(timeReservation.getReservationDate());
            event.setInfo(appointment.getInfo());
            event.setEmail(customer.getEmail());
            event.setPhoneNr(customer.getPhoneNr());
            list.add(event);
        }
        return list;
    }
}