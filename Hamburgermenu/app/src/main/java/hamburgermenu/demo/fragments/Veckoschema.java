package hamburgermenu.demo.fragments;

import android.content.DialogInterface;
import android.graphics.RectF;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

public class Veckoschema extends Fragment implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener{

    private WeekView weekView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.veckoschema, container, false);

        return rootView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        // Get a reference for the week view in the layout.
        weekView = (WeekView)view.findViewById(R.id.weekView);

        // Set an action when any event is clicked.
        weekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        weekView.setMonthChangeListener(this);

        // Set long press listener for events.
        weekView.setEventLongPressListener(this);

        //Set the number of visible days.
        weekView.setNumberOfVisibleDays(5);

        fetchEvents();
    }
    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(getActivity(), "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();


        //TextView custName = (TextView)getView().findViewById(R.id.CustomerInfo);
        //custName.setText(event.getTitle());

        Event tmpEvent = Events.events.get(0);

        for(Event weekEvent : Events.events){
            if(weekEvent.getId() == event.getId() && weekEvent.getTitle() == event.getName()){
                tmpEvent = weekEvent;
            }
        }

        if(tmpEvent instanceof AppointmentEvent){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            LayoutInflater inflater = LayoutInflater.from(getContext());

            View view = inflater.inflate(R.layout.event_dialog, null);

            builder.setView(view);

            final TextView editTitle = (TextView) view.findViewById(R.id.eventTitle);
            editTitle.setText(tmpEvent.getTitle());

            final TextView editTime = (TextView)view.findViewById(R.id.eventTime);
            editTime.setText("Tid: " + tmpEvent.getStartTime().substring(11,16) + "-" + tmpEvent.getStopTime().substring(11,16));

            final TextView editInfo = (TextView)view.findViewById(R.id.eventDescription);
            String info = tmpEvent.getInfo();
            editInfo.setText(info);

            builder.setNegativeButton("Cancel", null);
            final Event finalTmpEvent = tmpEvent;
            builder.setNeutralButton("Send email", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                    //use a bundle and a ArrayList<String> to send arguments to the email
                    //sender fragment thingymabober
                    Bundle bundle = new Bundle();
                    ArrayList<String> arguments = new ArrayList<String>();

                    //add the arguments to the ArrayList<String>
                    arguments.add(((AppointmentEvent) finalTmpEvent).getFirstName());
                    arguments.add(((AppointmentEvent) finalTmpEvent).getLastName());
                    arguments.add(((AppointmentEvent) finalTmpEvent).getEmail());

                    //add them to the bundle
                    bundle.putStringArrayList("key", arguments);

                    MailToCustomer mail = new MailToCustomer();

                    //add the bundle as an argument that goes to the fragment
                    mail.setArguments(bundle);

                    //replace the current view to that fragment
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction().replace(R.id.content_frame, mail).commit();

                }
            });

            AlertDialog dialog = builder.create();

            dialog.show();
        }
        else{
            buildDialog(tmpEvent);
        }

    }

    //Dialog ruta när man klickar på ett event i schemat.
    private void buildDialog(Event event){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view = inflater.inflate(R.layout.event_dialog, null);

        builder.setView(view);

        final TextView editTitle = (TextView) view.findViewById(R.id.eventTitle);
        editTitle.setText(event.getTitle());

        final TextView editTime = (TextView)view.findViewById(R.id.eventTime);
        editTime.setText("Tid: " + event.getStartTime().substring(11,16) + "-" + event.getStopTime().substring(11,16));

        final TextView editInfo = (TextView)view.findViewById(R.id.eventDescription);
        String info = event.getInfo();
        editInfo.setText(info);

        builder.setNegativeButton("OK", null);
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(getActivity(), "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();

        Event tmpEvent = Events.events.get(0);

        for(Event weekEvent : Events.events){
            if(weekEvent.getId() == event.getId() && weekEvent.getTitle() == event.getName()){
                tmpEvent = weekEvent;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view = inflater.inflate(R.layout.event_dialog, null);

        builder.setView(view);

        final TextView editTitle = (TextView) view.findViewById(R.id.eventTitle);
        editTitle.setText(tmpEvent.getTitle());

        final TextView editTime = (TextView)view.findViewById(R.id.eventTime);
        editTime.setText("Tid: " + tmpEvent.getStartTime().substring(11,16) + "-" + tmpEvent.getStopTime().substring(11,16));

        final TextView editInfo = (TextView)view.findViewById(R.id.eventDescription);
        String info = tmpEvent.getInfo();
        editInfo.setText(info);

        builder.setNegativeButton("Cancel", null);
        final Event finalTmpEvent = tmpEvent;
        builder.setNeutralButton("Remove event", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RetrofitWrapper retro = new RetrofitWrapper();
                if (finalTmpEvent instanceof AppointmentEvent){
                    retro.deleteAppointmentEvent((AppointmentEvent) finalTmpEvent);
                }
                else if (finalTmpEvent instanceof RepairEvent){
                    retro.deleteRepairEvent((RepairEvent) finalTmpEvent);
                }
                else if(finalTmpEvent instanceof LeaveEvent){
                    retro.deleteLeaveEvent((LeaveEvent) finalTmpEvent);
                }
                fetchEvents();
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();


    }

    private void test(int id) {
    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth){
        // Populate the week view with some events.
        List<WeekViewEvent> matchList = new ArrayList<WeekViewEvent>();

        long count = 0;
        for (Event weekEvent : Events.events) {
            if(weekEvent.getMonth() == newMonth){
                weekEvent.setId(count);
                matchList.add(weekEvent.toWeekViewEvent());
            }
            ++count;
        }
        return matchList;
    }
    public void fetchEvents()
    {
        RetrofitWrapper retro = new RetrofitWrapper();
        retro.getAppointmentEvents(new RetroCallback<List<Event>>()
                {
                    @Override
                    public void onResponse(List<Event> entity) {
                        Events.events = entity;
                        Calendar c = Calendar.getInstance();
                        weekView.getMonthChangeListener().onMonthChange(c.YEAR, c.MONTH);
                        weekView.notifyDatasetChanged();
                    }
                }
        );
        retro.getRepairEvents(new RetroCallback<List<Event>>()
            {
                @Override
                public void onResponse(List<Event> entity) {
                    for(Event events : entity)
                    {
                        Events.events.add(events);
                    }
                    Calendar c = Calendar.getInstance();
                    weekView.getMonthChangeListener().onMonthChange(c.YEAR, c.MONTH);
                    weekView.getMonthChangeListener().onMonthChange(2017, 10);
                    weekView.notifyDatasetChanged();
                }
            }
        );

        retro.getLeaveEvents(new RetroCallback<List<Event>>()
        {
            @Override
            public void onResponse(List<Event> entity) {
                for (Event events : entity){
                    Events.events.add(events);
                }
                Calendar c = Calendar.getInstance();
                weekView.getMonthChangeListener().onMonthChange(c.YEAR, c.MONTH);
                weekView.getMonthChangeListener().onMonthChange(2017,10);
                weekView.notifyDatasetChanged();
            }
        });

    }
}
