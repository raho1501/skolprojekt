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
        Call<Customers> call = client.getAllCustomers();

        call.enqueue(new Callback<Customers>() {
            @Override
            public void onResponse(Call<Customers> call, Response<Customers> response) {
                Customers customers= response.body();


                for (int i = 0; i < 1; i++)
                {
                    appendText(String.valueOf(customers.getCustomer(i).getFirstName()));
                }


            }

            @Override
            public void onFailure(Call<Customers> call, Throwable t) {
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
