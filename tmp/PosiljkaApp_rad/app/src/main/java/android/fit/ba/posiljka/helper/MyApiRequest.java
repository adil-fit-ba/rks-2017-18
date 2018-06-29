package android.fit.ba.posiljka.helper;

import android.fit.ba.posiljka.viewmodels.PosiljkaPregledVM;
import android.os.AsyncTask;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class MyApiRequest {
    public static <T> void Get(final String urlPath, final MyRunnable<T>  onSuccess)
    {

        new AsyncTask<Void, Void, T>(){
            @Override
            protected T doInBackground(Void... voids) {
                try {
                    String jsonString = MyUrlConnection.Get(urlPath);
                    T t = MyGson.build().fromJson(jsonString, onSuccess.getType());
                    return t;
                }
                catch (Exception e)
                {
                    return null;
                }

            }

            @Override
            protected void onPostExecute(T t) {
                if (t != null)
                    onSuccess.run(t);

            }
        }.execute();
    }

    public static <T> void Post(final String urlPath, final MyRunnable<T>  onSuccess)
    {

        new AsyncTask<Void, Void, T>(){
            @Override
            protected T doInBackground(Void... voids) {
                String jsonString = MyUrlConnection.Get( urlPath);
                T t = MyGson.build().fromJson(jsonString, onSuccess.getType());
                return t;
            }

            @Override
            protected void onPostExecute(T t) {
                onSuccess.run(t);

            }
        }.execute();
    }
}
