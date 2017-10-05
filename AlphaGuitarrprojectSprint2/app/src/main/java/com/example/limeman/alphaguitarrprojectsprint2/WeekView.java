package com.example.limeman.alphaguitarrprojectsprint2;

import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


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
        LinearLayout weekElem = (LinearLayout)findViewById(R.id.linearLayoutVecka);
        int days = weekElem.getChildCount();
        RelativeLayout currDay;
        TextView currHour;

        for(int i = 0; i < days; i++){
            currDay = (RelativeLayout)weekElem.getChildAt(i);

            for(int j = 0; j < 8; j++){
                currHour = (TextView)currDay.getChildAt(j);
                currHour.setVisibility(View.GONE);
            }
        }

        BookingContainer test1= new BookingContainer(1, "Idag är det måndag", "10:00", "11:00", 2017, 10, 2);
        BookingContainer test2= new BookingContainer(1, "It's thursday bitches", "12:00", "13:00", 2017, 10, 5);
        BookingContainer test3= new BookingContainer(1, "It's tuesday my dudes ööhhh", "16:00", "13:00", 2017, 10, 3);

        BookingContainer b[] = {test1,test2,test3};
        fillData(b);
    }


    public void fillData(BookingContainer[] b){

        Integer resId, timeIdentifier;
        TextView tv;

        for (int i = 0; i < b.length; i++){

            switch (b[i].startTime){
                case "10:00":
                    timeIdentifier = 1;
                    break;
                case "11:00":
                    timeIdentifier = 2;
                    break;
                case "12:00":
                    timeIdentifier = 3;
                    break;
                case "13:00":
                    timeIdentifier = 4;
                    break;
                case "14:00":
                    timeIdentifier = 5;
                    break;
                case "15:00":
                    timeIdentifier = 6;
                    break;
                case "16:00":
                    timeIdentifier = 7;
                    break;
                case "17:00":
                    timeIdentifier = 8;
                    break;
                default:
                    timeIdentifier = 0;
                    System.out.println("ogiltig tid, får ej inparameter");
                    break;

            }
            resId = getResources().getIdentifier(b[i].dayOfWeek + "Hour" + Integer.toString(timeIdentifier), "id", getPackageName());
            tv = (TextView)findViewById(resId);
            tv.setVisibility(View.VISIBLE);
            tv.setText(b[i].info);
        }

    }


}
