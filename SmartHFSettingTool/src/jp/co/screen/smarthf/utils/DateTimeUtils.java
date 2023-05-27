/*
 * Copyright (C) 2008-2012 Dainippon Screen Mfg. Co., Ltd.
 * CONFIDENTIAL Proprietary to Dainippon Screen Mfg. Co., Ltd.
 * 
 * 本プログラムの著作権は大日本スクリーン製造株式会社に帰属するものであり、
 * 同社はこれを営業秘密として管理するものです。従い、本プログラムの全て、
 * 一部にかかわらず、その複製、頒布を行うことは、同社の事前の書面による
 * 承諾がない限り固く禁じられるものです。
 * 
 * The copyright of this program shall belong to
 * Dainippon Screen Mfg. Co., Ltd.("SCREEN") as a "work made for hire."
 * Also, SCREEN will treat this program as its trade secret. Accordingly,
 * no one is allowed to copy and/or distribute this program, as a whole or
 * in part, without obtaining SCREEN' prior permission to do so in writing.
 */

package jp.co.screen.smarthf.utils;

/**
 * Class Description
 * EQF#C157 
 * @author syptn
 * @since EQUIOS V1.04 T5
 * @version EQUIOS V1.04 T5
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtils {
    public static TimeZone TIMEZONE_GMT = TimeZone.getDefault();
    
    public static String getCurTime()
    {
        long timestamp = Calendar.getInstance().getTime().getTime(); 
        return getTime(timestamp);
    }
        
        
    public static String getCurTime_2 ()
    {        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TIMEZONE_GMT);
        String strTime = dateFormat.format(new Date()).toString();      
        
        return strTime;
    }
        
    public static String getTime(long timestamp)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TIMEZONE_GMT);
        String strTime = dateFormat.format(new Date(timestamp)).toString();      
        
        return strTime;
    }
    
    public static long getCurTimeStamp()
    {
        return  Calendar.getInstance().getTime().getTime(); 
    }
}