package se.guitar_project.miun.appguitarofficial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.w3c.dom.Element;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private static String textContent = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Skapa ett objekt av classen som hämtar xml:en



        Button button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                postTest();
            }
        });
        Button button2= (Button)findViewById(R.id.btn2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getTest();
            }
        });


        /*XmlDocument xmld = test();
        int elementListSize = xmld.getVectorSize();
        setText(""+elementListSize);

        XmlDocument xmld2 = new XmlDocument(xmld.toString(), "customer");
        testPrint(xmld2, "customer");*/
    }


    public void postTest(){
        XmlRestInterface rest = new XmlRestInterface("POST" ,
                new XmlRestInterface.AsyncResponse()
                {
                    //Implementera processFinish som körs när tasken är klar.
                    //Anledningen till att vi överlagrar den här är att vi ska komma åt medlemar här.
                    @Override
                    public void processFinish(String output)
                    {
                        setText(output);
                    }
                });

        rest.setParam(test());
        rest.execute("http://10.0.2.2:8080/web_guitar_official/webresources/beans.timereservation/test");
    }

    public void getTest(){
        XmlRestInterface rest2 = new XmlRestInterface("GET" ,
                new XmlRestInterface.AsyncResponse()
                {
                    //Implementera processFinish som körs när tasken är klar.
                    //Anledningen till att vi överlagrar den här är att vi ska komma åt medlemar här.
                    @Override
                    public void processFinish(String output)
                    {
                        XmlDocument xmlD = new XmlDocument(output, "customer");
                        testPrint(xmlD, "customer");
                    }
                });

        rest2.execute("http://10.0.2.2:8080/web_guitar_official/webresources/beans.customer");

    }


    public XmlDocument test()
    {
        /*EditText nameText = (EditText)findViewById(R.id.textName);
        EditText lastText = (EditText)findViewById(R.id.textLast);
        EditText emailText = (EditText)findViewById(R.id.textEmail);
        EditText phoneText = (EditText)findViewById(R.id.textPhone);
        */
        XmlDocument doc;// = new XmlDocument();
        /*
        XmlElement customer = new XmlElement("customer");
        XmlElement email = new XmlElement("email", emailText.getText().toString());
        XmlElement firstName = new XmlElement("firstName",nameText.getText().toString());
        XmlElement lastName = new XmlElement("lastName", lastText.getText().toString());
        XmlElement phoneNr = new XmlElement("phoneNr", phoneText.getText().toString());*/

        /*
        XmlElement customer = new XmlElement("customer");
        XmlElement email = new XmlElement("email", "ASDF@mail.com");
        XmlElement firstName = new XmlElement("firstName", "qwer");
        XmlElement lastName = new XmlElement("lastName", "lastnameasdf");
        XmlElement phoneNr = new XmlElement("phoneNr", "123");

        XmlElement appointmentIdFk = new XmlElement("appointmentIdFk");
        XmlElement info = new XmlElement("info", "info kommer här");



        XmlElement timeReservationIdFk = new XmlElement("timeReservationIdFk");
        XmlElement reservationDate = new XmlElement("reservationDate", "2017/10/25");
        XmlElement startTime = new XmlElement("startTime", "10:10");
        XmlElement stopTime = new XmlElement("stopTime", "10:10");


        timeReservationIdFk.appendChild(reservationDate);
        timeReservationIdFk.appendChild(startTime);
        timeReservationIdFk.appendChild(stopTime);

        appointmentIdFk.appendChild(timeReservationIdFk);
        appointmentIdFk.appendChild(info);

        customer.appendChild(appointmentIdFk);
        customer.appendChild(email);
        customer.appendChild(firstName);
        customer.appendChild(lastName);
        customer.appendChild(phoneNr);

        doc.appendChild(customer);
        setText(doc.toString());

        */

        doc = new XmlDocument("<timeReservation><reservationDate>1014-10-10T00:00:00+01:00</reservationDate><startTime>1970-01-01T10:10:00+01:00</startTime><stopTime>1970-01-01T11:10:00+01:00</stopTime></timeReservation>",
                "timeResarvation"
        );

        return doc;
    }
    public void setText(String string)
    {
        TextView text = null;
        text = (TextView) findViewById(R.id.textfield);
        text.append("\n"+string);
        //text.setText(string);
    }
    public void testXml(String string)
    {
        XmlParser xml = new XmlParser(string);
        xml.parse();
        setText(xml.getElementByTag("appointment", "appointmentId", 0));
        setText(xml.getElementByTag("appointment", "date", 0));
        setText(xml.getElementByTag("appointment", "startTime", 0));
        setText(xml.getElementByTag("appointment", "stopTime", 0));
        setText(xml.getElementByTag("appointment", "info", 0));
    }

    public void testPrint(XmlDocument doc, String tagName)
    {
        for (int i = 0; i < doc.getVectorSize(); i++)
        {
            setText(doc.getElementByTag(tagName, "firstName", i));
            setText(doc.getElementByTag(tagName, "lastName", i));
            setText(doc.getElementByTag(tagName, "phoneNr", i));
            setText(doc.getElementByTag(tagName, "email", i));
            //setText("");
        }
    }





}