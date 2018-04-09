package ba.fit.app.hci_odbrana.helper.volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

import ba.ism.ednevnik.helper.ApiResult;
import ba.ism.ednevnik.helper.MyGson;


/**
 * Preuzeto sa
 * https://developer.android.com/training/volley/request-custom.html
 */
public class GsonRequest<T> extends JsonRequest<T>
{
    private final Gson gson; //izmjenjeo by Adil
    private final Type clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param get
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(String url, Type clazz, Map<String, String> headers, String json,
                       Response.Listener<T> listener, Response.ErrorListener errorListener, int get, Gson gson) {
        super(get, url, json, listener, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.gson =gson;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError
    {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {

        Log.w("parseNetworkResponse", "deliverResponse\n");
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String json=null;
        try {
            json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.w("parseNetworkResponse", "starting parsing\n" + json);
            T result = gson.fromJson(json, clazz);
            Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);


            Response<T> success = Response.success(result, cacheEntry);
            Log.w("parseNetworkResponse", "finished parsing\n");
            return  success;
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}