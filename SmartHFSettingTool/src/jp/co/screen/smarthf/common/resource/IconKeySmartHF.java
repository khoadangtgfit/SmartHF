/*
 * Copyright (C) 2008-2012 Dainippon Screen Mfg. Co., Ltd.
 * CONFIDENTIAL Proprietary to Dainippon Screen Mfg. Co., Ltd.
 * 
 * �{�v���O�����̒��쌠�͑���{�X�N���[������������ЂɋA��������̂ł���A
 * ���Ђ͂�����c�Ɣ閧�Ƃ��ĊǗ�������̂ł��B�]���A�{�v���O�����̑S�āA
 * �ꕔ�ɂ�����炸�A���̕����A�Еz���s�����Ƃ́A���Ђ̎��O�̏��ʂɂ��
 * �������Ȃ�����ł��ւ�������̂ł��B
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
