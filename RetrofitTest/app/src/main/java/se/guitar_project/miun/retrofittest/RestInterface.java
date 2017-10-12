package se.guitar_project.miun.retrofittest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Albert on 17/10/12.
 */

public interface RestInterface {
    @GET("beans.timereservation")
    Call<TimeReservations> getAllTimeResarvations();

    @POST("beans.timereservation/test")
    Call<TimeReservation> postTimeReservation(@Body TimeReservation timeReservation);

    @GET("beans.customer")
    Call<Customers> getAllCustomers();

    @POST("beans.customer/test")
    Call<Customer> postCustomer(@Body Customer customer);

    @GET("beans.appointment")
    Call<Appointments> getAllAppointments();

    @POST("beans.appointment/test")
    Call<Appointment> postAppointment(@Body Appointment appointment);

}
