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

package jp.co.screen.smarthf.view.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ActionMap;
import javax.swing.JPanel;
import javax.swing.JLabel;

import jp.co.screen.smarthf.controller.SmartHFActionKey;
import jp.co.screen.smarthf.controller.SmartHFAddAction;
import jp.co.screen.smarthf.controller.SmartHFCloseAction;
import jp.co.screen.smarthf.controller.SmartHFCopyAction;
import jp.co.screen.smarthf.controller.SmartHFDeleteAction;
import jp.co.screen.smarthf.controller.SmartHFEditAction;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.common.resource.LangKeySmartHF;

import org.apache.log4j.Logger;

/**
 * main panel of frame
 * @author syptn
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFManagerPnl extends JPanel{
	
	/**
	 * logger 
	 */
	private static Logger LOGGER=Logger.getLogger(SmartHFManagerPnl.class);
	
	private SmartHFManagerFrm mParentContainer;
	
	/**
	 * read only mode
	 */
	protected final boolean mIsReadOnly;
	
	/**
	 * action map
	 */
	protected ActionMap mActionMap;
		
	/**
	 * group panel
	 */
	private SmartHFGroupBtnPnl mButtonPnl;
	
	/**
	 * main content pane
	 */
	private SmartHFMainContentPnl mMainContentPnl ;
	
	/**
	 * 
	 * Constructor of LBRSManagerPnl.java
	 *
	 * @param inIsReadMode
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFManagerPnl(SmartHFManagerFrm inParentContainer, boolean inIsReadMode){
		super();
		mParentContainer = inParentContainer;
		mIsReadOnly = inIsReadMode;
		init();
	}
	
	/**
	 * init component
	 * 
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	private void init(){
		this.setLayout(new BorderLayout(20, 0));
		this.add(getMainContentPane(), BorderLayout.CENTER);
		
		JPanel groupBtnPnl = new JPanel(new BorderLayout());
		
		// Add empty space at right side
		JPanel leftPnl = new JPanel();
		leftPnl.setPreferredSize(new Dimension(10, 0));
		groupBtnPnl.add(leftPnl, BorderLayout.EAST);
		
		groupBtnPnl.add(getBtnsPanel(), BorderLayout.CENTER);

		this.add(groupBtnPnl, BorderLayout.EAST);
		
		updateBtnsState(0);
	}
	
	/**
	 * main content pane
	 * 
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFMainContentPnl getMainContentPane(){
		if(mMainContentPnl == null){
			mMainContentPnl  = new SmartHFMainContentPnl(this);
		}
		
		return mMainContentPnl;
	}
	
	/**
	 * create group button
	 * 
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	 public SmartHFGroupBtnPnl getBtnsPanel(){
        if( mButtonPnl == null ){
            mButtonPnl = new SmartHFGroupBtnPnl(this);
        }
        return mButtonPnl;
    }
	 	
	public void updateBtnsState(int inNumRowsSelected) {
		if (mIsReadOnly) {
			getBtnsPanel().getAddButton().setEnabled(false);
			getBtnsPanel().getPropertyButton().setEnabled(false);
			getBtnsPanel().getCopyButton().setEnabled(false);
			getBtnsPanel().getDeleteButton().setEnabled(false);
		} else {
			if (inNumRowsSelected > 0) {
				if (inNumRowsSelected == 1) {
					getBtnsPanel().getPropertyButton().setEnabled(true);
					getBtnsPanel().getCopyButton().setEnabled(true);
				} else {
					
					getBtnsPanel().getPropertyButton().setEnabled(false);
					getBtnsPanel().getCopyButton().setEnabled(false);
				}
				
				getBtnsPanel().getDeleteButton().setEnabled(true);
			} else {
				getBtnsPanel().getPropertyButton().setEnabled(false);
				getBtnsPanel().getCopyButton().setEnabled(false);
				getBtnsPanel().getDeleteButton().setEnabled(false);
			}
			
			getBtnsPanel().getAddButton().setEnabled(true);
		}
	}

	public boolean isReadOnly() {
		return mIsReadOnly;
	}
	
	public SmartHFManagerFrm getParentContainer() {
		return mParentContainer;
	}
}
