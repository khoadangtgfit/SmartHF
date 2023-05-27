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
import java.util.List;

import javax.swing.AbstractAction;

import jp.co.screen.smarthf.common.resource.LangKeySmartHF;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.utils.CommonUtils;
import jp.co.screen.smarthf.utils.JOptionPaneUtils;
import jp.co.screen.smarthf.view.copydgl.SmartHFCopyDlg;
import jp.co.screen.smarthf.view.main.SmartHFGroupBtnPnl;


/**
 * Class Description
 * @author hoavanngo.fd
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFCopyAction extends AbstractAction{

	
	//** parent container
	private SmartHFGroupBtnPnl mParentContainer = null;
	
	/**
	 * 
	 * Constructor of LBRSAddAction.java
	 *
	 * @param inParent
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFCopyAction(SmartHFGroupBtnPnl inParent){
		mParentContainer = inParent;
	}
	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		List<String> otherFileList = CommonUtils.getOtherFileList(mParentContainer.getParentContainer().getMainContentPane().getSmartHFSelectedRowData().getSmartHFPropertyFileModel().getDisplayName());
		if (otherFileList.size() > 0) {
			String errMsg = SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_CANT_COPY_HF);
			JOptionPaneUtils.showErrorDialog(mParentContainer.getParentContainer().getParentContainer(), errMsg);
			
			return;
		}
		
		new SmartHFCopyDlg(mParentContainer);
	}
	
	
}
