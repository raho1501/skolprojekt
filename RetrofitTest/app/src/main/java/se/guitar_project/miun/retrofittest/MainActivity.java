package se.guitar_project.miun.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitWrapper retro = new RetrofitWrapper();
        retro.getCustomers(
                new RetroCallback<Customers>()
                {
                    @Override
                    public void onResponse(Customers customers)
                    {
                        int size = customers.size();
                        for (int i = 0; i < size; i++)
                        {
                            appendText("\n" + customers.getCustomer(i).getFirstName());
                        }
                    }
                }
        );
        retro.getTimeReservations(
                new RetroCallback<TimeReservations>()
                {
                    @Override
                    public void onResponse(TimeReservations timeReservations)
                    {
                        int size = timeReservations.size();
                        for (int i = 0; i < size; i++)
                        {
                            appendText("\n" + timeReservations.getTimeReservation(i).getReservationDate());
                        }
                    }
                }
        );
        Customer customer = new Customer();
        customer.setEmail("linus@hotmail.se");
        customer.setFirstName("Linus");
        customer.setLastName("Nilsson");
        customer.setPhoneNr("100000");

        Appointment appointment = new Appointment();
        appointment.setInfo("Tror det funkar!");

        TimeReservation timeReservation = new TimeReservation();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");

        timeReservation.setReservationDate("2017-10-13T00:00:00+02:00");

        timeReservation.setStartTime("1970-01-01T10:00:00+01:00");

        timeReservation.setStopTime("1970-01-01T11:00:00+01:00");


        /*retro.postTimeReservation(timeReservation,
                    new RetroCallback<TimeReservation>()
                    {
                        @Override
                        public void onResponse(TimeReservation timeReservation)
                        {

                            appendText("\n" + timeReservation.getTimeResarvationId());

                        }
                    }
                );
*/
       retro.postInput(customer, timeReservation, appointment);
    }
    public void appendText(String text)
    {
        TextView textView= (TextView) findViewById(R.id.textField);
        textView.append(text);
    }
}
