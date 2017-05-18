package com.mapubiev.summerschool.uemergency;

import android.app.Application;
import android.content.Context;

/**
 * Created by Claude Soue on 17/04/2017.
 */
public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
