package se.guitar_project.miun.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

       Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/web_guitar_official/webresources/").addConverterFactory(SimpleXmlConverterFactory.create());
        Retrofit retrofit = builder.build();

        RestInterface client = retrofit.create(RestInterface.class);
        Call<TimeReservations> call = client.getAllTimeResarvations();

        call.enqueue(new Callback<TimeReservations>() {
            @Override
            public void onResponse(Call<TimeReservations> call, Response<TimeReservations> response) {
                TimeReservations timeReservations= response.body();


                for (int i = 0; i < 3; i++)
                {
                    appendText(String.valueOf(timeReservations.getTimeReservation(i).getTimeResarvationId()));
                }
            }

            @Override
            public void onFailure(Call<TimeReservations> call, Throwable t) {
                appendText("FAIL!");
            }
        });
    }
    public void appendText(String text)
    {
        TextView textView= (TextView) findViewById(R.id.textField);
        textView.append(text);
    }
}
