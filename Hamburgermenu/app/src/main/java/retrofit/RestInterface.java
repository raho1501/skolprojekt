package retrofit;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Albert on 17/10/12.
 */

public interface RestInterface {

    @POST("Upload")
    Call<ResponseBody> upload(@Part("description") RequestBody description, @Part MultipartBody.Part file);

    @GET("beans.timereservation")
    Call<TimeReservations> getAllTimeResarvations();

    @GET("beans.timereservation/{id}")
    Call<TimeReservation> getTimeReservationById(@Path("id") int id);

    @POST("beans.timereservation/test")
    Call<TimeReservation> postTimeReservation(@Body TimeReservation timeReservation);

    @GET("beans.customer")
    Call<Customers> getAllCustomers();

    @GET("beans.customer/{id}")
    Call<Customer> getCustomerById(@Path("id") int id);

    @POST("beans.customer/test")
    Call<Customer> postCustomer(@Body Customer customer);

    @GET("beans.appointment")
    Call<Appointments> getAllAppointments();

    @GET("beans.appointment/{id}")
    Call<Appointment> getAppointmentById(@Path("id") int id);

    @POST("beans.appointment/test")
    Call<Appointment> postAppointment(@Body Appointment appointment);

    @GET("beans.leave")
    Call<Leaves> getAllLeaves();

    @POST("beans.leave")
    Call<Leave> postLeave(@Body Leave leave);

    @GET("beans.repair")
    Call<Repairs> getAllRepairs();

    @POST("beans.repair")
    Call<Repair> postRepair(@Body Repair repair);

    @GET("beans.budget")
    Call<Budgets> getAllBudgets();

}
