package se.miun.raho1501.testprog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.DatePicker;

/**
 * Created by Limeman on 9/27/2017.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

<<<<<<< HEAD
=======
    //abstract method (to be implemented in BokaHandelse)
    //that will be called when datePickerFragment is complete
    public static interface OnCompleteListener {
        public abstract void onComplete(String date);
    }

    private OnCompleteListener mListener;

    //not entirely sure what this shit does, code does'nt work without it
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mListener = (OnCompleteListener)activity;
        }
        catch (final ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
        }
    }

    //constructor for datePickerDialog
>>>>>>> 6244587bd9a7e8ffe710e98135fe58206d008d71
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    //when the user has selected a date
    public void onDateSet(DatePicker view, int year, int month, int day) {
<<<<<<< HEAD
        // Do something with the date chosen by the user
=======

        //format the date into a sexy string
        String time = Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);

        //call the onComplete method and pass the formatted date string
        this.mListener.onComplete(time);
>>>>>>> 6244587bd9a7e8ffe710e98135fe58206d008d71
    }
}
