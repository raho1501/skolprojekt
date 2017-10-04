package se.miun.raho1501.testprog;

import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Limeman on 9/27/2017.
 * Modified by The Tiny Uncle on 9/27/2017.
 */

public class BokaLedighet extends Activity {

    private DatePickerFragment datePicker = new DatePickerFragment();
    private TimePickerFragment timePicker = new TimePickerFragment();
    private Button btn;
    public String newAppointment;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boka_ledighet);

        //insert date into textview display selected date
        Calendar c = Calendar.getInstance();
        View v = findViewById(R.id.inputDatum);
        TextView tv = (TextView)v;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        tv.setText(sdf.format(c.getTime()));
        //Tiny Uncle stuff
        /*
        Bundle b = getIntent().getExtras();
        String dayTab = b.getString("day");
        */
        //input from textboxes

        final EditText titleText = (EditText) findViewById(R.id.inputBoxDescription);
        final EditText startTimeText = (EditText) findViewById(R.id.inputBoxStartTime);
        final EditText stopTimeText = (EditText) findViewById(R.id.inputBoxStopTime);
        final EditText nameText = (EditText) findViewById(R.id.inputBoxName);
        final EditText descriptionText = (EditText) findViewById(R.id.inputBoxDescription);
        //end
        //add to textarray
        btn = (Button) findViewById(R.id.saveButton);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // this line adds the data of your EditText and puts in your array
                String title = titleText.getText().toString();
                String start = startTimeText.getText().toString();
                String stop = stopTimeText.getText().toString();
                String name = nameText.getText().toString();
                String description = descriptionText.getText().toString();
                newAppointment = title+"\n"+name+"\n"+start+" till "+stop +"\n"+description;
                // next thing you have to do is check if your adapter has changed
                Intent inRes = new Intent();
                inRes.putExtra("result",newAppointment);
                setResult(RESULT_OK,inRes);

                finish();
            }
        });


    }

    public void showDatePickerDialog(View v)
    {
        datePicker.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v)
    {

        timePicker.show(getFragmentManager(), "timePicker");
    }
}


