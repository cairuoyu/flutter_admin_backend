package com.cry.flutter.admin.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CryStringUtil {
    public static String genStringByTime(){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");//设置日期格式
        Random r = new Random();
        String newsNo =  LocalDateTime.now().format(fmt)+ r.nextInt();
        return newsNo;
    }
}
