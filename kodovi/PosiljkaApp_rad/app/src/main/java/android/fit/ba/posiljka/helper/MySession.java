package android.fit.ba.posiljka.helper;

import android.content.SharedPreferences;
import android.fit.ba.posiljka.MyApp;
import android.fit.ba.posiljka.viewmodels.KorisnikPregledVM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Adil on 11.5.2015.
 */
public class MySession
{
    private static final String PREFS_NAME = "DatotekaZaSharedPrefernces";
    public static final String KEY_LOGIRANI_KORISNIK = "KEY_LOGIRANI_KORISNIK";

    public static KorisnikPregledVM.Row getKorisnik()
    {
        SharedPreferences settings = MyApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        String str = settings.getString(KEY_LOGIRANI_KORISNIK, "");
        if (str.length() == 0)
        {
            return null;
        }
        return MyGson.build().fromJson(str, KorisnikPregledVM.Row.class);
    }

    public static void setUpisanRazredID(KorisnikPregledVM.Row korisnik)
    {

        final String str = korisnik!=null?MyGson.build().toJson(korisnik):"";
        SharedPreferences settings = MyApp.getContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_LOGIRANI_KORISNIK, str);
        editor.apply();
    }

}
