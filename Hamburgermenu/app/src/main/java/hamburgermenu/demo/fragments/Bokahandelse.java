package hamburgermenu.demo.fragments;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.alamkanak.weekview.WeekView;
import com.example.markus.hamburgermenu.R;


import org.w3c.dom.Text;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Markus on 2017-10-11.
 */

public class Bokahandelse extends Fragment implements AdapterView.OnItemSelectedListener {

    int monthNum;
    List<String> dayList= new ArrayList<String>();
    Calendar c = Calendar.getInstance();
    Spinner monthSpinner;
    ArrayAdapter<String> monthAdapter;
    Button btn;
    Event evn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.bokahandelse, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        TextView tv = (TextView)getView().findViewById(R.id.inputBoxName);
        //create desired formatting for displaying the date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //tv.setText(sdf.format(c.getTime()));

        monthSpinner = (Spinner) getView().findViewById(R.id.monthSpinner);
        final Spinner dateSpinner = (Spinner) getView().findViewById(R.id.datumSpinner);
        final Spinner startTidSpinner = (Spinner) getView().findViewById(R.id.startTidSpinner);
        final Spinner stopTidSpinner = (Spinner) getView().findViewById(R.id.stopTidSpinner);



        List<String> list;

        list = new ArrayList<String>();
        List<String> tidList;
        tidList = new ArrayList<String>();
        for(int i = 1;i<=12;i++)
        {
            list.add(String.valueOf(i));
        }
        for(int j = 10; j <=18;j++)
        {
            String t = String.valueOf(j);
            t= t+":00";
            tidList.add(t);
            t="";
        }
        monthAdapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, list);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);
        monthSpinner.setOnItemSelectedListener(this);




        dayList.clear();
        String selectedMonth =monthSpinner.getSelectedItem().toString();
        monthNum = Integer.parseInt(selectedMonth);
        monthNum--;
        Calendar mycal = new GregorianCalendar(c.get(Calendar.YEAR),monthNum, 1);
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);


        for(int k = 1; k<= daysInMonth; k++)
        {
            String t = String.valueOf(k);
            dayList.add(t);
            t="";
        }



        ArrayAdapter<String> dateAdapter= new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, dayList);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(dateAdapter);







        ArrayAdapter<String> startTidAdapter= new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, tidList);
        startTidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startTidSpinner.setAdapter(startTidAdapter);

        ArrayAdapter<String> stopTidAdapter= new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, tidList);
        stopTidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stopTidSpinner.setAdapter(stopTidAdapter);





        View view = getView();
        if(view != null) {
            view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("click");
                }
            });
        }

        btn = (Button)getView().findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                evn = new Event();
                evn.setDate( monthSpinner.getSelectedItem().toString() + "/" +
                        dateSpinner.getSelectedItem().toString()  + "/" +
                        Integer.toString(c.get(Calendar.YEAR)));
                TextView namn = (TextView)getView().findViewById(R.id.inputBoxName);
                evn.setName(namn.getText().toString());
                evn.setStartTime(
                        startTidSpinner.getSelectedItem().toString()
                );
                evn.setStopTime(
                        stopTidSpinner.getSelectedItem().toString()
                );
                TextView decr = (TextView)getView().findViewById(R.id.inputBoxDescription);
                evn.setDecription(
                        decr.getText().toString()
                );
                TextView subj = (TextView)getView().findViewById(R.id.inputBoxSubject);
                evn.setSubject(
                        subj.getText().toString()
                );
                Events.events.add(evn);
            }

        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent,View view , int position , long id)
    {
        if(parent.getId()== monthSpinner.getId()){

            System.out.println(parent.getId() + " no id"+monthSpinner.getId() );
            dayList.clear();
            String selectedMonth =parent.getSelectedItem().toString();
            monthNum = Integer.parseInt(selectedMonth);
            monthNum--;
            Calendar mycal = new GregorianCalendar(c.get(Calendar.YEAR),monthNum, 1);
            int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);


            for(int k = 1; k<= daysInMonth; k++)
            {
                String t = String.valueOf(k);
                dayList.add(t);
                t="";
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
