package com.mapubiev.summerschool.uemergency.helpers;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.mapubiev.summerschool.uemergency.App;
import com.mapubiev.summerschool.uemergency.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Claude Soue on 17/04/2017.
 */
public class Utils {


    public static void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(App.getContext().getResources().getString(R.string.unknown_error));
        }
    }

    private static void showSnackBar(String message) {
        View dummy_view = new View(App.getContext());
        Snackbar snackbar = Snackbar.make(dummy_view.findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(App.getContext(), R.color.white));
        snackbar.show();
    }


    public static void onError(@StringRes int resId) {
        onError(App.getContext().getResources().getString(resId));
    }

    /**
     * Gets a date string formatted and returns a Date element
     * @param dateString
     * @return Date
     */
    public static Date getDateFromString(String dateString)
    {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date myDate = null;
        try {
            myDate = df.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return myDate;
    }

    public static int getAge (int _year, int _month, int _day) {

        GregorianCalendar cal = new GregorianCalendar();
        int y, m, d, a;

        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(_year, _month, _day);
        a = y - cal.get(Calendar.YEAR);
        if ((m < cal.get(Calendar.MONTH))
                || ((m == cal.get(Calendar.MONTH)) && (d < cal
                .get(Calendar.DAY_OF_MONTH)))) {
            --a;
        }
        if(a < 0)
            throw new IllegalArgumentException("Age < 0");
        return a;
    }
}
