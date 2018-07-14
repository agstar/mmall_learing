package com.mmall.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class DateTimeUtil {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static Date strToDate(String dateTimeStr, String formatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatStr);
        TemporalAccessor parse = dateTimeFormatter.parse(dateTimeStr);
        return Date.from(LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String dateToStr(Date date, String formatStr) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        LocalDateTime dateTime = LocalDateTime.from(date.toInstant());
        return dateTime.format(DateTimeFormatter.ofPattern(formatStr));
    }

    public static Date strToDate(String dateTimeStr) {

        LocalDateTime dateTime = LocalDateTime.from(formatter.parse(dateTimeStr));
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String dateToStr(Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        LocalDateTime dateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        return dateTime.format(formatter);
    }


}
