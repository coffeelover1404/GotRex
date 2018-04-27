package com.example.nansi.gotrextest2;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BathFragment extends Fragment {

    ImageView imageView;
    AnimationDrawable anim;
    Button bathBtn;
    int check=0;
    private GotRexDatabase gotRexDatabase;

    public BathFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myGotRex = inflater.inflate(R.layout.fragment_bath, container, false);

        imageView = (ImageView)myGotRex.findViewById(R.id.babyBath);
        bathBtn = (Button) myGotRex.findViewById(R.id.bathButton);

        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_bath);

        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();

        bathBtn.setOnClickListener(myClick);

        return myGotRex;
    }

    // for bath button
    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(imageView == null) throw new AssertionError();
            if(check==0) {
                imageView.setBackgroundResource(R.drawable.animation_rinse);    // call animation from animation_rinse.xml
                check = 1;

                // update clean status when the user press rinse button
                gotRexDatabase = new GotRexDatabase(getActivity());
                gotRexDatabase.open();
                gotRexDatabase.updateBath();

                // check if the baby grow up
                boolean check = gotRexDatabase.checkGrow();
                CheckGrowth.getGrowth(check, getActivity());

            } else {
                imageView.setBackgroundResource(R.drawable.animation_bath);    // call animation from animation_bath.xml
                check = 0;
            }

            // animation play
            anim = (AnimationDrawable)imageView.getBackground();
            anim.start();
        }
    };

}
