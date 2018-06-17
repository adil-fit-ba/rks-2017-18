package android.fit.ba.posiljka.helper.volley;

import java.lang.reflect.ParameterizedType;

public abstract class OnSuccessListener<TResult> {
    public abstract void onSuccess(TResult value);

    public Class<TResult> getType()
    {
        Class<TResult> persistentClass = (Class<TResult>)
                ((ParameterizedType)getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];

        return persistentClass;
    }
}
