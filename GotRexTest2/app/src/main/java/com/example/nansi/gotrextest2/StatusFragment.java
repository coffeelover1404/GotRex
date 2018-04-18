package com.example.nansi.gotrextest2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {

    ProgressBar happyBar;
    ProgressBar hungryBar;
    ProgressBar cleanBar;
    ProgressBar energyBar;
    ProgressBar growthBar;
    TextView babyName;

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View statusPage = inflater.inflate(R.layout.fragment_status, container, false);
        hungryBar = statusPage.findViewById(R.id.HungryBar);
        cleanBar = statusPage.findViewById(R.id.CleanBar);
        energyBar = statusPage.findViewById(R.id.EnergyBar);
        happyBar = statusPage.findViewById(R.id.HappyBar);
        growthBar = statusPage.findViewById(R.id.GrowthBar);
        babyName = statusPage.findViewById(R.id.BabyName);

        //////////////////////////////Connect database to call value////////////////////////////

        hungryBar.setProgress(50);
        cleanBar.setProgress(50);
        energyBar.setProgress(50);
        happyBar.setProgress(50);
        growthBar.setProgress(50);
        //babyName.setText();

        return statusPage;
    }

}
