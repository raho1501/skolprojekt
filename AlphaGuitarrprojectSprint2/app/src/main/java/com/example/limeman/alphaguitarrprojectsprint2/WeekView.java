package com.example.limeman.alphaguitarrprojectsprint2;

import android.app.Activity;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Limeman on 10/3/2017.
 */

public class WeekView extends Activity {

    String currWeek;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_view);
        final Calendar c = Calendar.getInstance();
        TextView tv = (TextView)findViewById(R.id.textView1);

        currWeek = "Vecka " + Integer.toString(c.WEEK_OF_YEAR);

        tv.setText(currWeek);
    }
}
