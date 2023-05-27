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
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.smarthf.utils.CommonUtils;
import jp.co.screen.smarthf.view.property.SmartHFPropertyMacroRowPnl;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class PropertyFrmAddAction extends AbstractAction {

	private SmartHFPropertyMacroRowPnl mParentContainer;
	
	/** 
	 * Constructor of PropertyFrmAddAction.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public PropertyFrmAddAction(SmartHFPropertyMacroRowPnl inParentContainer) {
		mParentContainer = inParentContainer;
	}

	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent arg0) {
		List<String> macroList = CommonUtils.getRuleEngineMacroList();
		mParentContainer.getParentContainer().addMacroRow(new SmartHFPropertyMacroRowPnl(mParentContainer.getParentContainer(), macroList, Constants.EMPTY_STRING, Constants.EMPTY_STRING));
	}
}
