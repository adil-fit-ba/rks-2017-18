package app.fit.ba.posiljka.helper;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;


public class Util {

    public static void otvoriFragmentKaoReplace(Activity activity, int id, Fragment fragment)
    {
        final FragmentManager fm = activity.getFragmentManager();
        final FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }

    public static void otvoriFragmentKaoDijalog(Activity activity, DialogFragment fragment) {
        final FragmentManager fm = activity.getFragmentManager();
        fragment.show(fm, "tag");
    }
}
