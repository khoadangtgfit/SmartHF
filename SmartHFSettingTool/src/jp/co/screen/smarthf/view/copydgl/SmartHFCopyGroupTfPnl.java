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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jp.co.screen.equios.ui.common.util.ComponentUtilities;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFCopyGroupTfPnl extends JPanel {

	private JTextField mCopySourceTf;
	private JTextField mCopyingNameTf;
	
	/** 
	 * Constructor of SmartHFCopyGroupTfPnl.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFCopyGroupTfPnl() {
		mCopySourceTf = null;
		mCopyingNameTf = null;
		init();
	}

	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Box box_01 = Box.createHorizontalBox();
		box_01.add(getCopySourceTf());
		box_01.add(Box.createHorizontalGlue());

		Box box_02 = Box.createHorizontalBox();
		box_02.add(getCopyingNameTf());
		box_02.add(Box.createHorizontalGlue());

		
		add(Box.createVerticalStrut(30));
		add(box_01);
		add(Box.createVerticalStrut(10));
		add(box_02);
		add(Box.createGlue());
	}
	
	public JTextField getCopySourceTf() {
		if (mCopySourceTf == null) {
			mCopySourceTf = new JTextField();
			
			Dimension tfSize = new Dimension(200, 20);
			mCopySourceTf.setMaximumSize(tfSize);
			mCopySourceTf.setPreferredSize(tfSize);
			mCopySourceTf.setEditable(false);
		}
		
		return mCopySourceTf;
	}
	
	public JTextField getCopyingNameTf() {
		if (mCopyingNameTf == null) {
			mCopyingNameTf = new JTextField();
			Dimension tfSize = new Dimension(200, 20);
			mCopyingNameTf.setMaximumSize(tfSize);
			mCopyingNameTf.setPreferredSize(tfSize);
		}
		
		return mCopyingNameTf;
	}

}
