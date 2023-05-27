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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jp.co.screen.equios.ui.common.bean.setting.framework.SettingBoard;
import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.smarthf.common.resource.LangKeySmartHF;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.utils.CommonUtils;
import jp.co.screen.smarthf.utils.JOptionPaneUtils;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropertyFileNmRuStPnl extends JPanel {

	private SmartHFPropertyContentPnl mParentContainer;
	private JTextField mTopSepTf;
	private SmartHFPropertyMacroRowsPnl mMacroRowsPnl;
	
	/** 
	 * Constructor of SmartHFPropertyFileNmRuStPnl.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFPropertyFileNmRuStPnl(SmartHFPropertyContentPnl inParentContainer) {
		mParentContainer = inParentContainer;
		init();
	}
	
	public SmartHFPropertyContentPnl getParentContainer() {
		return mParentContainer;
	}
	
	private void init() {
		SmartHFDataModel dataModel = mParentContainer.getParentContainer().getParentContainer().getParentContainer().getMainContentPane().getSmartHFSelectedRowData();
		List<String> sepList = null;
		if (!mParentContainer.getParentContainer().isAddHotFolder() && dataModel != null) {
			sepList = CommonUtils.getSeparatorList(dataModel.getSmartHFRulePropertyFileModel().getRule());
		}
		
		// Initialize for Top separator
		mTopSepTf = new JTextField();
		mTopSepTf.setPreferredSize(new Dimension(100, 25));
		mTopSepTf.setMaximumSize(new Dimension(100, 25));
		
		if (sepList != null && sepList.size() > 0) {
			mTopSepTf.setText(sepList.get(0));
		}

		mTopSepTf.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent paramDocumentEvent) {
				updateFileNameRule();
			}
			
			public void insertUpdate(DocumentEvent paramDocumentEvent) {
				updateFileNameRule();
			}
			
			public void changedUpdate(DocumentEvent paramDocumentEvent) {
				updateFileNameRule();
			}
		});

		setAlignmentX(LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Box box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(20));
		box.add(mTopSepTf);
		box.add(Box.createHorizontalGlue());
		
		JScrollPane scrollPane = new JScrollPane(getMacroRowsPnl());
		if (mMacroRowsPnl.getMacroRowPnlList().size() > 0) {
			Dimension scrollSize = new Dimension();
			scrollSize.width = mMacroRowsPnl.getMacroRowPnlList().get(0).getPreferredSize().width;
			scrollSize.height = mMacroRowsPnl.getMacroRowPnlList().get(0).getPreferredSize().height * 4 + 120;
			scrollPane.getViewport().setPreferredSize(scrollSize);
			scrollPane.getViewport().setMaximumSize(scrollSize);
			
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
		}

		Box box1 = Box.createHorizontalBox();
		box1.add(scrollPane);
		box1.add(Box.createHorizontalGlue());

		Box box2 = Box.createVerticalBox();
		box2.add(Box.createVerticalStrut(10));
		box2.add(box);
		box2.add(Box.createVerticalStrut(10));
		box2.add(box1);
		box2.add(Box.createVerticalStrut(10));
		box2.add(Box.createVerticalGlue());

		add(box2);
		
		setBorder(BorderFactory.createTitledBorder(
				SmartHFResource.getResource(LangKeySmartHF.WORD_FILENAMERULESETTINGTITLE)));
		
		updateFileNameRule();
	}
	
	public SmartHFPropertyMacroRowsPnl getMacroRowsPnl() {
		if (mMacroRowsPnl == null) {
			mMacroRowsPnl = new SmartHFPropertyMacroRowsPnl(this);
		}
		
		return mMacroRowsPnl;
	}
	
	public void updateFileNameRule() {
		String fileNameRule = mTopSepTf.getText() + mMacroRowsPnl.getRowsString();
		mParentContainer.updateFileNameRule(fileNameRule);
	}
	
	public boolean checkFileNameRule() {
		/*if (!CommonUtils.checkSeparator(mParentContainer.getParentContainer(), mTopSepTf.getText())) {
			return false;
		}*/
		
		List<SmartHFPropertyMacroRowPnl> rowPnlsList = mMacroRowsPnl.getMacroRowPnlList();
		for (int rowIdx = 0; rowIdx < rowPnlsList.size(); rowIdx++) {
			if (rowIdx < rowPnlsList.size() - 1 && !CommonUtils.checkSeparator(mParentContainer.getParentContainer(), rowPnlsList.get(rowIdx).getSeparator())) {
				return false;
			}
			
			// Check have same macro string
			if (!rowPnlsList.get(rowIdx).getSeparator().equals(Constants.ASTERISK_STRING)){
				for (int idx = rowIdx; idx < rowPnlsList.size() - 1; idx++) {
					if (rowPnlsList.get(rowIdx).getMacro().equals(rowPnlsList.get(idx + 1).getMacro())) {
						JOptionPaneUtils.showErrorDialog(mParentContainer.getParentContainer(), SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_FAIL_TO_USE_SAME_MACRO));
						
						return false;
					}
				}
			}
		}
		
		List<String> requiredMacroList = CommonUtils.getRuleEngineRequiredMacroList();
		for (String requiredMacro : requiredMacroList) {
			boolean isFound = false;
			for (int rowIdx = 0; rowIdx < rowPnlsList.size(); rowIdx++) {
				if (rowPnlsList.get(rowIdx).getMacro().equals(requiredMacro)) {
					isFound = true;
					break;
				}
			}
			
			if (!isFound) {
				String requiredMacroListStr = new String();
				for (int idx = 0; idx < requiredMacroList.size(); idx++) {
					requiredMacroListStr += requiredMacroList.get(idx);
					
					if (idx != requiredMacroList.size() - 1) {
						requiredMacroListStr += Constants.COMMA_STRING + Constants.SPACE_STRING;
					}
				}
				
				JOptionPaneUtils.showErrorDialog(mParentContainer.getParentContainer(), String.format("%s%s",SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_NOT_ENOUGH_REQUIRE_MACRO), requiredMacroListStr));
				
				return false;
			}
		}
		
		return true;
	}
}
