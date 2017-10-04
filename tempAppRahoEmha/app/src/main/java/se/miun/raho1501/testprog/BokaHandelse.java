package se.miun.raho1501.testprog;

import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Limeman on 9/27/2017.
 */

public class BokaHandelse extends Activity implements DatePickerFragment.OnCompleteListener, TimePickerFragment.OnCompleteTimeListener {

    private DatePickerFragment datePicker = new DatePickerFragment();
    private TimePickerFragment timePicker = new TimePickerFragment();
    private Button btn;
    private String newAppointment;
    protected ArrayAdapter<String> adapter;
    private boolean isStartTime;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boka_handelse);

        Calendar c = Calendar.getInstance();
        TextView tv = (TextView)findViewById(R.id.inputDatum);
        //create desired formatting for displaying the date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        tv.setText(sdf.format(c.getTime()));

        TextView startTimeTextView = (TextView)findViewById(R.id.inputStarttid);
        TextView stopTimeTextView = (TextView)findViewById(R.id.inputStoptid);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        startTimeTextView.setText(timeFormat.format(c.getTime()));
        c.add(Calendar.HOUR, 1);
        stopTimeTextView.setText(timeFormat.format(c.getTime()));

        final EditText subjectText = (EditText) findViewById(R.id.inputBoxSubject);
        final EditText nameText = (EditText) findViewById(R.id.inputBoxName);
        final EditText descriptionText = (EditText) findViewById(R.id.inputBoxDescription);

        btn =  (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //collect data from input fields
                String amne = subjectText.getText().toString();
                String namn = nameText.getText().toString();
                String beskrivning = descriptionText.getText().toString();

                //format input data into a good looking string
                newAppointment = amne + "\n" + namn + "\n" + beskrivning;

                //collect the schedule from monday
                String[] appointment = getResources().getStringArray(R.array.monday_scedule);
                ArrayList<String> appointments = new ArrayList<String>(Arrays.asList(appointment));

                //adapter så att listview kan hämta data från en arraylist
                adapter = new ArrayAdapter<String>(BokaHandelse.this,R.layout.schema_view, R.id.tab1listview, appointments);

                adapter.add(newAppointment);
                adapter.notifyDataSetChanged();

            }
        });

    }



    public void showDatePickerDialog(View v){
        datePicker.show(getFragmentManager(), "datePicker");
    }

    public void showStartTimePickerDialog(View v){
        timePicker.show(getFragmentManager(), "timePicker");
        isStartTime = true;
    }

    public void showStopTimePickerDialog(View v){
        timePicker.show(getFragmentManager(), "timePicker");
        isStartTime = false;
    }

    //when the user hits ok, get the data from the datePickerFragment and
    //set inputDatum's text to the selected date
    @Override
    public void onComplete(String date) {
        View v = findViewById(R.id.inputDatum);
        TextView tv = (TextView)v;
        tv.setText(date);
    }

    //when the user hit's ok in the timePickerFragment collect selected time
    //from the timePickerFragment and set either inputStarttid or inputStoptid's
    //text to the selected time
    @Override
    public void onCompleteTime(String time) {
        if(isStartTime){
            View v = findViewById(R.id.inputStarttid);
            TextView tv = (TextView)v;
            tv.setText(time);
        }
        else{
            View v = findViewById(R.id.inputStoptid);
            TextView tv = (TextView)v;
            tv.setText(time);
        }
    }
}
