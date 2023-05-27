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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jp.co.screen.equios.ui.common.util.ComponentUtilities;
import jp.co.screen.equios.ui.resource.lang.common.LangKeyCommon;
import jp.co.screen.smarthf.common.resource.SmartHFResource;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFCopyGroupLbPnl extends JPanel {

	/** 
	 * Constructor of SmartHFCopyGroupLbPnl.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFCopyGroupLbPnl() {
		init();		
	}
	
	private void init() {
		JLabel copySourceLb = new JLabel(SmartHFResource.getResource(LangKeyCommon.WORD_COPYSOURCE));
		copySourceLb.setHorizontalAlignment(JLabel.LEFT); 
		
		JLabel copyingNameLb = new JLabel(SmartHFResource.getResource(LangKeyCommon.WORD_NAMEAFTERCOPY));
		copyingNameLb.setHorizontalAlignment(JLabel.LEFT);
		
		JLabel copyDglMsgLb = new JLabel(SmartHFResource.getResource(LangKeyCommon.MSG_INF_COPYDATA));
		copyDglMsgLb.setHorizontalAlignment(JLabel.LEFT);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(copyDglMsgLb);
		add(Box.createVerticalStrut(20));
		add(copySourceLb);
		add(Box.createVerticalStrut(20));
		add(copyingNameLb);
		add(Box.createVerticalGlue());
	}
}
