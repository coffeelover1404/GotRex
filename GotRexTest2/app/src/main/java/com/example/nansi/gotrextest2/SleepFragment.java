package com.example.nansi.gotrextest2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SleepFragment extends Fragment {
    private GotRexDatabase gotRexDatabase;

    public SleepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        gotRexDatabase = new GotRexDatabase(getActivity());
        gotRexDatabase.open();
        gotRexDatabase.updateSleep();
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

}
