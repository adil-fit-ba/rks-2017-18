package android.fit.ba.posiljka.helper;

/**
 * Created by Adil Joldic on 17.06.2017..
 */

public class MyResult<T> {
    public String errorMessage = "";
    public boolean isException = false;
    public int exceptionCode = 0;
    public T value;
}