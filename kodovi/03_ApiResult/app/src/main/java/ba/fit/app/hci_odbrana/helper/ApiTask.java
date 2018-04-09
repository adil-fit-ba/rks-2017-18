package ba.fit.app.hci_odbrana.helper;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by Adil on 11.5.2015.
 */
public abstract class ApiTask<T> implements Serializable
{
    public abstract void run(boolean isCommunicationOrParseError, boolean isException, int exceptionCode, String message, T value);

    Class<T> getType()
    {
        Class<T> persistentClass = (Class<T>)
                ((ParameterizedType)getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0];

        return persistentClass;
    }
}
