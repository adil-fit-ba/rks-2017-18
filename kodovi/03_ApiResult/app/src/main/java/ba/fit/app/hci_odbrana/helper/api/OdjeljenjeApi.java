package ba.fit.app.hci_odbrana.helper.api;


import java.util.List;

import ba.fit.app.hci_odbrana.helper.ApiRequest;
import ba.fit.app.hci_odbrana.helper.ApiTask;
import ba.fit.app.hci_odbrana.helper.Config;

/**
 * Created by adil_000 on 23.3.2015.
 */
public class OdjeljenjeApi
{
    public static final String CONTROLLER = "Odjeljenje";

    public static void Index(ApiTask<OdjeljenjeIndexVM> task)
    {
        String url = Config.baseUrl
                .buildUpon()
                .appendPath(CONTROLLER)
                .appendPath("Predmeti")
                .appendQueryParameter("razred", "")
                .toString();


        ApiRequest.Get(url, task);
    }

    public static void GetNastavnici(ApiTask<OdjeljenjeNastavniciVM> task)
    {
        String url = Config.baseUrl
                .buildUpon()
                .appendPath(CONTROLLER)
                .appendPath("GetNastavnici")
                .toString();


        ApiRequest.Get(url, task);
    }

    public static void Save(OdjeljenjeSaveVM input, ApiTask<Void> task)
    {
        String url = Config.baseUrl
                .buildUpon()
                .appendPath(CONTROLLER)
                .appendPath("Save")
                .toString();


        ApiRequest.Post(url, input, task);
    }
}
