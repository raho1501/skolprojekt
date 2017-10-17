package hamburgermenu.demo.fragments;

import android.graphics.Color;

import com.alamkanak.weekview.WeekViewEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by linus on 2017-10-11.
 */

public class Event
{
    private String name;
    private String startTime;
    private String stopTime;
    private String date;
    private String info;
    private String email;
    private String phoneNr;


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

    public int getMonth(){
        return Integer.parseInt(date.substring(5,7));
    }

    public void setDate(String date) {
        this.date = date;
    }
    public WeekViewEvent toWeekViewEvent()
    {
        WeekViewEvent weekViewEvent = new WeekViewEvent();

        Calendar now = Calendar.getInstance();
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        Calendar start = (Calendar) now.clone();

        Calendar stop = (Calendar) now.clone();
        Calendar reservationDate = (Calendar) now.clone();
        try
        {
            Date temp = parser.parse(getStartTime());
            start.setTime(temp);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }

        try
        {
            Date temp = parser.parse(getStopTime());
            stop.setTime(temp);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        try
        {
            Date temp = parser.parse(getDate());
            reservationDate.setTime(temp);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }

        // Initialize start and end time.

        Calendar startTime = (Calendar) now.clone();
        startTime.set(Calendar.YEAR, reservationDate.get(Calendar.YEAR));
        startTime.set(Calendar.MONTH, reservationDate.get(Calendar.MONTH));
        startTime.set(Calendar.DAY_OF_MONTH, reservationDate.get(Calendar.DAY_OF_MONTH));
        startTime.set(Calendar.HOUR_OF_DAY, start.get(Calendar.HOUR_OF_DAY));
        startTime.set(Calendar.MINUTE, start.get(Calendar.MINUTE));
        startTime.set(Calendar.SECOND, start.get(Calendar.SECOND));

        Calendar stopTime = (Calendar) now.clone();
        stopTime.set(Calendar.YEAR, reservationDate.get(Calendar.YEAR));
        stopTime.set(Calendar.MONTH, reservationDate.get(Calendar.MONTH));
        stopTime.set(Calendar.DAY_OF_MONTH, reservationDate.get(Calendar.DAY_OF_MONTH));
        stopTime.set(Calendar.HOUR_OF_DAY, stop.get(Calendar.HOUR_OF_DAY));
        stopTime.set(Calendar.MINUTE, stop.get(Calendar.MINUTE));
        stopTime.set(Calendar.SECOND, stop.get(Calendar.SECOND));

        weekViewEvent.setName(getName());
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(stopTime);
        weekViewEvent.setColor(Color.argb(255, 50, 100, 100));


        return weekViewEvent;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }
}
