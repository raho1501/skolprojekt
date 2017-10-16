package hamburgermenu.demo.fragments;

import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Veckoschema extends Fragment implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.veckoschema, container, false);

        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        // Get a reference for the week view in the layout.
        WeekView weekView = (WeekView)view.findViewById(R.id.weekView);

        // Set an action when any event is clicked.
        weekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        weekView.setMonthChangeListener(this);

        // Set long press listener for events.
        weekView.setEventLongPressListener(this);

        //Set the number of visible days.
        weekView.setNumberOfVisibleDays(5);

    }
    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(getActivity(), "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
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
