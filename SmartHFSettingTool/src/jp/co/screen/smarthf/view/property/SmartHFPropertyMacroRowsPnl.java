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

import java.awt.LayoutManager;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.sun.tools.javac.code.Attribute.Constant;

import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.utils.CommonUtils;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

@SuppressWarnings("serial")
public class SmartHFPropertyMacroRowsPnl extends JPanel {

	private SmartHFPropertyFileNmRuStPnl mParentContainer;
	private List<SmartHFPropertyMacroRowPnl> mMacroRowPnlList;
	private List<String> mRuleEngineMacroList;
	
	/** 
	 * Constructor of SmartHFPropertyMacroRowsPnl.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFPropertyMacroRowsPnl(SmartHFPropertyFileNmRuStPnl inParentContainer) {
		mParentContainer = inParentContainer;
		mRuleEngineMacroList = null;
		mMacroRowPnlList = null;
		
		init();
	}

	public SmartHFPropertyFileNmRuStPnl getParentContainer() {
		return mParentContainer;
	}
	
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setAlignmentY(TOP_ALIGNMENT);
		
		mRuleEngineMacroList = CommonUtils.getRuleEngineMacroList();
		getMacroRowPnlList();
		
		for (int idx = 0; idx < mMacroRowPnlList.size(); idx++) {
			add(mMacroRowPnlList.get(idx));
			
			if (idx != mMacroRowPnlList.size() - 1) {
				add(mMacroRowPnlList.get(idx).getVerBottomGap());
			}
		}
	}
	
	public List<SmartHFPropertyMacroRowPnl> getMacroRowPnlList() {
		if (mMacroRowPnlList == null) {
			mMacroRowPnlList = new LinkedList<SmartHFPropertyMacroRowPnl>();
			
			if (!mParentContainer.getParentContainer().getParentContainer().isAddHotFolder()) {
				SmartHFDataModel dataModel = mParentContainer.getParentContainer().getParentContainer().getParentContainer().getParentContainer().getMainContentPane().getSmartHFSelectedRowData();
				List<String > macroList = CommonUtils.getMacroList(dataModel.getSmartHFRulePropertyFileModel().getRule());
				List<String > sepList = CommonUtils.getSeparatorList(dataModel.getSmartHFRulePropertyFileModel().getRule());
				for (int idx = 0; idx < macroList.size(); idx++) {
					String separator = null;
					if (idx + 1 < sepList.size()) {
						separator = sepList.get(idx + 1);
					}
					else {
						separator = Constants.EMPTY_STRING;
					}
					
					mMacroRowPnlList.add(new SmartHFPropertyMacroRowPnl(this, mRuleEngineMacroList, macroList.get(idx), separator));
				}
			} else {
				List<String> requiredMacroList = CommonUtils.getRuleEngineRequiredMacroList();
				if (requiredMacroList.size() > 0) {
					for (int idx = 0; idx < requiredMacroList.size(); idx++) {
						mMacroRowPnlList.add(new SmartHFPropertyMacroRowPnl(this, mRuleEngineMacroList, requiredMacroList.get(idx), Constants.EMPTY_STRING));
					}
				} else {
					for (int idx = 0; idx < Constants.MAX_NEW_ROWS; idx++) {
						mMacroRowPnlList.add(new SmartHFPropertyMacroRowPnl(this, mRuleEngineMacroList, Constants.EMPTY_STRING, Constants.EMPTY_STRING));
					}
				}
			}
		}
		
		if (mMacroRowPnlList != null && mMacroRowPnlList.size() >  0) {
			mMacroRowPnlList.get(mMacroRowPnlList.size() - 1).changeMacroBtn(false);
		}
		
		return mMacroRowPnlList;
	}
	
	public void addMacroRow(SmartHFPropertyMacroRowPnl inNewMacroRow) {
		inNewMacroRow.changeMacroBtn(false);
		
		if (mMacroRowPnlList != null && mMacroRowPnlList.size() >  0) {
			mMacroRowPnlList.get(mMacroRowPnlList.size() - 1).changeMacroBtn(true);
			add(mMacroRowPnlList.get(mMacroRowPnlList.size() - 1).getVerBottomGap());
		}
		
		mMacroRowPnlList.add(inNewMacroRow);
		add(inNewMacroRow);

		revalidate();
		repaint();
		
		mParentContainer.updateFileNameRule();
	}
	
	public void removeMacroRow(SmartHFPropertyMacroRowPnl inMacroRow) {
		if (mMacroRowPnlList != null && mMacroRowPnlList.size() >  0) {
			mMacroRowPnlList.remove(inMacroRow);
		}
		
		remove(inMacroRow.getVerBottomGap());
		remove(inMacroRow);
		
		revalidate();
		repaint();
		
		mParentContainer.updateFileNameRule();
	}
		
	public String getRowsString() {
		String rowsString = new String();
		for (SmartHFPropertyMacroRowPnl macroRow : mMacroRowPnlList) {
			rowsString += macroRow.getRowString();
		}
		
		return rowsString;
	}
}
