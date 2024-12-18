package com.ywq.simpleDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SimpleDateFormatTest {
    private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        String format = simpleDateFormatThreadLocal.get().format(calendar.getTime());
        System.out.println(format);
    }
}
