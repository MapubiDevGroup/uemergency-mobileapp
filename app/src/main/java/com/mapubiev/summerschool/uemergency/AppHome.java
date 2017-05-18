package com.mapubiev.summerschool.uemergency;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AppHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home);
    }


    public static Intent getIntented(Context context)
    {
          /* Create an Intent that will start the Menu-Activity. */
        Intent intent = new Intent(context,AppHome.class);
        return intent;
    }


}
