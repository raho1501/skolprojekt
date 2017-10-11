package se.guitar_project.miun.retrofittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by linus on 2017-10-08.
 */

public interface RestInterface {
    @GET("beans.timereservation")
    Call<TimeReservations> getAllTimeResarvations();

    @GET("beans.customer")
    Call<Customers> getAllCustomers();
}

