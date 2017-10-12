package com.example.limeman.alphaguitarrprojectsprint2;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TuesdayView extends Fragment {
    @Override

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){

        View tuesday = inflater.inflate(R.layout.fragment_tuesday_view, container, false);
        return tuesday;
    }
}
