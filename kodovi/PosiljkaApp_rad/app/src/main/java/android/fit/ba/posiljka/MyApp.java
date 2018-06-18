package android.fit.ba.posiljka;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Adil on 11.5.2015.
 *
 * obavezno navesti app name u android-manifest.xml
 *
 *  <application
 android:allowBackup="true"
 android:name=".MyApp"
 *
 */
public class MyApp extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }


}
