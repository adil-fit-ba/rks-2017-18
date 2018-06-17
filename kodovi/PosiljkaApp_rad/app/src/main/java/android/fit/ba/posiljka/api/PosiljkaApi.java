package android.fit.ba.posiljka.api;

import android.fit.ba.posiljka.helper.MyApiRequest;
import android.fit.ba.posiljka.helper.MyConfig;
import android.fit.ba.posiljka.helper.MyRunnable;
import android.fit.ba.posiljka.viewmodels.PosiljkaPregledVM;

public class PosiljkaApi {
    public static final String CONTROLLER = "Posiljka";

    public static void GetAll(MyRunnable<PosiljkaPregledVM> model)
    {
        String url = MyConfig.baseUrl.buildUpon()
                .appendPath(CONTROLLER)
                .appendPath("GetAll")
                .toString();

        MyApiRequest.Get(url, model);
    }
}
