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

package jp.co.screen.smarthf.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import jp.co.screen.equios.ui.resource.lang.common.LangKeyCommon;
import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.smarthf.common.resource.LangKeySmartHF;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.model.SmartHFTableModel;
import jp.co.screen.smarthf.utils.CommonUtils;
import jp.co.screen.smarthf.utils.JOptionPaneUtils;
import jp.co.screen.smarthf.utils.StringUtils;
import jp.co.screen.smarthf.view.property.SmartHFPropertyGroupBtnPnl;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class PropertyFrmOkAction extends AbstractAction {

	private SmartHFPropertyGroupBtnPnl mParentContainer;
	/** 
	 * Constructor of PropertyFrmOkAction.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public PropertyFrmOkAction(SmartHFPropertyGroupBtnPnl inParentContainer) {
		mParentContainer = inParentContainer;
	}

	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (mParentContainer.getParentContainer().checkHFData()) {
			SmartHFTableModel tableModel = mParentContainer.getParentContainer().getParentContainer().getParentContainer()
					.getParentContainer().getMainContentPane().getTblModel();
					
			if (mParentContainer.getParentContainer().getParentContainer().isAddHotFolder()) {
				SmartHFDataModel dataModel = new SmartHFDataModel();
				dataModel.getSmartHFPropertyFileModel().setDisplayName(mParentContainer.getParentContainer().getHFName());
				dataModel.getSmartHFRulePropertyFileModel().setRule(mParentContainer.getParentContainer().getFileNameRule());

				if (!CommonUtils.createHotFolder(mParentContainer.getParentContainer().getHFName(), dataModel, tableModel)) {
					JOptionPaneUtils.showErrorDialog(mParentContainer.getParentContainer().getParentContainer(), SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_FAILTOUPDATEHF));
					
					return;
				}
			} else {
				SmartHFDataModel dataModel = mParentContainer.getParentContainer().getParentContainer().getParentContainer()
						.getParentContainer().getMainContentPane().getSmartHFSelectedRowData();
			
				List<String> otherFileList = CommonUtils.getOtherFileList(mParentContainer.getParentContainer().getHFName());
				if (otherFileList.size() > 0) {
					String otherFileNamesString = new String();
					for(int idx = 0; idx < otherFileList.size(); idx++) {
						otherFileNamesString += otherFileList.get(idx);
						
						if (idx < otherFileList.size() - 1) {
							otherFileNamesString += Constants.COMMA_STRING + Constants.SPACE_STRING;
						}
					}
					
					String message = new String();
					if (dataModel.getSmartHFPropertyFileModel().getDisplayName().equals(mParentContainer.getParentContainer().getHFName())) {
						message = SmartHFResource.getResource(LangKeySmartHF.MSG_INF_EXITDLGSMARTHFPROPERTY) + otherFileNamesString;
						int confirmed = JOptionPane.showConfirmDialog(mParentContainer.getParentContainer().getParentContainer(),
																	message,
																	SmartHFResource.getResource(LangKeyCommon.WORD_CONFIRMATION),
																	JOptionPane.YES_NO_OPTION);
						if (confirmed == JOptionPane.NO_OPTION) {
							mParentContainer.getParentContainer().getParentContainer().dispose();
						}
					} else {
						message = SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_CHANGE_NAME_FOR_HF_NAME) + otherFileNamesString;
						JOptionPaneUtils.showErrorDialog(mParentContainer.getParentContainer().getParentContainer(), SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_FAILTOUPDATEHF));
						
						return;
					}
				}
				
				if (dataModel != null) {
					if (!dataModel.getSmartHFPropertyFileModel().getDisplayName().equals(mParentContainer.getParentContainer().getHFName())) {
						CommonUtils.renameHotfolder(dataModel.getSmartHFPropertyFileModel().getDisplayName(), mParentContainer.getParentContainer().getHFName());
					}
					
					dataModel.getSmartHFPropertyFileModel().setDisplayName(mParentContainer.getParentContainer().getHFName());
					dataModel.getSmartHFRulePropertyFileModel().setRule(mParentContainer.getParentContainer().getFileNameRule());
					if (!CommonUtils.editHotFolder(mParentContainer.getParentContainer().getHFName(), dataModel, tableModel)) {
						JOptionPaneUtils.showErrorDialog(mParentContainer.getParentContainer().getParentContainer(), SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_FAILTOUPDATEHF));
						
						return;
					}	
				}			
			}
			
			mParentContainer.getParentContainer().getParentContainer().getParentContainer().getParentContainer().updateBtnsState(0);
			mParentContainer.getParentContainer().getParentContainer().dispose();
		}
	}
}
