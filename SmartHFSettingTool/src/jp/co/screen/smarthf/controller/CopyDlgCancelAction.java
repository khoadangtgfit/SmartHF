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

package jp.co.screen.smarthf.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import jp.co.screen.smarthf.view.copydgl.SmartHFCopyContentPnl;
import jp.co.screen.smarthf.view.copydgl.SmartHFCopyGroupBtnPnl;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

@SuppressWarnings("serial")
public class CopyDlgCancelAction extends AbstractAction {

	private SmartHFCopyGroupBtnPnl mParentContainer;
	
	/** 
	 * Constructor of CopyDlgCancelAction.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public CopyDlgCancelAction(SmartHFCopyGroupBtnPnl inParentContainer) {
		mParentContainer = inParentContainer;
	}


	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent arg0) {
		mParentContainer.getParentContainer().getParentContainer().dispose();
		
	}
}
