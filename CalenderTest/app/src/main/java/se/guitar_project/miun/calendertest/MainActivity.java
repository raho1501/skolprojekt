package se.guitar_project.miun.calendertest;

import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener{

    public List<WeekViewEvent> eventList = new ArrayList<WeekViewEvent>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get a reference for the week view in the layout.
        WeekView weekView = (WeekView) findViewById(R.id.weekView);

        // Set an action when any event is clicked.
        weekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        weekView.setMonthChangeListener(this);

        // Set long press listener for events.
        weekView.setEventLongPressListener(this);

        //Set the number of visible days.
        weekView.setNumberOfVisibleDays(5);

        Event event = new Event();
        event.setName("Event");
        event.setDate("10/12/2017");
        event.setStartTime("15:00");
        event.setStopTime("16:00");
        addEvent(event);

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
        List<WeekViewEvent> matchList = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent weekEvent : eventList) {
            //TODO hitta ett sätt att bara adda events som är i vyn just nu.
            matchList.add(weekEvent);
        }
        return matchList;
    }
    public void addEvent(Event event)
    {
        eventList.add(event.toWeekViewEvent());
    }
}