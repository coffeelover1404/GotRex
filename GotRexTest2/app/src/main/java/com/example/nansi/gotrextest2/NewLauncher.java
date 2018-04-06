package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    private GotRexDatabase gotRexDatabase;

    public NewLauncher() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_launcher);
        init();

        editText = findViewById(R.id.editText);
        //aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toDoItems);

        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent keyEvent) {
                /*if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
*/
                    String Name = editText.getText().toString();
                    editText.setText("");

                    gotRexDatabase.insertName(Name);
                    return true;
                }
                //return false;
  //          }
        });

        gotRexDatabase = new GotRexDatabase(this);
        //database.open();
    }

    public Button butt;

    public void init(){
        butt = (Button)findViewById(R.id.button);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startGame = new Intent(NewLauncher.this, EggStart.class);
                startActivity(startGame);
            }
        });
    }
}
