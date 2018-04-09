package ba.fit.app.hci_odbrana.helper;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

import ba.fit.app.hci_odbrana.MyApp;
import ba.fit.app.hci_odbrana.helper.volley.GsonRequest;
import ba.fit.app.hci_odbrana.helper.volley.MySingleton;


/**
 * Created by Adil Joldic on 17.06.2017..
 */

public class ApiRequest {
    public static <T> void Get(final String url, final ApiTask<T> result)
    {
        Response.Listener<ApiResult<T>> successListener = new Response.Listener<ApiResult<T>>() {
            @Override
            public void onResponse(ApiResult<T> response) {
                result.run(false, response.isException, response.exceptionCode, response.errorMessage, response.value);
                Log.w("ApiRequest", "end-ok api: " + url);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                //if (error != null)
                    result.run(true, false, 0, "connection error za " + url, null);
                Log.w("ApiRequest", "end-error api: " + url);
            }
        };

        Log.w("ApiRequest", "start api: " + url);
        //final Type t = new TypeToken<ApiResult<T>>(){}.getType();

        Gson gson = MyGson.builder().registerTypeAdapter(ApiResult.class, new JsonDeserializer<ApiResult<T>>(){
            @Override
            public ApiResult<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject jObject = json.getAsJsonObject();

                ApiResult<T> x = new ApiResult<T>();
                x.exceptionCode = jObject.get("exceptionCode").getAsInt();
                x.errorMessage = jObject.get("exceptionMessage").getAsString();
                x.isException = jObject.get("isException").getAsBoolean();

                JsonElement jsonElement = jObject.get("value");
                if(!jsonElement.isJsonNull()) {
                    JsonObject valueObject = jsonElement.getAsJsonObject();
                    x.value = context.deserialize(valueObject, result.getType());
                }
                return x;
            }
        }).create();

        GsonRequest<ApiResult<T>> gsonRequest = new GsonRequest<>(url, ApiResult.class, null, null, successListener, errorListener, Request.Method.GET, gson);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }
}
