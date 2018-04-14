package com.example.nansi.gotrextest2;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ImageView imageView;
    AnimationDrawable anim;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myGotRex = inflater.inflate(R.layout.fragment_home, container, false);
        imageView = (ImageView)myGotRex.findViewById(R.id.imageBaby);
        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_baby);

        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_home);

        /*imageView = (ImageView)getView().findViewById(R.id.imageEgg);
        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_egg);

        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();*/


    }

}
