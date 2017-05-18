package com.mapubiev.summerschool.uemergency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mapubiev.summerschool.uemergency.helpers.PreferencesManager;
import com.mapubiev.summerschool.uemergency.helpers.Utils;

import java.util.Calendar;
import java.util.Date;

public class Signup extends AppCompatActivity {


    EditText lname;
    EditText fname;
    EditText bdate;
    EditText nic_numb;
    EditText tel;
    AutoCompleteTextView country;
    AutoCompleteTextView sex;
    CheckBox im_concerned;

    Button submit_btn;

    private static final String[] COUNTRIES = new String[] {
            "Cameroon", "Nigeria"
    };

    private static final String[] SEX = new String[] {
            "M", "F"
    };


    private static final int MAJORITY_AGE = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /*We check if the Signup activity has ever been launched */

        boolean isFirstTime = PreferencesManager.isFirst(Signup.this);

        if(isFirstTime) {

            setContentView(R.layout.activity_signup);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, COUNTRIES);
            AutoCompleteTextView textView = (AutoCompleteTextView)
                    findViewById(R.id.country);
            textView.setAdapter(adapter);

            ArrayAdapter<String> adapterS = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, SEX);
            AutoCompleteTextView textViewS = (AutoCompleteTextView)
                    findViewById(R.id.sex);
            textViewS.setAdapter(adapterS);

        }
        else{
            Intent intent = AppHome.getIntented(Signup.this);
            Signup.this.startActivity(intent);
        }
    }

    //Function called when the Ok button is clicked on
    protected void getUserInput(View view)
    {

        lname = (EditText) findViewById(R.id.lname);
        fname = (EditText) findViewById(R.id.fname);
        bdate = (EditText) findViewById(R.id.bdate);
        nic_numb = (EditText) findViewById(R.id.nic_numb);
        tel = (EditText) findViewById(R.id.tel);
        country = (AutoCompleteTextView) findViewById(R.id.country);
        sex = (AutoCompleteTextView) findViewById(R.id.sex);

        im_concerned = (CheckBox) findViewById(R.id.im_concerned);



        //Manage Date input
        Date bDayDate = Utils.getDateFromString(bdate.getText().toString());
        Calendar currentDate = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(bDayDate);

        //Validate inputs
        //validate email and password

        if (lname.getText().toString() == null || lname.getText().toString().isEmpty()) {
            Utils.onError(R.string.empty_lname);
            return;
        }

        if (fname.getText().toString() == null || fname.getText().toString().isEmpty()) {
            Utils.onError(R.string.empty_fname);
            return;
        }

        if (sex.getText().toString() == null || sex.getText().toString().isEmpty()) {
            Utils.onError(R.string.empty_sex);
            return;
        }

        if (bdate.getText().toString() == null || bdate.getText().toString().isEmpty()) {
            Utils.onError(R.string.empty_bdate);
            return;
        }

        //We check if the user is major and make the NIC number mandatory if he proves major
        if(Utils.getAge(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)) >  MAJORITY_AGE)
        {
            if (nic_numb.getText().toString() == null || nic_numb.getText().toString().isEmpty()) {
                Utils.onError(R.string.empty_nic);
                return;
            }
        }

        Intent intent = AppHome.getIntented(Signup.this);
        Signup.this.startActivity(intent);

    }
}
