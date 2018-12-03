package com.itcast.ssm.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String,Date> {
    @Override
    public Date convert(String departureTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        if(departureTime!=null){
            try {
                date = format.parse(departureTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
}
