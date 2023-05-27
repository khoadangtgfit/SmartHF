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

package jp.co.screen.smarthf.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import jp.co.screen.smarthf.view.main.SmartHFGroupBtnPnl;

/**
 * Class Description
 * @author hoavanngo.fd
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFCloseAction extends AbstractAction{

	//** parent container
	private SmartHFGroupBtnPnl mParentContainer = null;
	
	/**
	 * 
	 * Constructor of LBRSAddAction.java
	 *
	 * @param inParent
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFCloseAction(SmartHFGroupBtnPnl inParent){
		mParentContainer = inParent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		mParentContainer.getParentContainer().getParentContainer().confirmExit();
	}

}