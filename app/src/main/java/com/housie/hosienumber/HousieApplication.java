package com.housie.hosienumber;

import android.app.Application;
import android.content.Context;

public class HousieApplication extends Application {

   private static  Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context=getApplicationContext();

    }


    public static Context getContext()
    {
        return context;
    }
}
