package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewLauncher extends Activity {

    private EditText editText;
    private Button okButton;
    private GotRexDatabase gotRexDatabase;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_launcher);
        gotRexDatabase = new GotRexDatabase(this);
        gotRexDatabase.open();
        boolean  check = gotRexDatabase.checkDB(); // Call check database function to determine the start page
        if(check == false){ //table not empty mean that player already named baby
            Intent continueGame = new Intent(NewLauncher.this, MainActivity.class); // go to home page
                      startActivity(continueGame);
        }
        else { //table mean that player did not named baby and crack baby egg yet
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_new_launcher);

            editText = findViewById(R.id.editText);
            okButton = findViewById(R.id.button);

            okButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    if (editText.getText().length() > 0) { //get name of baby from player
                        String Name = editText.getText().toString();
                        editText.setText("");

                        gotRexDatabase.insertName(Name); // insert name into table
                        Intent startGame = new Intent(NewLauncher.this, CrackEgg.class); // go to crack baby egg page
                        startActivity(startGame);
                    } else {
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(NewLauncher.this); // check if player really name the baby
                        mBuilder.setMessage("Please name your pet!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert = mBuilder.create();
                        alert.setTitle("No Name Set");
                        alert.show();
                    }
                }
            });
        }
    }
}
