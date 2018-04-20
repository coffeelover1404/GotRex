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


    public EatFragment() {
        // Required empty public constructor
    }

    ImageView food1, food2, food3, food4, food5, grex;
    TextView tt, tgg;
    View a;
    ImageView imageView;
    AnimationDrawable anim;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        a = inflater.inflate(R.layout.fragment_eat, container, false);

        food1 = (ImageView) a.findViewById(R.id.food1);
        food2 = (ImageView) a.findViewById(R.id.food2);
        food3 = (ImageView) a.findViewById(R.id.food3);
        food4 = (ImageView) a.findViewById(R.id.food4);
        food5 = (ImageView) a.findViewById(R.id.food5);
        grex = (ImageView) a.findViewById(R.id.eater);
        tt = (TextView) a.findViewById(R.id.ttt);
        tgg = (TextView) a.findViewById(R.id.tgg);

        food1.setOnLongClickListener(longClick);
        food2.setOnLongClickListener(longClick);
        food3.setOnLongClickListener(longClick);
        food4.setOnLongClickListener(longClick);
        food5.setOnLongClickListener(longClick);

        grex.setOnDragListener(dragListener);
        tgg.setOnDragListener(dragListener);

        return a;
    }

    View.OnLongClickListener longClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder shadow = new View.DragShadowBuilder(v);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tgg.setText("ma la");
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

                    if (view.getId() == R.id.tgg) {
                        tgg.setText("yey!");
                        /*imageView = (ImageView) a.findViewById(R.id.eater);
                        if(imageView == null) throw new AssertionError();
                        imageView.setBackgroundResource(R.drawable.animation_egg);

                        anim = (AnimationDrawable)imageView.getBackground();
                        anim.start();*/
                    }

                    break;
            }

            return true;
        }
    };

}
