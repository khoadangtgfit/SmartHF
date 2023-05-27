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

package jp.co.screen.smarthf.common.log;
import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.swc.util.log.LoggerAlreadyExistException;
import jp.co.screen.swc.util.log.LoggerHolder;

import org.apache.log4j.PropertyConfigurator;


/**
 * Logger
 *  
 * @author hoavanngo.fd
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFLogger {
	
	/**
	 * init logger
	 * @author hoavanngo.fd
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public static void init(){
	  PropertyConfigurator.configure(Constants.LOG4J_CONFIG_FILE);
	  try {
	      LoggerHolder.setLogger(new NullLogger());
	  } catch (LoggerAlreadyExistException e) {
	      e.printStackTrace();
	  }
	}
}

