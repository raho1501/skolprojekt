package se.guitar_project.miun.calendertest;

import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
=======
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener{

<<<<<<< HEAD
    public List<WeekViewEvent> eventList = new ArrayList<WeekViewEvent>();
=======
    public List<Event> eventList = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get a reference for the week view in the layout.
<<<<<<< HEAD
        WeekView weekView = (WeekView) findViewById(R.id.weekView);
=======
        final WeekView weekView = (WeekView) findViewById(R.id.weekView);


        // Set an action when any event is clicked.
        weekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        weekView.setMonthChangeListener(this);

        // Set long press listener for events.
        weekView.setEventLongPressListener(this);

        //Set the number of visible days.
        weekView.setNumberOfVisibleDays(5);

<<<<<<< HEAD
        Event event = new Event();
        event.setName("Event");
        event.setDate("10/12/2017");
        event.setStartTime("15:00");
        event.setStopTime("16:00");
        addEvent(event);

=======

        RetrofitWrapper retro = new RetrofitWrapper();
        retro.getEvents(
            new RetroCallback<List<Event>>()
            {
                @Override
                public void onResponse(List<Event> entity) {
                    setList(entity);
                    weekView.getMonthChangeListener().onMonthChange(2017,10);
                    weekView.notifyDatasetChanged();
                }
            }
        );
    }
    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth){
        // Populate the week view with some events.
<<<<<<< HEAD
        List<WeekViewEvent> matchList = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent weekEvent : eventList) {
            //TODO hitta ett sätt att bara adda events som är i vyn just nu.
            matchList.add(weekEvent);
=======
        List<WeekViewEvent> matchList = new ArrayList<>();
            //TODO hitta ett sätt att bara adda events som är i vyn just nu.
        for (Event event : eventList)
        {
            WeekViewEvent weekEvent = event.toWeekViewEvent();
            int month = weekEvent.getStartTime().get(Calendar.MONTH);
            if(month == newMonth)
            {
                matchList.add(event.toWeekViewEvent());
            }
        }
        return matchList;
    }
    public void addEvent(Event event)
    {
<<<<<<< HEAD
        eventList.add(event.toWeekViewEvent());
=======
        eventList.add(event);
    }
    public void setList(List<Event> events)
    {
        eventList = events;
    }
}
