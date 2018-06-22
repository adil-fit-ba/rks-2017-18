package android.fit.ba.posiljka.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.fit.ba.posiljka.data.KorisnikPregledVM;

public class MySession {
    private static final String PREFS_NAME = "DatotekaZaSharedPrefernces";
    private static String nekikey="Key_korisnik";

    public static KorisnikPregledVM.Row getKorisnik()
    {
        SharedPreferences sharedPreferences = MyApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String strJson = sharedPreferences.getString(nekikey, "");
        if (strJson.length() == 0)
            return null;

        KorisnikPregledVM.Row x = MyGson.build().fromJson(strJson, KorisnikPregledVM.Row.class);
        return x;
    }

    public static void setKorisnik(KorisnikPregledVM.Row x)
    {
        String strJson = x!=null? MyGson.build().toJson(x):"";

        SharedPreferences sharedPreferences = MyApp.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nekikey, strJson);
        editor.apply();
    }
}
