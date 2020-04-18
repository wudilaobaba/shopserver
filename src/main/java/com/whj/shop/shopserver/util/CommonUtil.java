package com.whj.shop.shopserver.util;

import com.whj.shop.shopserver.bo.PageCounter;

import java.util.Calendar;
import java.util.Date;

public class CommonUtil {

    public static PageCounter converToPageParameter(Integer start, Integer count) {
        int pageNum = start / count;

        PageCounter pageCounter = PageCounter.builder()
                .page(pageNum)
                .count(count)
                .build();
        return pageCounter;
    }

    public static Boolean isInTimeLine(Date date, Date start, Date end) {
        Long time = date.getTime();
        Long startTime = start.getTime();
        Long endTime = end.getTime();
        if(time > startTime && time <endTime){
            return true;
        }
        return false;
    }

    public static Calendar addSomeSeconds(Calendar calendar, int seconds) {
        calendar.add(Calendar.SECOND, seconds);
        return calendar;
    }

    //period 单位：秒
    public static Boolean isOutOfDate(Date startTime, Long period) {
        Long now = Calendar.getInstance().getTimeInMillis();
        Long startTimeStamp = startTime.getTime();
        Long periodMillSecond = period * 1000;
        if (now > (startTimeStamp + periodMillSecond)) {
            return true;
        }
        return false;
    }
}
