package com.example.nansi.gotrextest2;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SleepFragment extends Fragment {

    private GotRexDatabase gotRexDatabase;
    ImageView imageView;
    AnimationDrawable anim;

    public SleepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myGotRex = inflater.inflate(R.layout.fragment_sleep, container, false);

        imageView = (ImageView)myGotRex.findViewById(R.id.babySleep);
        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_sleep);

        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();

        //when the user come to this page, increase the energy score
        //then check the growth score
        gotRexDatabase = new GotRexDatabase(getActivity());
        gotRexDatabase.open();
        gotRexDatabase.updateSleep();
        boolean check = gotRexDatabase.checkGrow();
        CheckGrowth.getGrowth(check, getActivity());

        return myGotRex;
    }
}
