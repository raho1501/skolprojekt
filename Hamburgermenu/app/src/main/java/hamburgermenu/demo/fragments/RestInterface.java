package hamburgermenu.demo.fragments;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Albert on 17/10/12.
 */

public interface RestInterface {
    @GET("beans.timereservation")
    Call<TimeReservations> getAllTimeResarvations();

    @GET("beans.timereservation/{id}")
    Call<TimeReservation> getTimeReservationById(@Path("id") int id);

    @POST("beans.timereservation/test")
    Call<TimeReservation> postTimeReservation(@Body TimeReservation timeReservation);

    @DELETE("beans.timereservation/{id}")
    Call<TimeReservation> deleteTimeReservation(@Path("id") int id);

    @GET("beans.customer")
    Call<Customers> getAllCustomers();

    @GET("beans.customer/{id}")
    Call<Customer> getCustomerById(@Path("id") int id);

    @POST("beans.customer/test")
    Call<Customer> postCustomer(@Body Customer customer);

    @DELETE("beans.customer/{id}")
    Call<Customer> deleteCustomer(@Path("id") int id);

    @GET("beans.appointment")
    Call<Appointments> getAllAppointments();

    @GET("beans.appointment/{id}")
    Call<Appointment> getAppointmentById(@Path("id") int id);

    @POST("beans.appointment/test")
    Call<Appointment> postAppointment(@Body Appointment appointment);

    @DELETE("beans.appointment/{id}")
    Call<Appointment> deleteAppointment(@Path("id") int id);

    @GET("beans.leave")
    Call<Leaves> getAllLeaves();

    @GET("beans.leave/{id}")
    Call<Leave> getLeaveById(@Path("id") int id);

    @POST("beans.leave")
    Call<Leave> postLeave(@Body Leave leave);

    @DELETE("beans.leave/{id}")
    Call<Leave> deleteLeave(@Path("id") int id);

    @GET("beans.repair")
    Call<Repairs> getAllRepairs();

    @GET("beans.repair/{id}")
    Call <Repair> getRepairById(@Path("id") int id);

    @POST("beans.repair")
    Call<Repair> postRepair(@Body Repair repair);

    @DELETE("beans.repair/{id}")
    Call<Repair> deleteRepair(@Path("id") int id);

    @Multipart
    @POST("beans.shop/upload")
    Call<ResponseBody> upload(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
    );

    @POST("beans.shop/test")
    Call<Shop> postGuitar(@Body Shop shop);

}
