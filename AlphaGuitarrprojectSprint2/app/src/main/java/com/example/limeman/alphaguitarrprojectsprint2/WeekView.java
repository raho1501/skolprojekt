package com.example.limeman.alphaguitarrprojectsprint2;

import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



/**
 * Created by Limeman on 10/3/2017.
 */

public class WeekView extends Activity {

    String currWeek;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);

        //get the current date and set the displayed week to the current week
        final Calendar c = Calendar.getInstance();
        TextView tv = (TextView)findViewById(R.id.textView1);
        currWeek = "Vecka " + Integer.toString(c.WEEK_OF_YEAR);
        tv.setText(currWeek);

        //lines 33 to 45 hide the TextViews that are blocks indicating if an hour is booked or not
        LinearLayout dayElem = (LinearLayout)findViewById(R.id.linearLayoutVecka);
        int days = dayElem.getChildCount();
        RelativeLayout currDay;
        TextView currHour;

        for(int i = 0; i < days; i++){
            currDay = (RelativeLayout)dayElem.getChildAt(i);
            for(int j = 0; j < 8; j++){
                currHour = (TextView)currDay.getChildAt(j);
                currHour.setVisibility(View.GONE);
            }
        }
    }

}
