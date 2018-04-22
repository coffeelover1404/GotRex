package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
public class GotRexAdult extends Activity {

    private GotRexDatabase gotRexDatabase;
    Button newGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gotrex_adult);

        gotRexDatabase = new GotRexDatabase(this);
        gotRexDatabase.open();
        newGameBtn = findViewById(R.id.newGame);

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: delete database here
                Intent startGame = new Intent(GotRexAdult.this, NewLauncher.class);
                startActivity(startGame);
            }
        });
        boolean  checkAdult = gotRexDatabase.checkBond();
        if(checkAdult == true){
            //TODO: Trex
        }
        else{
            //TODO: Godzilla
        }

    }
}