package se.guitar_project.miun.appguitarofficial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static String textContent = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Skapa ett objekt av classen som hämtar xml:en
        XmlRestInterface rest = new XmlRestInterface(new XmlRestInterface.AsyncResponse()
        {
            //Implementera processFinish som körs när tasken är klar.
            //Anledningen till att vi överlagrar den här är att vi ska komma åt medlemar här.
            @Override
            public void processFinish(String output)
            {
                setText(output);
            }
        });

        rest.execute("http://10.0.2.2:8080/web_guitar_official/webresources/beans.appointment");
    }
    public void setText(String string)
    {
        TextView text = new TextView(this);
        text = (TextView) findViewById(R.id.textfield);
        text.setText(string);
    }
}
