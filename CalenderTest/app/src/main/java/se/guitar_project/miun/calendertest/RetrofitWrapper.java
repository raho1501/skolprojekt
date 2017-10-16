package se.guitar_project.miun.calendertest;

import android.os.AsyncTask;
import android.os.StrictMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
    private List<Customer> customerList = new ArrayList<Customer>();
    private List<Appointment> appointmentList = new ArrayList<Appointment>();;
    private List<TimeReservation> timeReservationsList = new ArrayList<TimeReservation>();;

    public RetrofitWrapper()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/web_guitar_official/webresources/").addConverterFactory(SimpleXmlConverterFactory.create());
        retrofit = builder.build();

        client = retrofit.create(RestInterface.class);
    }
    public void getEvents(final RetroCallback<List<Event>> func)
    {
        final EventResponse response = new EventResponse();
        List<Event> list = new ArrayList<Event>();


        getCustomers(new RetroCallback<Customers>()
        {
            @Override
            public void onResponse(Customers entity) {
                response.setCustomers(entity);
                Customers customers = response.getCustomers();
                final int size = customers.size();
                for (int i = 0; i < size; i++)
                {
                    final int finalI = i;
                    Customer customer = customers.getCustomer(i);
                    getAppointmentById(customer.getAppointmentIdFk(), new RetroCallback<Appointment>()
                            {
                                @Override
                                public void onResponse(Appointment entity) {
                                    response.addAppointment(entity);
                                    Appointment appointment = response.getLastAppointment();
                                    getTimeReservationsById(appointment.getTimeReservationIdFk(), new RetroCallback<TimeReservation>()
                                            {
                                                @Override
                                                public void onResponse(TimeReservation entity) {
                                                    response.addTimeReservation(entity);
                                                    if(finalI >= size-1)
                                                    {
                                                        func.onResponse(response.getEventList());
                                                    }
                                                }
                                            }
                                    );
                                }
                            }
                    );


                }
            }
        });

    }
    public void getCustomers(final RetroCallback<Customers> func)
    {
        Call<Customers> call;
        call = client.getAllCustomers();
        try
        {
            Customers temp = call.execute().body();
            func.onResponse(temp);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void getCustomerById(int id, final RetroCallback<Customer> func)
    {
        Call<Customer> call = client.getCustomerById(id);
        try
        {
            func.onResponse(call.execute().body());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
    public void getTimeReservations(final RetroCallback<TimeReservations> func)
    {
        Call<TimeReservations> call;
        call = client.getAllTimeResarvations();
        try
        {
            func.onResponse(call.execute().body());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void getTimeReservationsById(int id, final RetroCallback<TimeReservation> func)
    {
        Call<TimeReservation> call = client.getTimeReservationById(id);
        try
        {
            func.onResponse(call.execute().body());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
    public void getAppointments(final RetroCallback<Appointments> func)
    {
        Call<Appointments> call;
        call = client.getAllAppointments();
        try
        {
            func.onResponse(call.execute().body());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void getAppointmentById(int id, final RetroCallback<Appointment> func)
    {
        Call<Appointment> call = client.getAppointmentById(id);
        try
        {
            func.onResponse(call.execute().body());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
    private void addCustomerList(Customer customer)
    {
        customerList.add(customer);
    }
    private void clearCustomerList(Customer customer)
    {
        customerList.clear();
    }
    private void addAppointentList(Appointment appointment)
    {
        appointmentList.add(appointment);
    }
    private void clearAppointentList(Appointment appointment)
    {
        appointmentList.clear();
    }
    private void addTimeReservationList(TimeReservation timeReservation)
    {
        timeReservationsList.add(timeReservation);
    }
    private void clearTimeReservationList(TimeReservation timeReservation)
    {
        timeReservationsList.clear();
    }
    private List<Event> generateEvents()
    {
        List<Event> eventList = new ArrayList<>();
        int size = customerList.size();
        for(int i = 0; i < size; i++)
        {
            Customer customer = customerList.get(i);
            Appointment appointment = appointmentList.get(i);
            TimeReservation timeReservation = timeReservationsList.get(i);
            Event event = new Event();
            event.setName(customer.getFirstName());
            event.setDate(timeReservation.getReservationDate());
            event.setStartTime(timeReservation.getStartTime());
            event.setStopTime(timeReservation.getStopTime());
            eventList.add(event);
        }
        return eventList;
    }
}
