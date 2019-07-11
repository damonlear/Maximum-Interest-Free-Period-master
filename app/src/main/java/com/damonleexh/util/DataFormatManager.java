package com.damonleexh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatManager {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate(Date date)throws ParseException {
        synchronized(sdf){
            return sdf.format(date);
        }
    }

    public static Date parse(String strDate) throws ParseException{
        synchronized(sdf){
            return sdf.parse(strDate);
        }
    }
}
