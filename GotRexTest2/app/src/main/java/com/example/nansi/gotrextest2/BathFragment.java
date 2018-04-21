package com.example.nansi.gotrextest2;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BathFragment extends Fragment {

    ImageView imageView;
    AnimationDrawable anim;

    public BathFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myGotRex = inflater.inflate(R.layout.fragment_bath, container, false);

        imageView = (ImageView)myGotRex.findViewById(R.id.babyBath);
        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_bath);

        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();
        return myGotRex;
    }

}
