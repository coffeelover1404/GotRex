package com.example.nansi.gotrextest2;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
    Button bathButt;
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
        bathButt = (Button) myGotRex.findViewById(R.id.bathButton);

        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_bath);

        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();

        bathButt.setOnClickListener(myClick);

        return myGotRex;
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(imageView == null) throw new AssertionError();
            if(check==0) {
                imageView.setBackgroundResource(R.drawable.animation_rinse);
                check = 1;
                gotRexDatabase = new GotRexDatabase(getActivity());
                gotRexDatabase.open();
                gotRexDatabase.updateBath();
                boolean check = gotRexDatabase.checkGrow();
                CheckGrowth.getGrowth(check, getActivity());

            } else {
                imageView.setBackgroundResource(R.drawable.animation_bath);
                check = 0;
            }

            anim = (AnimationDrawable)imageView.getBackground();
            anim.start();
        }
    };

}
