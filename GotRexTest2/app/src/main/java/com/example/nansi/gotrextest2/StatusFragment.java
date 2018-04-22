package com.example.nansi.gotrextest2;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private GotRexDatabase gotRexDatabase;
    Button deletePet;

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
        deletePet = statusPage.findViewById(R.id.deleteBtn);

        //////////////////////////////Connect database to call value////////////////////////////
        gotRexDatabase = new GotRexDatabase(getActivity());
        gotRexDatabase.open();

        hungryBar.setProgress(gotRexDatabase.pullStatus("hungry"));
        cleanBar.setProgress(gotRexDatabase.pullStatus("clean"));
        energyBar.setProgress(gotRexDatabase.pullStatus("energy"));
        happyBar.setProgress(gotRexDatabase.pullStatus("happy"));
        growthBar.setProgress(gotRexDatabase.pullStatus("grow"));
        babyName.setText(gotRexDatabase.pullName());
        deletePet.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setMessage("Are you sure? All status of your baby will be deleted").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: delete database here
                        Intent newGame = new Intent(getActivity(), NewLauncher.class);
                        startActivity(newGame);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = mBuilder.create();
                alert.setTitle("Deleting Pet");
                alert.show();
            }
        }));

        return statusPage;
    }



}
