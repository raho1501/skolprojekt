package com.example.limeman.alphaguitarrprojectsprint2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Martin on 2017-10-05.
 */

public class MondayView extends Fragment {

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View monday = inflater.inflate(R.layout.fragment_tuesday_view, container, false);
        return monday;
    }
}
