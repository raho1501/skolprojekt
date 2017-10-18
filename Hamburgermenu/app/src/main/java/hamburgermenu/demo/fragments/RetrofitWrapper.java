package hamburgermenu.demo.fragments;

import android.os.AsyncTask;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by linus on 2017-10-12.
 */

public class RetrofitWrapper
{
    private Retrofit retrofit;
    private RestInterface client;

    public RetrofitWrapper()
    {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/web_guitar_official/webresources/").addConverterFactory(SimpleXmlConverterFactory.create());
        retrofit = builder.build();

        client = retrofit.create(RestInterface.class);
    }
    public void getEvents(final RetroCallback<List<Event>> func)
    {
        //Retur värdet samt parametervärdet har ingen betydelse.
        AsyncTask<Integer, Integer, List<Event>> task = new AsyncTask<Integer, Integer, List<Event>>() {
            @Override
            protected List<Event> doInBackground(Integer... params) {
                List<TimeReservation> timeReservations = new ArrayList<>();
                List<Appointment> appointments = new ArrayList<>();

                Customers customers = getCustomers();
                int size = customers.size();
                for (int i = 0; i < size; i++)
                {
                    Customer customer = customers.getCustomer(i);
                    Appointment appointment = getAppointmentById(customer.getAppointmentIdFk());
                    TimeReservation timeReservation = getTimeReservationsById(appointment.getTimeReservationIdFk());

                    appointments.add(appointment);
                    timeReservations.add(timeReservation);
                }

                return generateEvents(customers, appointments, timeReservations);
            }
            @Override
            protected void onPostExecute(List<Event> arg){
                func.onResponse(arg);

            }
        };
        task.execute(0);
    }
    public void getRepairEvents(final RetroCallback<List<Event>> func)
    {
        //Retur värdet samt parametervärdet har ingen betydelse.
        AsyncTask<Integer, Integer, List<Event>> task = new AsyncTask<Integer, Integer, List<Event>>() {
            @Override
            protected List<Event> doInBackground(Integer... params) {
                List<TimeReservation> timeReservations = new ArrayList<>();

                Repairs repairs = getRepairs();
                int size = repairs.size();
                for (int i = 0; i < size; i++)
                {
                    Repair repair = repairs.getRepair(i);
                    TimeReservation timeReservation= getTimeReservationsById(repair.getTimeReservationIdFk());

                    timeReservations.add(timeReservation);
                }

                return generateEvents(repairs, timeReservations);
            }
            @Override
            protected void onPostExecute(List<Event> arg){
                func.onResponse(arg);
            }
        };
        task.execute(0);
    }

    private Customers getCustomers()
    {
        Customers customers = new Customers();
        Call<Customers> call;
        call = client.getAllCustomers();
        try
        {
            customers = call.execute().body();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return customers;
    }
    private Customer getCustomerById(int id)
    {
        Customer customer = new Customer();
        Call<Customer> call = client.getCustomerById(id);
        try
        {
            customer = call.execute().body();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return customer;
    }
    public void postCustomer(Customer customer, final RetroCallback<Customer> func)
    {
        Call<Customer> call = client.postCustomer(customer);
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private TimeReservations getTimeReservations()
    {
        TimeReservations timeReservations = new TimeReservations();
        Call<TimeReservations> call;
        call = client.getAllTimeResarvations();
        try
        {
            timeReservations = call.execute().body();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return timeReservations;
    }
    private TimeReservation getTimeReservationsById(int id)
    {
        TimeReservation timeReservation = new TimeReservation();
        Call<TimeReservation> call = client.getTimeReservationById(id);
        try
        {
            timeReservation = call.execute().body();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return timeReservation;
    }
    public void postTimeReservation(TimeReservation timeReservation, final RetroCallback<TimeReservation> func)
    {
        Call<TimeReservation> call = client.postTimeReservation(timeReservation);
        call.enqueue(new Callback<TimeReservation>() {
            @Override
            public void onResponse(Call<TimeReservation> call, Response<TimeReservation> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<TimeReservation> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private Appointments getAppointments()
    {
        Appointments appointments = new Appointments();
        Call<Appointments> call;
        call = client.getAllAppointments();
        try
        {
            appointments = call.execute().body();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return appointments;
    }
    private Appointment getAppointmentById(int id)
    {
        Appointment appointment = new Appointment();
        Call<Appointment> call = client.getAppointmentById(id);
        try
        {
            appointment = call.execute().body();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return appointment;
    }
    public void postAppointment(Appointment appointment, final RetroCallback<Appointment> func)
    {
        Call<Appointment> call = client.postAppointment(appointment);
        call.enqueue(new Callback<Appointment>() {
            @Override
            public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Appointment> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public void postInput(final Customer customer, final TimeReservation timeReservation, final Appointment appointment)
    {
        postTimeReservation(timeReservation,
                new RetroCallback<TimeReservation>()
                {
                    @Override
                    public void onResponse(TimeReservation entity)
                    {
                        int fk = entity.getTimeResarvationId();
                        appointment.setTimeReservationIdFk(fk);
                        postAppointment(appointment,
                                new RetroCallback<Appointment>()
                                {
                                    @Override
                                    public void onResponse(Appointment entity) {
                                        int fk = entity.getAppointmentId();
                                        customer.setAppointmentIdFk(fk);
                                        postCustomer(customer,
                                                new RetroCallback<Customer>()
                                                {
                                                    @Override
                                                    public void onResponse(Customer entity) {

                                                    }
                                                }
                                        );
                                    }
                                }
                        );
                    }
                }

        );
    }

    private void postInput(final Repair repair, TimeReservation timeReservation)
    {
        postTimeReservation(timeReservation,
                new RetroCallback<TimeReservation>()
                {
                    @Override
                    public void onResponse(TimeReservation entity)
                    {
                        int fk = entity.getTimeResarvationId();
                        repair.setTimeReservationIdFk(fk);
                        postRepair(repair,
                                new RetroCallback<Repair>()
                                {
                                    @Override
                                    public void onResponse(Repair entity) {

                                    }
                                }
                        );
                    }
                }

        );
    }

    public void postRepairEvent(RepairEvent event)
    {
        postInput(event.getRepair(), event.getTimeReservation());
    }

    public Repairs getRepairs()
    {
        Repairs repairs = new Repairs();
        Call<Repairs> call;
        call = client.getAllRepairs();
        try
        {
            repairs = call.execute().body();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return repairs;
    }
    public void postRepair(Repair repair, final RetroCallback<Repair> func)
    {
        Call<Repair> call = client.postRepair(repair);
        call.enqueue(new Callback<Repair>() {
            @Override
            public void onResponse(Call<Repair> call, Response<Repair> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Repair> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public Leaves getLeaves()
    {
        Leaves leaves = new Leaves();
        Call<Leaves> call;
        call = client.getAllLeaves();
        try
        {
            leaves = call.execute().body();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return leaves;
    }
    public void postLeave(Leave leave, final RetroCallback<Leave> func)
    {
        Call<Leave> call = client.postLeave(leave);
        call.enqueue(new Callback<Leave>() {
            @Override
            public void onResponse(Call<Leave> call, Response<Leave> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Leave> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private List<Event> generateEvents(Customers customers, List<Appointment> appointments, List<TimeReservation> timeReservations)
    {
        List<Event> eventList = new ArrayList<>();
        int size = customers.size();
        for(int i = 0; i < size; i++)
        {
            Customer customer = customers.getCustomer(i);
            Appointment appointment = appointments.get(i);
            TimeReservation timeReservation = timeReservations.get(i);
            AppointmentEvent event = new AppointmentEvent();
            event.setAppointment(appointment);
            event.setTimeReservation(timeReservation);
            event.setCustomer(customer);
            eventList.add(event);
        }
        return eventList;
    }
    private List<Event> generateEvents(Repairs repairs, List<TimeReservation> timeReservations)
    {
        List<Event> eventList = new ArrayList<>();
        int size = repairs.size();
        for(int i = 0; i < size; i++)
        {
            Repair repair = repairs.getRepair(i);
            TimeReservation timeReservation = timeReservations.get(i);
            RepairEvent event = new RepairEvent();
            event.setRepair(repair);
            event.setTimeReservation(timeReservation);
            eventList.add(event);
        }
        return eventList;
    }
}
