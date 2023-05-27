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

package jp.co.screen.smarthf.common.resource;

import jp.co.screen.equios.ui.config.ClientConfigMap;

/**
 * Class Description
 * @author hoavanngo.fd
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public interface IconKeySmartHF {
	 String CURRENT_PATH = ClientConfigMap.getCanonicalPath(ClientConfigMap.getSysDirResource()
	            + "/icon")
	            + System.getProperty("file.separator");

	    String LBRS_ICON= CURRENT_PATH+"SmartHFSettingTool_Icon.png";
	    
	    String ADDMACRO_ICON= CURRENT_PATH + "Add_Icon.png";
	    
	    String DELETEMACRO_ICON= CURRENT_PATH + "Delete_Icon.png";
}
