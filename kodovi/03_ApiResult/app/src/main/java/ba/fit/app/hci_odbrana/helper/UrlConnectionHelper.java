package ba.fit.app.hci_odbrana.helper;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

import ba.fit.app.hci_odbrana.helper.api.OdjeljenjeIndexVM;

import static ba.fit.app.hci_odbrana.helper.StreamTools.readStream;

public class UrlConnectionHelper {
    public static OdjeljenjeIndexVM Get(String s, Type type) {

        HttpURLConnection urlConnection ;
        try {
            URL url = new URL(s);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String povratnaVrijednost = readStream(in);

            OdjeljenjeIndexVM returnDataObject
                    = MyGson.build().fromJson(povratnaVrijednost, OdjeljenjeIndexVM.class);
            urlConnection.disconnect();
            return returnDataObject;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
