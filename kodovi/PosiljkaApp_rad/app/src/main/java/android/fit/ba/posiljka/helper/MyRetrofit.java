package android.fit.ba.posiljka.helper;

import retrofit2.Retrofit;

public class MyRetrofit {
    public static Retrofit getInstance()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyConfig.baseUrl.toString())
                .build();

        return retrofit;
    }
}
