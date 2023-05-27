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

import jp.co.screen.equios.ui.resource.lang.common.LangKeyCommon;
import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.smarthf.common.resource.LangKeySmartHF;
import jp.co.screen.smarthf.common.resource.SmartHFResource;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropertyFileNameRuleLbPnl extends JPanel {

	private JLabel mFileNameRuleLb;
	private String mFileNameRuleTitle;
	private String mFileNameRule;
	
	/** 
	 * Constructor of SmartHFPropertyFileNameRuleLbPnl.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFPropertyFileNameRuleLbPnl() {
		mFileNameRuleLb = null;
		init();
	}
	
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		mFileNameRuleTitle = SmartHFResource.getResource(LangKeySmartHF.WORD_FILENAMERULETITLE);
		
		mFileNameRuleLb = new JLabel(mFileNameRuleTitle);
		setMaximumSize(new Dimension(400, 25));
		setPreferredSize(new Dimension(400, 25));

		add(Box.createHorizontalStrut(10));
		add(mFileNameRuleLb);
		add(Box.createHorizontalGlue());
	}

	public void updateFileNameRule(String inFileNameRule) {
		mFileNameRule = inFileNameRule;
		String fileNameuRule = mFileNameRuleTitle + Constants.SPACE_STRING + inFileNameRule;
		mFileNameRuleLb.setToolTipText(inFileNameRule);
		mFileNameRuleLb.setText(fileNameuRule);
	}
	
	public String getFileNameRule() {
		return mFileNameRule;
	}
}
