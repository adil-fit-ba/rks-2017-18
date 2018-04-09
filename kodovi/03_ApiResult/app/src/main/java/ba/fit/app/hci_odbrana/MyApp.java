package ba.fit.app.hci_odbrana;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

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
public class MyApp extends Application
{

    private static Context context;

    public static Context getContext()
    {
        return context;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }

    private Activity mCurrentActivity = null;
    public Activity getCurrentActivity(){
        return mCurrentActivity;
    }
    public void setCurrentActivity(Activity mCurrentActivity){
        this.mCurrentActivity = mCurrentActivity;
    }
}
