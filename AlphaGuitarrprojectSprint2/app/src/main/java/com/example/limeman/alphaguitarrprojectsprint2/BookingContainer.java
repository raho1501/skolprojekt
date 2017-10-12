package com.example.limeman.alphaguitarrprojectsprint2;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.ULocale;

import java.util.Locale;

/**
 * Created by Limeman on 10/4/2017.
 * BookingContainer class, will be used to store the database locally
 */

public class BookingContainer {
    int appointmentId;
    int year;
    int month;
    int day;

    String info;
    String startTime;
    String stopTime;
    String dayOfWeek;

    public BookingContainer(int appointmentId, String info, String startTime, String stopTime, int year, int month, int day){
        this.appointmentId = appointmentId;
        this.info = info;

        this.startTime = startTime;
        this.stopTime = stopTime;
        this.year = year;
        this.month  = month;
        this.day = day;

        Calendar c = Calendar.getInstance();
        c.set(year, month-1 ,day);

        SimpleDateFormat tempday = new SimpleDateFormat("EEE", Locale.ENGLISH);
        dayOfWeek = tempday.format(c.getTime());
    }
}
