package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatUtils {

    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initValue() {
            return new SimpleDateFormat(Constant.TIME_FORMAT);
        }
    };

    public static String safeFormatDate(Date date) {
        return threadLocal.get().format(date);
    }

    public static Date safeparswDate(String date) {
        try {
            return threadLocal.get().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
