package android.fit.ba.posiljka.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.fit.ba.posiljka.data.PosiljkaPregledVM;
import android.os.AsyncTask;

import java.lang.reflect.Type;

public class MyApiRequest {
    public static <T> void get(final Activity activity, final String urlAction, final MyRunnable<T> myCallback) {


        new AsyncTask<Void, Void, T>() {
            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(activity, "Loading", "Sačekajte...");
            }


            @Override
            protected T doInBackground(Void... voids) {

                String strJson = MyUrlConnection.Get(MyConfig.baseUrl + urlAction);
                Type genericType = myCallback.getGenericType();
                T x = MyGson.build().fromJson(strJson, genericType);


                return x;
            }

            @Override
            protected void onPostExecute(T x) {

                progressDialog.dismiss();

            }
        }.execute();

    }
}
