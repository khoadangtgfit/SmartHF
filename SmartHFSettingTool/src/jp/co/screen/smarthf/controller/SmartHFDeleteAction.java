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
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import jp.co.screen.equios.ui.resource.lang.common.LangKeyCommon;
import jp.co.screen.smarthf.common.resource.LangKeySmartHF;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.utils.CommonUtils;
import jp.co.screen.smarthf.utils.JOptionPaneUtils;
import jp.co.screen.smarthf.view.main.SmartHFGroupBtnPnl;

/**
 * Class Description
 * @author hoavanngo.fd
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFDeleteAction extends AbstractAction{

	//** parent container
	private SmartHFGroupBtnPnl mParentContainer = null;
	
	/**
	 * 
	 * Constructor of LBRSAddAction.java
	 *
	 * @param inParent
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFDeleteAction(SmartHFGroupBtnPnl inParent){
		mParentContainer = inParent;
	}

	
	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		int confirmed = JOptionPane.showConfirmDialog(mParentContainer.getParentContainer().getParentContainer(),
													SmartHFResource.getResource(LangKeySmartHF.MSG_INF_DELETEHOTFOLDERCONFIRM),
													SmartHFResource.getResource(LangKeyCommon.WORD_CONFIRMATION),
													JOptionPane.YES_NO_OPTION);
		if (confirmed == JOptionPane.YES_OPTION) {
			List<SmartHFDataModel> selectedDataModels = mParentContainer.getParentContainer().getMainContentPane().getSmartHFSelectedRowsData();
			// Check HF is processing
			for (SmartHFDataModel dtModel : selectedDataModels) {
				List<String> otherFileList = CommonUtils.getOtherFileList(dtModel.getSmartHFPropertyFileModel().getDisplayName());
				if (otherFileList.size() > 0) {
					String errMsg = SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_CANT_DELETE_HF);
					JOptionPaneUtils.showErrorDialog(mParentContainer.getParentContainer().getParentContainer(), errMsg);
					
					return;
				}
			}
			
			for (SmartHFDataModel dtModel : selectedDataModels) {
				if (!CommonUtils.deleteHotfolder(dtModel.getSmartHFPropertyFileModel().getDisplayName())) {
					JOptionPane.showMessageDialog(null, SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_CANTDELETEHF),
												SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_CANTDELETEHF), JOptionPane.ERROR_MESSAGE);
					
					mParentContainer.getParentContainer().updateBtnsState(0);
					return;
				}
				
				mParentContainer.getParentContainer().getMainContentPane().getTblModel().removeSmartHFData(dtModel);
			}
			
			mParentContainer.getParentContainer().updateBtnsState(0);
		}
	}

}
