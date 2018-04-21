package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //if ไป intent หน้าอื่น
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_launcher);
        gotRexDatabase = new GotRexDatabase(this);
        gotRexDatabase.open();
        boolean  check = gotRexDatabase.checkDB();
        if(check == false){
            Intent continueGame = new Intent(NewLauncher.this, MainActivity.class);
                      startActivity(continueGame);
        }
        else {
            //else all down here ไม่มั่นใจว่าทั้งหมดนี่จริงๆ รึเปล่านะ แต่เดาว่างั้น
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_launcher);

            editText = findViewById(R.id.editText);
            okButton = findViewById(R.id.button);

            //gotRexDatabase = new GotRexDatabase(this);
            //gotRexDatabase.open();

            okButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    if (editText.getText().length() > 0) {
                        String Name = editText.getText().toString();
                        editText.setText("");

                        gotRexDatabase.insertName(Name);
                        Intent startGame = new Intent(NewLauncher.this, CrackEgg.class);
                        startActivity(startGame);
                    } else {
                        //String Name = "default name";
                        //editText.setText("");

                        //gotRexDatabase.insertName(Name);
                        Intent startGame = new Intent(NewLauncher.this, CrackEgg.class);
                        startActivity(startGame);
                    /*new AlertDialog.Builder(mContext).setTitle("Error").setMessage("Please name your pet!")
                       .setPositiveButton(" OK", null).show();*/
                    }
                }
            });
        }
    }
}
