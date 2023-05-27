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

package jp.co.screen.smarthf.view.property;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.smarthf.common.resource.LangKeySmartHF;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.utils.CommonUtils;
import jp.co.screen.swc.util.NameUtilities;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropertyHFNamePnl extends JPanel {

	SmartHFPropertyContentPnl mParentContainer;
	private JTextField mHFNameTf;
	
	/** 
	 * Constructor of SmartHFPropertyHFNamePnl.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFPropertyHFNamePnl(SmartHFPropertyContentPnl inParentContainer) {
		mParentContainer = inParentContainer;
		init();
	}

	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setAlignmentX(10);
		
		// Initialize HF Name TextField
		mHFNameTf = new JTextField();
		if (mParentContainer.getParentContainer().isAddHotFolder()) {
			mHFNameTf.setText(NameUtilities.createNewName(
					SmartHFResource.getResource(LangKeySmartHF.WORD_DEFAULT_HFNAME),
					CommonUtils.getHotfolderNameList()));
		} else {
			SmartHFDataModel dataModel = mParentContainer.getParentContainer().getParentContainer().getParentContainer().getMainContentPane().getSmartHFSelectedRowData();
			mHFNameTf.setText(dataModel.getSmartHFPropertyFileModel().getDisplayName());
		}
		
		mHFNameTf.setPreferredSize(new Dimension(150, 25));
		mHFNameTf.setMaximumSize(new Dimension(150, 25));

		// Create HF Name label
		JLabel hfNameLb = new JLabel(SmartHFResource.getResource(LangKeySmartHF.WORD_HFNAME) + Constants.COLON_STRING);
		
		add(Box.createHorizontalStrut(10));
		add(hfNameLb);
		add(Box.createHorizontalStrut(30));
		add(mHFNameTf);
		add(Box.createHorizontalGlue());
	}
	
	public String getHFName() {
		return mHFNameTf.getText();
	}	
}
