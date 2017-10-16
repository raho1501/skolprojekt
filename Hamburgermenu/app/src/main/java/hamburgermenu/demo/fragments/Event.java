package hamburgermenu.demo.fragments;

import android.graphics.Color;

import com.alamkanak.weekview.WeekViewEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by linus on 2017-10-11.
 */

public class Event
{
    private String name;
    private String startTime;
    private String stopTime;
    private String date;
    private String decription;
    private String subject;

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDecription(){
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getDate() {
        return date;
    }

    public int getDay(){
        return Integer.parseInt(date.substring(3,5));
    }
    public int getMonth(){
        System.out.println(date.substring(0,2));
        return Integer.parseInt(date.substring(0, 2));
    }
    public int getYear(){
        return Integer.parseInt(date.substring(6, 10));
    }

    public void setDate(String date) {this.date = date;}
    public WeekViewEvent toWeekViewEvent()
    {
        WeekViewEvent weekViewEvent = new WeekViewEvent();

        Calendar now = Calendar.getInstance();
        SimpleDateFormat hourMinutes = new SimpleDateFormat("HH:mm");
        SimpleDateFormat years = new SimpleDateFormat("MM/dd/yyyy");
        Calendar start = (Calendar) now.clone();

        Calendar stop = (Calendar) now.clone();
        Calendar reservationDate = (Calendar) now.clone();
        try
        {
            Date temp = hourMinutes.parse(getStartTime()); // TODO Någonting med parsingen går fel.
            start.setTime(temp);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }

        try
        {
            stop.setTime(hourMinutes.parse(getStopTime()));
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        try
        {
            reservationDate.setTime(years.parse(getDate()));
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }

        // Initialize start and end time.

        Calendar startTime = (Calendar) now.clone();
        startTime.set(Calendar.YEAR, reservationDate.get(Calendar.YEAR));
        startTime.set(Calendar.DATE, reservationDate.get(Calendar.DATE));
        startTime.set(Calendar.MONTH, reservationDate.get(Calendar.MONTH));
        startTime.set(Calendar.HOUR_OF_DAY, start.get(Calendar.HOUR_OF_DAY));
        startTime.set(Calendar.MINUTE, start.get(Calendar.MINUTE));
        startTime.set(Calendar.SECOND, start.get(Calendar.SECOND));

        Calendar stopTime = (Calendar) now.clone();
        stopTime.set(Calendar.YEAR, reservationDate.get(Calendar.YEAR));
        stopTime.set(Calendar.DATE, reservationDate.get(Calendar.DATE));
        stopTime.set(Calendar.MONTH, reservationDate.get(Calendar.MONTH));
        stopTime.set(Calendar.HOUR_OF_DAY, stop.get(Calendar.HOUR_OF_DAY));
        stopTime.set(Calendar.MINUTE, stop.get(Calendar.MINUTE));
        stopTime.set(Calendar.SECOND, stop.get(Calendar.SECOND));

        weekViewEvent.setName(getName());
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(stopTime);
        weekViewEvent.setColor(Color.argb(255, 50, 100, 100));


        return weekViewEvent;
    }
}
