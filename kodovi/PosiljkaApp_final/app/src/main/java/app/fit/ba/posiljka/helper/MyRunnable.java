package app.fit.ba.posiljka.helper;

import java.io.Serializable;


public interface MyRunnable<T> extends Serializable
{
    void  run(T t);
}

