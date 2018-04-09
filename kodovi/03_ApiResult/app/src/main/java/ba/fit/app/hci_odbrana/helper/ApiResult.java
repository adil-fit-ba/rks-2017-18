package ba.fit.app.hci_odbrana.helper;

import java.util.Date;

/**
 * Created by Adil Joldic on 17.06.2017..
 */

public class ApiResult<T> {
    public String errorMessage = "";
    public boolean isException = false;
    public int exceptionCode = 0;
    public T value;
}