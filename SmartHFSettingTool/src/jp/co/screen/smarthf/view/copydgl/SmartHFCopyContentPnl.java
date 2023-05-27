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

package jp.co.screen.smarthf.view.copydgl;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.utils.CommonUtils;
import jp.co.screen.swc.util.NameUtilities;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFCopyContentPnl extends JPanel {

	private SmartHFCopyDlg mParentContainer;
	private SmartHFCopyGroupBtnPnl mGroupBtnPnl;
	private SmartHFCopyGroupLbPnl mGroupLbPnl;
	private SmartHFCopyGroupTfPnl mGroupTfPnl;
	
	/** 
	 * Constructor of SmartHFCopyContentPnl.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFCopyContentPnl(SmartHFCopyDlg inParentContainer) {
		mParentContainer = inParentContainer;
		init();
	}

	public SmartHFCopyDlg getParentContainer() {
		return mParentContainer;
	}
	
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		mGroupBtnPnl = new SmartHFCopyGroupBtnPnl(this);
		mGroupLbPnl = new SmartHFCopyGroupLbPnl();
		mGroupTfPnl = new SmartHFCopyGroupTfPnl();
		
		SmartHFDataModel dataModel = mParentContainer.getParentContainer().getParentContainer().getMainContentPane().getSmartHFSelectedRowData();
		mGroupTfPnl.getCopySourceTf().setText(dataModel.getSmartHFPropertyFileModel().getDisplayName());
		mGroupTfPnl.getCopyingNameTf().setText(NameUtilities.createCopyName(dataModel.getSmartHFPropertyFileModel().getDisplayName(), CommonUtils.getHotfolderNameList()));
				
		Box box_01 = Box.createVerticalBox();
		box_01.add(Box.createVerticalStrut(5));
		box_01.add(mGroupTfPnl);
		box_01.add(Box.createVerticalStrut(20));
		box_01.add(Box.createVerticalGlue());
		box_01.add(mGroupBtnPnl);
		box_01.add(Box.createVerticalStrut(10));

		Box box_02 = Box.createHorizontalBox();
		box_02.add(mGroupLbPnl);
		box_02.add(Box.createHorizontalStrut(20));
		box_02.add(box_01);
		box_02.add(Box.createHorizontalStrut(20));
		box_02.add(Box.createHorizontalGlue());

		add(box_02);
}
	
	public String getCopyingName() {
		return mGroupTfPnl.getCopyingNameTf().getText();
	}
}
