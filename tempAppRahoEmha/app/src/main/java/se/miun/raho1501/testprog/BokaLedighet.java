package se.miun.raho1501.testprog;

import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Limeman on 9/27/2017.
 */

public class BokaLedighet extends Activity {

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

        
    }

    public void showDatePickerDialog(View v){
        datePicker.show(getFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v){
        timePicker.show(getFragmentManager(), "timePicker");
    }

    }
