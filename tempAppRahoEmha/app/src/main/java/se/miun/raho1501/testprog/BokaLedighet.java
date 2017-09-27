package se.miun.raho1501.testprog;

import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

/**
 * Created by Limeman on 9/27/2017.
 */

public class BokaLedighet extends Activity implements DatePickerFragment.OnCompleteListener {

    private DatePickerFragment datePicker = new DatePickerFragment();
    private TimePickerFragment timePicker = new TimePickerFragment();

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

    }

    public void showDatePickerDialog(View v){
        datePicker.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v){
        timePicker.show(getFragmentManager(), "timePicker");
    }

    @Override
    public void onComplete(String date) {
        View v = findViewById(R.id.inputDatum);
        TextView tv = (TextView)v;
        tv.setText(date);
    }
}
