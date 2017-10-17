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

public abstract class Event {
    private String title;
    private String info;
    private long id;
    private int color = Color.argb(255, 50, 100, 100);
    private TimeReservation timeReservation = new TimeReservation();

    long getId() {
        return id;
    }

    void setId(long x) {
        id = x;
    }
    protected void setColor(int r, int g, int b, int a)
    {
        color = Color.argb(a, r, g, b);
    }
    private int getColor()
    {
        return color;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return timeReservation.getStartTime();
    }

    public void setStartTime(String startTime) {
        timeReservation.setStartTime(startTime);
    }

    public String getStopTime() {
        return timeReservation.getStopTime();
    }

    public void setStopTime(String stopTime) {
        timeReservation.setStopTime(stopTime);
    }

    public String getDate() {
        return timeReservation.getReservationDate();
    }

    public void setDate(String date) {
        timeReservation.setReservationDate(date);
    }

    public int getMonth() {
        String tempDate = getDate().substring(5, 7);
        return Integer.parseInt(tempDate);
    }


    public WeekViewEvent toWeekViewEvent() {
        WeekViewEvent weekViewEvent = new WeekViewEvent();

        Calendar now = Calendar.getInstance();
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        Calendar start = (Calendar) now.clone();

        Calendar stop = (Calendar) now.clone();
        Calendar reservationDate = (Calendar) now.clone();
        try {
            Date temp = parser.parse(getStartTime());
            start.setTime(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            Date temp = parser.parse(getStopTime());
            stop.setTime(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            Date temp = parser.parse(getDate());
            reservationDate.setTime(temp);
        } catch (ParseException e) {
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

        weekViewEvent.setName(getTitle());
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(stopTime);
        weekViewEvent.setColor(getColor());
        weekViewEvent.setId(getId());

        return weekViewEvent;
    }
    public TimeReservation getTimeReservation() {
        return timeReservation;
    }

    public void setTimeReservation(TimeReservation timeReservation) {
        this.timeReservation = timeReservation;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
