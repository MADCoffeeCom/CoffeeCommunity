package com.example.coffeecom.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDateTime {
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
    private static SimpleDateFormat formatter;


    public static String convertDatetoStringDate (Date date){
        String dateStr = DATE_FORMAT.format(date);
        return dateStr;
    }

    public static String convertDatetoStringTime (Date date){
        String dateStr = new SimpleDateFormat("hh:mm").format(date);
        return dateStr;
    }

    public static Date convertStringtoDate (String date) throws ParseException {
        Date dateDate = DATE_FORMAT.parse(date);
        return dateDate;
    }


}
