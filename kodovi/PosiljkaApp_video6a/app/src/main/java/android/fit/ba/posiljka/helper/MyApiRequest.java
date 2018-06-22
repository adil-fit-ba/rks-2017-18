package android.fit.ba.posiljka.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.fit.ba.posiljka.data.PosiljkaPregledVM;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.lang.reflect.Type;

public class MyApiRequest {
    public static <T> void get(final Activity activity, final String urlAction, final MyRunnable<T> myCallback) {


        new AsyncTask<Void, Void, MyApiResult>() {
            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(activity, "Loading", "Sačekajte...");
            }


            @Override
            protected MyApiResult doInBackground(Void... voids) {

                MyApiResult result = MyUrlConnection.Get(MyConfig.baseUrl + urlAction);



                return result;
            }

            @Override
            protected void onPostExecute(MyApiResult result) {


                progressDialog.dismiss();



                if (result.isException)
                {
                    View parentLayout = activity.findViewById(android.R.id.content);
                    Snackbar snackbar;
                    if (result.resultCode == 0)
                    {
                        snackbar = Snackbar.make(parentLayout, "Greška u komunikaciji sa serverom.", Snackbar.LENGTH_LONG);

                    }
                    else
                    {
                        snackbar = Snackbar.make(parentLayout, "Greška " + result.resultCode + ": " + result.errorMessage, Snackbar.LENGTH_LONG);
                    }
                    snackbar.setAction("Ponovi", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyApiRequest.get(activity, urlAction, myCallback);
                        }
                    });
                    snackbar.show();
                }else
                {

                    Type genericType = myCallback.getGenericType();

                    try {
                        T x = MyGson.build().fromJson(result.value, genericType);

                        myCallback.run(x);
                    }catch (Exception e)
                    {
                        View parentLayout = activity.findViewById(android.R.id.content);
                        Snackbar.make(parentLayout, "Greška u aplikaciji. ", Snackbar.LENGTH_LONG).show();
                    }

                }
            }
        }.execute();

    }

    public static void delete(Activity activity, String urlAction, MyRunnable myRunnable) {
    }
}
