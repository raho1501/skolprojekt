package hamburgermenu.demo.fragments;

import android.content.Context;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.markus.hamburgermenu.R;

import java.util.ArrayList;
import java.util.List;

public class BokaReparation extends Fragment implements AdapterView.OnItemSelectedListener{
    int monthNum;
    List<String> dayList= new ArrayList<String>();
    Calendar c = Calendar.getInstance();
    Spinner monthSpinner;
    ArrayAdapter<String> monthAdapter;
    Button btn;
    RepairEvent evn;
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_boka_reparation, container, false);
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        monthSpinner = (Spinner) getView().findViewById(R.id.date);
        final Spinner dateSpinner = (Spinner) getView().findViewById(R.id.month);
        final Spinner startTidSpinner = (Spinner) getView().findViewById(R.id.startTime);
        final Spinner stopTidSpinner = (Spinner) getView().findViewById(R.id.stopTime);


        List<String> list;

        list = new ArrayList<String>();
        List<String> tidList;
        tidList = new ArrayList<String>();
        for (int i = 1; i <= 12; i++) {
            list.add(String.valueOf(i));
        }
        for (int j = 10; j <= 18; j++) {
            String t = String.valueOf(j);
            t = t + ":00";
            tidList.add(t);
            t = "";
        }
        monthAdapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, list);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);
        monthSpinner.setOnItemSelectedListener(this);


        dayList.clear();
        String selectedMonth = monthSpinner.getSelectedItem().toString();
        monthNum = Integer.parseInt(selectedMonth);
        monthNum--;
        Calendar mycal = new GregorianCalendar(c.get(Calendar.YEAR), monthNum, 1);
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);


        for (int k = 1; k <= daysInMonth; k++) {
            String t = String.valueOf(k);
            dayList.add(t);
            t = "";
        }


        ArrayAdapter<String> dateAdapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, dayList);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(dateAdapter);


        ArrayAdapter<String> startTidAdapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, tidList);
        startTidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startTidSpinner.setAdapter(startTidAdapter);

        ArrayAdapter<String> stopTidAdapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, tidList);
        stopTidAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stopTidSpinner.setAdapter(stopTidAdapter);



        btn = (Button) getView().findViewById(R.id.button);
        if (btn != null) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("click");
                }
            });
        }


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                evn = new RepairEvent();
                //check the length of the selected item to make sure we have proper formatting
                String s = monthSpinner.getSelectedItem().toString();
                String pepe = dateSpinner.getSelectedItem().toString();
                if (s.length() < 2){
                    s = "0" + s;
                    if(pepe.length() < 2){
                        pepe = "0" + pepe;
                        evn.setDate(Integer.toString(c.get(Calendar.YEAR)) + "-"
                                + s + "-" + pepe
                                + "T00:00:00+02:00");
                    }
                    else{
                        evn.setDate(Integer.toString(c.get(Calendar.YEAR)) + "-" +
                                s + "-" + dateSpinner.getSelectedItem().toString()
                                + "T00:00:00+02:00");
                    }
                }
                else{
                    if(pepe.length() < 2){
                        pepe = "0" + pepe;
                        evn.setDate(Integer.toString(c.get(Calendar.YEAR)) + "-"
                                + s + "-" + pepe
                                + "T00:00:00+02:00");
                    }
                    else{
                        evn.setDate(Integer.toString(c.get(Calendar.YEAR)) + "-" +
                                monthSpinner.getSelectedItem().toString() + "-" +
                                dateSpinner.getSelectedItem().toString()
                                + "T00:00:00+02:00");

                    }
                }

                System.out.println(startTidSpinner.getSelectedItem());
                evn.setStartTime(
                        "1970-01-01T" + startTidSpinner.getSelectedItem().toString()
                                + ":00+01:00"
                );
                evn.setStopTime(
                        "1970-01-01T" + stopTidSpinner.getSelectedItem().toString()
                                + ":00+01:00"
                );
                TextView decr = (TextView) getView().findViewById(R.id.inputBoxInfo);
                evn.setInfo(
                        decr.getText().toString()
                );

                RetrofitWrapper rw = new RetrofitWrapper();

                rw.postRepairEvent((RepairEvent)evn);

                Toast.makeText(getActivity(), "Händelse tillagd!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view , int position , long id)
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
