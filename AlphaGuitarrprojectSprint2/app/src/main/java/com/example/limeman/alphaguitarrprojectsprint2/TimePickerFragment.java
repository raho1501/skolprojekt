package com.example.limeman.alphaguitarrprojectsprint2;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

/**
 * Created by Limeman on 9/27/2017.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        //format the selected time into a nice string
        String time = Integer.toString(hourOfDay) + ":" + Integer.toString(minute);

        //call the onCompleteTime method and pass the formatted time string
        this.mListener.onCompleteTime(time);
    }

    //abstract method to be implemented in BokaHandelse
    //will be called when timePickerFragment is complete
    public static interface OnCompleteTimeListener {
        public abstract void onCompleteTime(String time);
    }

    private OnCompleteTimeListener mListener;

    //have no idea what this shit does, code won't work without it
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteTimeListener)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }

}
