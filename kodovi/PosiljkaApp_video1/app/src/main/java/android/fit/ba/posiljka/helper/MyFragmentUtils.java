package android.fit.ba.posiljka.helper;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.fit.ba.posiljka.fragments.PosiljkaListFragment;

public class MyFragmentUtils {
    public static void openAsReplace(Activity activity, int id, Fragment fragment) {

        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }


}
