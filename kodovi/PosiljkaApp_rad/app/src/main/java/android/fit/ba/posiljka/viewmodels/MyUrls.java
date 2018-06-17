package android.fit.ba.posiljka.viewmodels;

import android.fit.ba.posiljka.helper.MyConfig;

public class MyUrls {
    public static String PosiljkaPregled(){
        return MyConfig.baseUrl.buildUpon()
                .appendPath("Posiljka")
                .appendPath("GetAll")
                .toString();


    }
}
