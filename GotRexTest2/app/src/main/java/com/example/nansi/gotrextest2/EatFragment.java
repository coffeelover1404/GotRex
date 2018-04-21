package com.example.nansi.gotrextest2;


import android.content.ClipData;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EatFragment extends Fragment {

    ImageView imageView;
    AnimationDrawable anim;
    int check=0;
    ImageView food1, food2, food3, food4, food5;

    public EatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myGotRex = inflater.inflate(R.layout.fragment_eat, container, false);

        food1 = (ImageView) myGotRex.findViewById(R.id.food1);
        food2 = (ImageView) myGotRex.findViewById(R.id.food2);
        food3 = (ImageView) myGotRex.findViewById(R.id.food3);
        food4 = (ImageView) myGotRex.findViewById(R.id.food4);
        food5 = (ImageView) myGotRex.findViewById(R.id.food5);

        imageView = (ImageView)myGotRex.findViewById(R.id.babyEat);
        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_eat);

        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();

        food1.setOnLongClickListener(myClick);
        food2.setOnLongClickListener(myClick);
        food3.setOnLongClickListener(myClick);
        food4.setOnLongClickListener(myClick);
        food5.setOnLongClickListener(myClick);
        imageView.setOnDragListener(dragListener);

        return myGotRex;
    }

   View.OnLongClickListener myClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder shadow = new View.DragShadowBuilder(v);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                v.startDragAndDrop(data, shadow, v, 0);
            } else {
                v.startDrag(data, shadow, v, 0);
            }
            return true;
        }
    };


   View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent = event.getAction();

            switch(dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:

                    final View view = (View) event.getLocalState();

                    if (view.getId() == R.id.food1) {

                        if(imageView == null) throw new AssertionError();
                        imageView.setBackgroundResource(R.drawable.animation_chew);

                        anim = (AnimationDrawable)imageView.getBackground();
                        anim.start();
                    }
                    break;
            }
            return true;
        }
    };

}