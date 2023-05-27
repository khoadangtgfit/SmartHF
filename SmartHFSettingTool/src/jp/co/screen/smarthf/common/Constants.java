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

package jp.co.screen.smarthf.common;

import java.io.File;

/**
 * Constants params
 * @author hoavanngo.fd
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class Constants {
	
	/** The Constant APPLICATION_DIR. */
	  private static final String APPLICATION_DIR=System.getProperty("user.dir")+File.separatorChar;
	
	  /** The Constant LOG4J_CONFIG_FILE. */
	  public static final String LOG4J_CONFIG_FILE = APPLICATION_DIR + "tool/lib/SmartHFSettingTool/"+ "log4j.properties";
	
	  /** The Constant INI_CONFIG_FILE. */
	  public static final String INI_CONFIG_FILE = APPLICATION_DIR + "tool/lib/SmartHFSettingTool/"+  "SmartHFSettingTool.ini";
	  
	  /** The Constant KEY_LOCALE. */
	  public static final String KEY_LOCALE = "Locale";
	  
	  /** The Constant SINGLE_INSTANCE_LOCKER_FILE. */
	  public static final String SINGLE_INSTANCE_LOCKER_FILE = APPLICATION_DIR + "shfst.lock";
	  
	  /** The Constant KEY_SYS_TOOL_SERVICECTRL. */
	  public static final String KEY_SYS_TOOL_SERVICECTRL = "sys.tool.servicectrl";

	  /** Window size **/
	  public static final String WINDOW_WIDTH= "window.width";
	  public static final String WINDOW_HEIGHT= "window.height";
	  
	  /** Window size default **/
	  public static final int WINDOW_WIDTH_DEFAULT= 600;
	  public static final int WINDOW_HEIGHT_DEFAULT= 400;

	public static final String EMPTY_STRING = "";

	public static final String UTF8_ENCODING = "UTF8";

	public static final String EQUAL = "=";

	public static final String CRLF = "\r\n";

	public static final String SLASH = "/";

	public static final String DOUBLE_BACK_SLASH = "\\";

	public static final CharSequence BLANK = " ";

	public static final String DOUBLE_SLASH = "\\";
	
	public static final String SPACE_STRING = " ";
	
	public static final String PERIOD_STRING = ".";
	
	public static final String COLON_STRING = ":";
	
	public static final String ASTERISK_STRING = "*";
	
	public static final String COMMA_STRING = ",";
	
	public static final String RULE_PROPERTY_FILE = ".ruleProperty";

	public static final String PROPERTY_FILE = ".property";

	public static final String KEY_SCRIPT = "Script";

	public static final String KEY_RULE = "Rule";
  
	public static final int MAX_NEW_ROWS = 4;
	
	public static final String TRUE_STRING = "true";
	
}
