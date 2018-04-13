package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 * <p>
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class CrackEgg extends Activity {

    ImageView imageView;
    AnimationDrawable anim;
    private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crack_egg);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent eggWait = new Intent(CrackEgg.this, HomeFragment.class);
                startActivity(eggWait);
                finish();
            }
        }, SPLASH_TIME_OUT);
        imageView = findViewById(R.id.imageEgg);
        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_egg);

        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();
    }
}
