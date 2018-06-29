package android.fit.ba.posiljka.api;

import android.fit.ba.posiljka.helper.MyConfig;
import android.fit.ba.posiljka.helper.MyRetrofit;
import android.fit.ba.posiljka.helper.MyRunnable;
import android.fit.ba.posiljka.viewmodels.OpstinaPregledVM;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class OpstinaApi
{
    public static final String CONTROLLER = "Opstina";

    public static void Pregled(MyRunnable<OpstinaPregledVM> onSuccess) {
        String url = MyConfig.baseUrl
                .buildUpon()
                .appendPath(CONTROLLER)
                .appendPath("Pregled")
                .toString();



        PregledInterface service = MyRetrofit.getInstance().create(PregledInterface.class);

        service.Pregled().enqueue(new Callback<OpstinaPregledVM>() {
            @Override
            public void onResponse(Call<OpstinaPregledVM> call, Response<OpstinaPregledVM> response) {
                try {
                    response.errorBody().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<OpstinaPregledVM> call, Throwable t) {

            }
        });

    }

    public  interface PregledInterface {
        @GET(CONTROLLER + "/Pregled")
        Call<OpstinaPregledVM> Pregled();
    }
}
