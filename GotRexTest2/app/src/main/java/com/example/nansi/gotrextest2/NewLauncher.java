package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


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
public class NewLauncher extends Activity {

    private EditText editText;
    private Button okButton;
    private GotRexDatabase gotRexDatabase;

    public NewLauncher() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_launcher);
        init();

        editText = findViewById(R.id.editText);
        okButton = findViewById(R.id.button);

        /*okButton.setOnClickListener(new View.OnClickListener() {
                if(editText.getText().length() > 0){
                    gotRexDatabase = new GotRexDatabase(this);
                    gotRexDatabase.open();
                    String Name = editText.getText().toString();
                    editText.setText("");

                    gotRexDatabase.insertName(Name);
                }
                else{
                    Context mContext;
                    new AlertDialog.Builder(mContext).setTitle("Error").setMessage("Please name your pet!")
                            .setPositiveButton(" OK",null).show();
                }

        });*/

    }

    public Button btn;

    public void init(){
        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startGame = new Intent(NewLauncher.this, EggStart.class);
                startActivity(startGame);
            }
        });
    }
}
