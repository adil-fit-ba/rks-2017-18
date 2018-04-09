package ba.fit.app.hci_odbrana.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Adil on 9.5.2015.
 */
public class MyDate
{
    public static String to_dd_mm_yyyy(Date date)
    {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }
    public static String to_dd_mm_yyyy_hh_mm(Date date)
    {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date);
    }
    public static String to_day(Date date)
    {
        return new SimpleDateFormat("ddddd").format(date);
    }

    public static String to_dd_mm(Date date) {
        return new SimpleDateFormat("dd.MM.").format(date);
    }
}
