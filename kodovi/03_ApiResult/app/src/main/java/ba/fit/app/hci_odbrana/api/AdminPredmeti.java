package ba.fit.app.hci_odbrana.api;


import ba.fit.app.hci_odbrana.helper.ApiRequest;
import ba.fit.app.hci_odbrana.helper.ApiTask;
import ba.fit.app.hci_odbrana.helper.Config;

/**
 * Created by adil_000 on 23.3.2015.
 */
public class AdminOdjeljenjaApi
{
    public static final String CONTROLLER = "AdminOdjeljenja";

    public static void GetOcjene(ApiTask<AdminOdjeljenjaVM> task)
    {
        String url = Config.baseUrl
                .buildUpon()
                .appendPath(CONTROLLER)
                .appendPath("Predmeti")
                .appendQueryParameter("razred", "1")
                .toString();


        ApiRequest.Get(url, task);
    }

}
