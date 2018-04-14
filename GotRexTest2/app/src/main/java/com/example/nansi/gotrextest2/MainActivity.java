package com.example.nansi.gotrextest2;

//import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public boolean setName = false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Home");
                    transaction.replace(R.id.content, new HomeFragment()).commit();
                    //HomeFragment littleGotRex = new HomeFragment();
                    //fragmentTransaction.replace(R.id.fram, fragment, "HomeFragment");
                    //fragmentTransaction.commit();
                    return true;
                case R.id.navigation_eat:
                    setTitle("Eat");
                    transaction.replace(R.id.content, new EatFragment()).commit();
                    return true;
                case R.id.navigation_bath:
                    setTitle("Take a Bath");
                    transaction.replace(R.id.content, new BathFragment()).commit();
                    return true;
                case R.id.navigation_status:
                    setTitle("Status");
                    transaction.replace(R.id.content, new StatusFragment()).commit();
                    return true;
                case R.id.navigation_sleep:
                    setTitle("Sleep");
                    transaction.replace(R.id.content, new SleepFragment()).commit();
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);

        setTitle("Home");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new HomeFragment()).commit();
    }

}
