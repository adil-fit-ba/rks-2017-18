package ba.fit.app.hci_odbrana.helper.api;


import ba.fit.app.hci_odbrana.helper.ApiRequest;
import ba.fit.app.hci_odbrana.helper.ApiTask;
import ba.fit.app.hci_odbrana.helper.Config;

/**
 * Created by adil_000 on 23.3.2015.
 */
public class AdminOdjeljenjaApi
{
    public static final String CONTROLLER = "AdminHome";

    public static void Predmeti(ApiTask<AdminOdjeljenjaVM> task)
    {
        String url = Config.baseUrl
                .buildUpon()
                .appendPath(CONTROLLER)
                .appendPath("Predmeti")
                .appendQueryParameter("a", "1")
                .appendQueryParameter("a", "2")
                .appendQueryParameter("b", "4")
                .toString();


        ApiRequest.Get(url, task);
    }

}
