package app.coreproject.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ductbm
 */
public class DateUtil {

    private static final SimpleDateFormat DMY_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static String getDmyDateString(Date date) {
        try {
            return DMY_DATE_FORMAT.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static Date getDmyDateFromString(String dateStr) {
        try {
            return DMY_DATE_FORMAT.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }
}
