package app.coreproject.util;

import java.util.Calendar;
import java.util.Date;

public class CommonDate {
    public static Date getDateNow() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        return now;
    }

}
