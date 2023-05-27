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

package jp.co.screen.smarthf.view.copydgl;

import java.awt.Color;
import java.awt.LayoutManager;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import jp.co.screen.equios.ui.common.bean.button.JButtonExAction;
import jp.co.screen.equios.ui.common.util.ComponentUtilities;
import jp.co.screen.equios.ui.resource.lang.common.LangKeyCommon;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.controller.CopyDlgCancelAction;
import jp.co.screen.smarthf.controller.CopyDlgOkAction;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

@SuppressWarnings("serial")
public class SmartHFCopyGroupBtnPnl extends JPanel {

	private SmartHFCopyContentPnl mParentContainer;
	private JButtonExAction mOkBtn;
	private JButtonExAction mCancelBtn;
	/** 
	 * Constructor of SmartHFCopyGroupBtnPnl.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFCopyGroupBtnPnl(SmartHFCopyContentPnl inParentContainer) {
		mParentContainer = inParentContainer;
		mOkBtn = null;
		mCancelBtn = null;
		init();
	}

	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Box box_01 = Box.createVerticalBox();
		box_01.add(Box.createVerticalGlue());
		box_01.add(getOkButton());
		
		Box box_02 = Box.createVerticalBox();
		box_02.add(Box.createVerticalGlue());
		box_02.add(getCancelButton());
		
		add(box_01);
		add(Box.createHorizontalStrut(20));
		add(box_02);
		add(Box.createGlue());
		
		List<JComponent> btnList = new LinkedList<JComponent>();
		btnList.add(getOkButton());
		btnList.add(getCancelButton());
		
		ComponentUtilities.setButtonPanelComponentsProperties(btnList);
		
		installListener();
	}
	
	public JButtonExAction getOkButton() {
		if (mOkBtn == null) {
			mOkBtn = new JButtonExAction();
			mOkBtn.setText(SmartHFResource.getResource(LangKeyCommon.WORD_OK));
		}
		
		return mOkBtn;
	}
	
	public JButtonExAction getCancelButton() {
		if (mCancelBtn == null) {
			mCancelBtn = new JButtonExAction();
			mCancelBtn.setText(SmartHFResource.getResource(LangKeyCommon.WORD_CANCEL));
		}
		
		return mCancelBtn;
	}
	
	private void installListener() {
		mOkBtn.setAction(new CopyDlgOkAction(this));
		mCancelBtn.setAction(new CopyDlgCancelAction(this));
	}

	public SmartHFCopyContentPnl getParentContainer() {
		return mParentContainer;
	}
}
