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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import jp.co.screen.smarthf.common.resource.LangKeySmartHF;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.utils.CommonUtils;
import jp.co.screen.smarthf.utils.JOptionPaneUtils;
import jp.co.screen.smarthf.utils.StringUtils;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropertyContentPnl extends JPanel {

	private SmartHFPropertyFrm mParentContainer;
	private SmartHFPropertyHFNamePnl mHFNamePnl;
	private SmartHFPropertyFileNameRuleLbPnl mFileNameRuleLbPnl;
	private SmartHFPropertyGroupBtnPnl mGroupBtnPnl;
	private SmartHFPropertyFileNmRuStPnl mFileNameRuleSettingPnl;
	
	
	/** 
	 * Constructor of SmartHFPropertyContentPnl.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFPropertyContentPnl(SmartHFPropertyFrm inParentContainer) {
		mParentContainer = inParentContainer;
		init();
	}
	
	private void init() {
		mHFNamePnl = new SmartHFPropertyHFNamePnl(this);
		mFileNameRuleLbPnl = new SmartHFPropertyFileNameRuleLbPnl();
		mGroupBtnPnl = new SmartHFPropertyGroupBtnPnl(this);
		mFileNameRuleSettingPnl = new SmartHFPropertyFileNmRuStPnl(this);
		
		setLayout(new BorderLayout());
		add(mFileNameRuleSettingPnl, BorderLayout.CENTER);
		
		Box box = Box.createHorizontalBox();
		box.add(mFileNameRuleLbPnl);
		box.add(Box.createHorizontalGlue());
		
		Box box1 = Box.createHorizontalBox();
		box1.add(Box.createHorizontalGlue());
		box1.add(mGroupBtnPnl);

		Box box2 = Box.createVerticalBox();
		box2.add(Box.createVerticalStrut(5));
		box2.add(box);
		box2.add(Box.createVerticalStrut(5));
		box2.add(box1);
		box2.add(Box.createVerticalStrut(10));
		box2.add(Box.createVerticalGlue());
		
		add(box2, BorderLayout.SOUTH);
		
		
		Box box3 = Box.createHorizontalBox();
		box3.add(Box.createHorizontalStrut(10));
		add(box3, BorderLayout.EAST);
		
		Box box4 = Box.createVerticalBox();
		box4.add(Box.createVerticalStrut(10));
		box4.add(mHFNamePnl);
		box4.add(Box.createVerticalStrut(10));
		
		add(box4, BorderLayout.NORTH);		
	}
	
	public String getHFName() {
		return mHFNamePnl.getHFName();
	}
	
	public String getFileNameRule() {
		return mFileNameRuleLbPnl.getFileNameRule();
	}
	
	public void updateFileNameRule(String inFileNameRule) {
		mFileNameRuleLbPnl.updateFileNameRule(inFileNameRule);
	}
	
	public SmartHFPropertyFrm getParentContainer() {
		return mParentContainer;
	}
	
	private boolean checkHFName() {
		if (mParentContainer.isAddHotFolder()) {
			return CommonUtils.checkHFName(mParentContainer, getHFName(), true);
		} else {
			SmartHFDataModel dataModel = mParentContainer.getParentContainer().getParentContainer().getMainContentPane().getSmartHFSelectedRowData();
			if (!dataModel.getSmartHFPropertyFileModel().getDisplayName().equals(getHFName())) {
				return CommonUtils.checkHFName(mParentContainer, getHFName(), true);
			} else {
				return CommonUtils.checkHFName(mParentContainer, getHFName(), false);
			}
		}
	}
	
	public boolean checkHFData() {
		if (!checkHFName() || !mFileNameRuleSettingPnl.checkFileNameRule()) {
			return false;
		}
		
		return true;
	}
}
