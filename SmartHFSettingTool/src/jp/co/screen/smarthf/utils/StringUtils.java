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

import java.text.DecimalFormat;

import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.swc.util.ProhibitConverter;

/**
 * Class Description
 * @author khath
 * @since EQUIOS V2.02 EQ203 EQF#C492
 */

public class StringUtils {
	/**
     * Method to concatenate given strings into one
     * 
     * @param sts
     * @return concatenated strings
     */
    public static String concat(String... sts) {
        StringBuilder sb = null;
        if (sts != null) {
            sb = new StringBuilder();
            for (String st : sts) {
                sb.append(st);
            }
            return sb.toString();
        }
        return null;

    }

    /**
     * 
     * Method to check whether a given string is null or empty or not
     * 
     * @param st
     *            :String to check
     * @return true if it is or false otherwise
     * @since v1.0
     */
    public static boolean isBlankOrNull(String st) {
        return st == null || st.trim().length() == 0 ? true : false;
    }

    /**
     * Method to check whether a given string is contain space or not
     * 
     * @param st
     * @return
     */
    public static boolean isContainSpace(String st) {
        if (!isBlankOrNull(st)) {
            if (st.contains(Constants.BLANK)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to remove doubleslash from given string
     * 
     * @param st
     * @return
     */
    public static String removeDoubleSlash(String st) {
        String s1 = st;
        if (!isBlankOrNull(st)) {
            while (s1.startsWith(Constants.DOUBLE_SLASH)) {
                s1 = s1.substring(1);
            }
        }
        return s1;

    }

    /**
     * Method to check whether two given strings are equal or not. Also check null or empty
     * 
     * @param st1
     * @param st2
     * @return
     */
    public static boolean equals(String st1, String st2) {
        return isBlankOrNull(st1) || isBlankOrNull(st2) ? false : st1.equalsIgnoreCase(st2);

    }

    /**
     * An overloading of method {@link StringUtils#getValueAsInt(String, int)} with default value is
     * Zero
     * 
     * @param st
     * @return int
     * @since v1.0
     */
    public static int getValueAsInt(String st) {
        return getValueAsInt(st, 0);

    }

    /**
     * Method to get given string as Integer value. If the string is not an Integer then return
     * default value
     * 
     * @param st
     * @param defaultValue
     * @return int
     * @since v1.0
     */
    public static int getValueAsInt(String st, int defaultValue) {
        int retVal = defaultValue;
        if (!StringUtils.isBlankOrNull(st)) {
            try {
                retVal = Integer.valueOf(st.trim());
            } catch (NumberFormatException e) {
                retVal = defaultValue;
            }

        }
        return retVal;

    }

    /**
     * Round given double to have digit
     * 
     * @param d
     * @param digit "#.#" or "#.##"
     * @return double
     * @since v1.0
     */
    
    
    public static String roundToDecimals(String d, String digit) {
        String result = "";
        DecimalFormat DForm = new DecimalFormat(digit);
        if (d!=null && !d.equals("")) {
            double value = Double.valueOf(DForm.format(Double.parseDouble(d)));
            if (Math.abs(value) == 0) {
                value = 0d;
            }
            result = String.valueOf(value);
        }
        return result;
    }
    public static Double roundToDecimals(Double d, String digit) {
        Double value = 0.0;
        DecimalFormat DForm = new DecimalFormat(digit);
        if (d!=null && !d.equals("")) {
            value = Double.valueOf(DForm.format(d));
            if (Math.abs(value) == 0) {
                value = 0d;
            }
        }
        return value;
    }

    /**
     * 
     * compare String
     * EQF#C378
     * @param str1
     * @param str2
     * @return
     * @author phuongvn GCS
     * @since EQUIOS V2.00EQ003T1
     * @version EQUIOS V2.00EQ003T1
     */
    public static boolean compare(String str1, String str2){
    	if(str1 == null && str2 == null)return true;
    	if(str1 == null || str2 == null) return false;
    	return str1.equals(str2)? true: false;
    	
    }
    
    /**
     * 
     * compare Boolean
     * EQF#C378
     * @param str1
     * @param str2
     * @return
     * @author phuongvn GCS
     * @since EQUIOS V2.00EQ003T1
     * @version EQUIOS V2.00EQ003T1
     */
    public static boolean compare(Boolean str1, Boolean str2){
    	if(str1 == null && str2 == null)return true;
    	if(str1 == null || str2 == null) return false;
    	return str1 == str2? true: false;
    	
    }

	public static String checkProhibited(String inProhibitedText) {
		ProhibitConverter converter = new ProhibitConverter();
		String[] listProhibited = converter.getProhibitStrList();
		String result = Constants.EMPTY_STRING;
//		for(String prohibitedChar : listProhibited) {
		for (int idx = 0; idx < listProhibited.length; idx++) {
			if(inProhibitedText.contains(listProhibited[idx])) {
				if (idx != 0) {
					result += Constants.COMMA_STRING + Constants.SPACE_STRING;
				}
				
				result += listProhibited[idx];
			}
		}
		return result;
	}
	
	public static boolean checkSpaceAndPeriodAtStartAnEnd(String inString) {
		if (inString != null && !inString.isEmpty()) {
			String firstIndex = String.valueOf(inString.charAt(0));
			String lastIndex = String.valueOf(inString.charAt(inString.length() - 1));
			if (firstIndex.equals(Constants.SPACE_STRING) || firstIndex.equals(Constants.PERIOD_STRING)
					|| lastIndex.equals(Constants.SPACE_STRING)
					|| lastIndex.equals(Constants.PERIOD_STRING)) {
				return true;
			}
		}
		
		return false;
	}

}
