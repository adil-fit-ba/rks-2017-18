package android.fit.ba.posiljka.helper;


public class MyFragmentUtils
{

    public static void openAsReplace(android.app.Activity activity, int id, android.support.v4.app.Fragment fragment)
    {

        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }

    public static void openAsDialog(android.app.Activity activity, android.app.DialogFragment dlg)
    {
        android.app.FragmentManager fm = activity.getFragmentManager();
        dlg.show(fm, "nekitag");
    }
}
