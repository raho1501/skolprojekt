package se.guitar_project.miun.calendertest;

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
    private Customers customers;
    private Appointments appointments;
    private TimeReservations timeReservations;

    public RetrofitWrapper()
    {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/web_guitar_official/webresources/").addConverterFactory(SimpleXmlConverterFactory.create());
        retrofit = builder.build();

        client = retrofit.create(RestInterface.class);
    }
    public List<Event> getEvents()
    {
        List<Event> eventList = new ArrayList<Event>();

        //TODO kod för att hämta event här.

        return eventList;
    }
    public void getCustomers(final RetroCallback<Customers> func)
    {
        Call<Customers> call;
        call = client.getAllCustomers();
        call.enqueue(new Callback<Customers>() {
            @Override
            public void onResponse(Call<Customers> call, Response<Customers> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Customers> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
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
        call.enqueue(new Callback<TimeReservations>() {
            @Override
            public void onResponse(Call<TimeReservations> call, Response<TimeReservations> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<TimeReservations> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
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
        call.enqueue(new Callback<Appointments>() {
            @Override
            public void onResponse(Call<Appointments> call, Response<Appointments> response) {
                func.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Appointments> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
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
}
