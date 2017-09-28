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

        //insert date into textview display selected date
        Calendar c = Calendar.getInstance();
        View v = findViewById(R.id.inputDatum);
        TextView tv = (TextView)v;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        tv.setText(sdf.format(c.getTime()));

        //insert current time into textfields
        View startTimeView = findViewById(R.id.inputStarttid);
        View stopTimeView = findViewById(R.id.inputStoptid);

        TextView startTimeTextView = (TextView)startTimeView;
        TextView stopTimeTextView = (TextView)stopTimeView;

        SimpleDateFormat startTimeSdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat stopTimeSdf = new SimpleDateFormat("HH:mm");

        startTimeTextView.setText(startTimeSdf.format(c.getTime()));
        c.add(Calendar.HOUR, 1);
        stopTimeTextView.setText(stopTimeSdf.format(c.getTime()));

        final EditText subjectText = (EditText) findViewById(R.id.inputBoxSubject);
        final EditText nameText = (EditText) findViewById(R.id.inputBoxName);
        final EditText descriptionText = (EditText) findViewById(R.id.inputBoxDescription);

        btn =  (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amne = subjectText.getText().toString();
                String namn = nameText.getText().toString();
                String beskrivning = descriptionText.getText().toString();

                newAppointment = amne + "\n" + namn + "\n" + beskrivning;
                //we will temporarily only insert into mondays

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

    public void showTimePickerDialogStartTime(View v){
        timePicker.show(getFragmentManager(), "timePicker");
        isStartTime = true;
    }

    public void showTimePickerDialogStopTime(View v){
        timePicker.show(getFragmentManager(), "timePicker");
        isStartTime = false;
    }

    @Override
    public void onComplete(String date) {
        View v = findViewById(R.id.inputDatum);
        TextView tv = (TextView)v;
        tv.setText(date);
    }


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
