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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jp.co.screen.equios.ui.common.bean.button.JButtonExAction;
import jp.co.screen.smarthf.common.resource.IconKeySmartHF;
import jp.co.screen.smarthf.controller.PropertyFrmAddAction;
import jp.co.screen.smarthf.controller.PropertyFrmDeleteAction;
import jp.co.screen.smarthf.controller.SmartHFPropertyButtonUI;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropertyMacroRowPnl extends JPanel {

	enum eBtnAction {
		mAddAction,
		mDeleteAction
	}
	
	private SmartHFPropertyMacroRowsPnl mParentContainer;
	private List<String> mMacroList;
	private String mMacroValSelected;
	private String mSeparator;
	private JComboBox<String> mMacroCbx;
	private JTextField mSepTf;
	private JButtonExAction mMacroAddBtn;
	private JButtonExAction mMacroDelBtn;
	private eBtnAction mCurAction; 
	private Box mVerBottomGap;
	
	/** 
	 * Constructor of SmartHFPropertyMacroRowPnl.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFPropertyMacroRowPnl(SmartHFPropertyMacroRowsPnl inParentContainer, List<String> inMacroList, String inMacroVal, String inSepVal) {
		mParentContainer = inParentContainer;
		mMacroList = inMacroList;
		mMacroValSelected = inMacroVal;
		mSeparator = inSepVal;
		
		init();
	}
	
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setAlignmentX(LEFT_ALIGNMENT);
		
		// Create Macro ComboBox
		String[] macroArr = new String[mMacroList.size()];
		for(int i = 0; i < mMacroList.size(); i++) {
			macroArr[i] = mMacroList.get(i);
		}

		mMacroCbx = new JComboBox<String>(macroArr);
		mMacroCbx.setPreferredSize(new Dimension(150, 25));
		mMacroCbx.setMaximumSize(new Dimension(150, 25));
		
		if (!mMacroValSelected.isEmpty()) {
			mMacroCbx.setSelectedItem(mMacroValSelected);
		}else {
			mMacroCbx.setSelectedItem(mMacroCbx.getItemAt(0));
		}

		// Create Separator TextField 
		mSepTf = new JTextField();
		mSepTf.setPreferredSize(new Dimension(150, 25));
		mSepTf.setMaximumSize(new Dimension(150, 25));
		mSepTf.setText(mSeparator);
		
		// Create Macro Add button
		mMacroAddBtn = new JButtonExAction();
		mMacroAddBtn.setBorderPainted(false);
		mMacroAddBtn.setContentAreaFilled(false); 
		//mMacroAddBtn.setIcon(new ImageIcon(IconKeySmartHF.ADDMACRO_ICON));
		mMacroAddBtn.setMaximumSize(new Dimension(20, 20));
		mMacroAddBtn.setPreferredSize(new Dimension(20, 20));
		mMacroAddBtn.setUI(new SmartHFPropertyButtonUI(20, true));
		
		// Create Macro Delete button
		mMacroDelBtn = new JButtonExAction();
		mMacroDelBtn.setBorderPainted(false);
		mMacroDelBtn.setContentAreaFilled(false); 
		//mMacroDelBtn.setIcon(new ImageIcon(IconKeySmartHF.DELETEMACRO_ICON));
		mMacroDelBtn.setMaximumSize(new Dimension(20, 20));
		mMacroDelBtn.setPreferredSize(new Dimension(20, 20));
		mMacroDelBtn.setUI(new SmartHFPropertyButtonUI(20, false));
		
		add(Box.createHorizontalStrut(20));
		add(mMacroCbx);
		add(Box.createHorizontalStrut(10));
		add(mSepTf);
		add(Box.createHorizontalStrut(10));
		add(mMacroDelBtn);
		
		mCurAction = eBtnAction.mDeleteAction;
		
		mVerBottomGap = Box.createVerticalBox();
		mVerBottomGap.add(Box.createVerticalStrut(20));
		
		installListener();
	}
	
	private void installListener() {
		// Macro ComboBox
		mMacroCbx.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				mParentContainer.getParentContainer().updateFileNameRule();
			}
		});

		mMacroAddBtn.setAction(new PropertyFrmAddAction(this));
		mMacroDelBtn.setAction(new PropertyFrmDeleteAction(this));
		
		mSepTf.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent paramDocumentEvent) {
				mParentContainer.getParentContainer().updateFileNameRule();
			}
			
			public void insertUpdate(DocumentEvent paramDocumentEvent) {
				mParentContainer.getParentContainer().updateFileNameRule();
			}
			
			public void changedUpdate(DocumentEvent paramDocumentEvent) {
				mParentContainer.getParentContainer().updateFileNameRule();
			}
		});
	}
	
	public void changeMacroBtn(boolean inIsDelBtn) {
		if (inIsDelBtn) {
			if (mCurAction == eBtnAction.mAddAction) {
				remove(mMacroAddBtn);
				add(mMacroDelBtn);
				
				revalidate();
				repaint();
				
				mCurAction = eBtnAction.mDeleteAction;
			}
		}
		else {
			if (mCurAction == eBtnAction.mDeleteAction) {
				remove(mMacroDelBtn);
				add(mMacroAddBtn);
				
				revalidate();
				repaint();
				
				mCurAction = eBtnAction.mAddAction;
			}
		}
	}
	
	public String getRowString() {
		return mMacroCbx.getSelectedItem().toString() + mSepTf.getText();
	}
	
	public SmartHFPropertyMacroRowsPnl getParentContainer() {
		return mParentContainer;
	}
	
	public Box getVerBottomGap() {
		return mVerBottomGap;
	}
	
	public String getMacro() {
		return mMacroCbx.getSelectedItem().toString();
	}
	
	public String getSeparator() {
		return mSepTf.getText();
	}
}
