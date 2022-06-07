package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateUtils {

    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        TimeZone timezone = TimeZone.getDefault();
        calendar.setTimeZone(timezone);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        if(month.length() == 1) {
            month = "0"+month;
        }
        String day = String.valueOf(calendar.get(Calendar.DATE));
        if(day.length() ==  1) {
            day = "0" + day;
        }
        return year + "-"+ month + "-"+ day;
    }

    public static String getDayOfWeek(String date, String dateFormat) {
        DateFormat format = new SimpleDateFormat(dateFormat);
        String week = null;
        try {
            week = format.parse(date).toString().split(" ")[0].toUpperCase();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return week;
    }

    public static void main(String[] args) {
        System.out.println(getDayOfWeek("2021-09-08","yyyy-MM-dd"));
    }
}
