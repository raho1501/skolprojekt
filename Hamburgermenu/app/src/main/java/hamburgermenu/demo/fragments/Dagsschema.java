package hamburgermenu.demo.fragments;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.example.markus.hamburgermenu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Markus on 2017-10-11.
 */

public class Dagsschema extends Fragment implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener {
    //public Events events;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.dagsschema, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        // Get a reference for the week view in the layout.
        WeekView weekView = (WeekView)view.findViewById(R.id.dayView);

        // Set an action when any event is clicked.
        weekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        weekView.setMonthChangeListener(this);

        // Set long press listener for events.
        weekView.setEventLongPressListener(this);

        //Set the number of visible days.
        weekView.setNumberOfVisibleDays(1);
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(getActivity(), "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();

        Event tempEvent = Events.events.get(0);

        for(Event weekEvent: Events.events){
            if(weekEvent.getId() == event.getId()){
                tempEvent = weekEvent;
            }
        }
        buildDialog(tempEvent);
    }

    //Dialog ruta när man klickar på ett event i schemat.
    private void buildDialog(Event event){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view =  inflater.inflate(R.layout.event_dialog, null);

        builder.setView(view);

        final TextView editTitle = (TextView) view.findViewById(R.id.eventTitle);
        editTitle.setText(event.getName());

        final TextView editTime = (TextView)view.findViewById(R.id.eventTime);
        editTime.setText("Tid: " + event.getStartTime().substring(11,16) + "-" + event.getStopTime().substring(11,16));

        final TextView editInfo = (TextView)view.findViewById(R.id.eventDescription);
        editInfo.setText(event.getInfo());

        builder.setNegativeButton("OK", null);
        AlertDialog dialog = builder.create();

        dialog.show();
    }


    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(getActivity(), "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();


    }
    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth){
        // Populate the week view with some events.
        List<WeekViewEvent> matchList = new ArrayList<WeekViewEvent>();
        for (Event weekEvent : Events.events) {
            //TODO hitta ett sätt att bara adda events som är i vyn just nu.
            if(weekEvent.getMonth() == newMonth){
                matchList.add(weekEvent.toWeekViewEvent());
            }
        }
        return matchList;
    }
}
