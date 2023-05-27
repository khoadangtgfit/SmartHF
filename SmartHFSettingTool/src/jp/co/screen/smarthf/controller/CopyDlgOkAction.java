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

package jp.co.screen.smarthf.controller;

import java.awt.event.ActionEvent;
import java.nio.file.Paths;

import javax.swing.AbstractAction;

import jp.co.screen.smarthf.common.resource.LangKeySmartHF;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.view.copydgl.SmartHFCopyContentPnl;
import jp.co.screen.smarthf.view.copydgl.SmartHFCopyGroupBtnPnl;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.utils.CommonUtils;
import jp.co.screen.smarthf.utils.FileUtils;
import jp.co.screen.smarthf.utils.JOptionPaneUtils;
import jp.co.screen.smarthf.utils.StringUtils;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

@SuppressWarnings("serial")
public class CopyDlgOkAction extends AbstractAction {

	private SmartHFCopyGroupBtnPnl mParentContainer;
	
	public CopyDlgOkAction(SmartHFCopyGroupBtnPnl inParentContainer) {
		mParentContainer = inParentContainer;
	}

	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		String hfName = mParentContainer.getParentContainer().getCopyingName();
		if (CommonUtils.checkHFName(mParentContainer.getParentContainer().getParentContainer(), hfName, true)) {
			SmartHFDataModel dataModel = mParentContainer.getParentContainer().getParentContainer().getParentContainer().getParentContainer().getMainContentPane().getSmartHFSelectedRowData();
			SmartHFDataModel newDataModel = dataModel.clone();
			
			// Copy hot folder
			CommonUtils.copyHotfolder(dataModel.getSmartHFPropertyFileModel().getDisplayName(), hfName);
			
			// Change DisplayName element in property file
			newDataModel.getSmartHFPropertyFileModel().setDisplayName(hfName);
			if(CommonUtils.writePropertyFile(Paths.get(FileUtils.getSmartHotfolderRootPath(), hfName).toString(), newDataModel.getSmartHFPropertyFileModel())) {
				// Update table
				mParentContainer.getParentContainer().getParentContainer().getParentContainer().getParentContainer().getMainContentPane().getTblModel().addSmartHFData(newDataModel);
				
				mParentContainer.getParentContainer().getParentContainer().getParentContainer().getParentContainer().updateBtnsState(0);
				mParentContainer.getParentContainer().getParentContainer().dispose();
			} else {
				JOptionPaneUtils.showErrorDialog(mParentContainer.getParentContainer().getParentContainer(), SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_FAILTOUPDATEHF));
			}
		}
	}
}
