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
import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import jp.co.screen.equios.ui.common.bean.button.JButtonExAction;
import jp.co.screen.equios.ui.common.resource.lang.LangResource;
import jp.co.screen.equios.ui.common.util.ComponentUtilities;
import jp.co.screen.equios.ui.resource.lang.common.LangKeyCommon;
import jp.co.screen.equios.ui.feature.pmngdialog.collaborator.ButtonPanel;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.controller.SmartHFActionKey;
import jp.co.screen.smarthf.controller.SmartHFAddAction;
import jp.co.screen.smarthf.controller.SmartHFCloseAction;
import jp.co.screen.smarthf.controller.SmartHFCopyAction;
import jp.co.screen.smarthf.controller.SmartHFDeleteAction;
import jp.co.screen.smarthf.controller.SmartHFEditAction;

import org.apache.log4j.Logger;

/**
 * Class Description
 * 
 * @author syptn
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFGroupBtnPnl extends JPanel {

	private static Logger LOGGER = Logger.getLogger(SmartHFGroupBtnPnl.class);
	private LangResource mResource;
	private JButtonExAction mAddButton;
	private JButtonExAction mPropertyButton;
	private JButtonExAction mCopyButton;
	private JButtonExAction mDeleteButton;
	private JButtonExAction mBtnClose;
	private ActionMap mActionMap;
	private SmartHFManagerPnl mParentContainer;

	public SmartHFGroupBtnPnl(SmartHFManagerPnl inParentContainer) {
		mParentContainer = inParentContainer;
		init();
		installListener();
	}

	protected void init() {
		LOGGER.info("Start");
		mResource = SmartHFResource.getInstance();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(ComponentUtilities.getButtonPanelBorder());

		add(Box.createVerticalStrut(40));
		add(getAddButton());
		add(Box.createVerticalStrut(10));
		add(getPropertyButton());
		add(Box.createVerticalStrut(10));
		add(getCopyButton());
		add(Box.createVerticalStrut(20));
		add(getDeleteButton());
		add(Box.createGlue());
		add(getBtnClose());
		add(Box.createVerticalStrut(10));

		/*
		 * mComps.add( getAddButton() ); mComps.add( getPropertyButton() ); mComps.add(
		 * getCopyButton() ); mComps.add( getDeleteButton() ); mComps.add( getBtnClose()
		 * ); LOGGER.info("end");
		 * 
		 * 
		 * VisibleChangeListener listener = new VisibleChangeListener(); for (JComponent
		 * comps : mComps) { comps.addComponentListener(listener); }
		 */

		List<JComponent> btnList = new LinkedList<JComponent>();
		btnList.add(getAddButton());
		btnList.add(getPropertyButton());
		btnList.add(getCopyButton());
		btnList.add(getDeleteButton());
		btnList.add(getBtnClose());

		ComponentUtilities.setButtonPanelComponentsProperties(btnList);

	}

	public JButtonExAction getAddButton() {
		if (mAddButton == null) {
			mAddButton = new JButtonExAction();
			mAddButton.setText(mResource.getPropertyAdd3Period(LangKeyCommon.WORD_ADD));
			mAddButton.setAlignmentY(JButtonExAction.TOP_ALIGNMENT);
		}
		return mAddButton;
	}

	public JButtonExAction getPropertyButton() {
		if (mPropertyButton == null) {
			mPropertyButton = new JButtonExAction();
			mPropertyButton.setText(mResource.getPropertyAdd3Period(LangKeyCommon.WORD_PROPERTY));
			mPropertyButton.setAlignmentY(JButtonExAction.TOP_ALIGNMENT);
		}
		return mPropertyButton;
	}

	public JButtonExAction getCopyButton() {
		if (mCopyButton == null) {
			mCopyButton = new JButtonExAction();
			mCopyButton.setText(mResource.getPropertyAdd3Period(LangKeyCommon.WORD_COPY));
			mCopyButton.setAlignmentY(JButtonExAction.TOP_ALIGNMENT);
		}
		return mCopyButton;
	}

	public JButtonExAction getDeleteButton() {
		if (mDeleteButton == null) {
			mDeleteButton = new JButtonExAction();
			mDeleteButton.setText(mResource.getProperty(LangKeyCommon.WORD_DELETE));
			mDeleteButton.setAlignmentY(JButtonExAction.TOP_ALIGNMENT);
		}
		return mDeleteButton;
	}

	public JButtonExAction getBtnClose() {
		if (mBtnClose == null) {
			mBtnClose = new JButtonExAction();
			mBtnClose.setText(mResource.getProperty(LangKeyCommon.WORD_CLOSE));
			mBtnClose.setAlignmentY(JButtonExAction.BOTTOM_ALIGNMENT);
		}
		return mBtnClose;
	}

	private ActionMap initListenerActionMap() {
		if (mActionMap == null) {
			LOGGER.info("Set action map");
			mActionMap = new ActionMap();
			mActionMap.put(SmartHFActionKey.KEY_CLOSE_ACTION, new SmartHFCloseAction(this));
			mActionMap.put(SmartHFActionKey.KEY_ADD_ACTION, new SmartHFAddAction(this));
			mActionMap.put(SmartHFActionKey.KEY_EDIT_ACTION, new SmartHFEditAction(this));
			mActionMap.put(SmartHFActionKey.KEY_DELETE_ACTION, new SmartHFDeleteAction(this));
			mActionMap.put(SmartHFActionKey.KEY_COPY_ACTION, new SmartHFCopyAction(this));
			LOGGER.info("End set action map.");
		}

		return mActionMap;
	}

	private void installListener() {
		LOGGER.info("Start installListener ");

		initListenerActionMap();

		getAddButton().setAction(mActionMap.get(SmartHFActionKey.KEY_ADD_ACTION));
		getPropertyButton().setAction(mActionMap.get(SmartHFActionKey.KEY_EDIT_ACTION));
		getCopyButton().setAction(mActionMap.get(SmartHFActionKey.KEY_COPY_ACTION));
		getDeleteButton().setAction(mActionMap.get(SmartHFActionKey.KEY_DELETE_ACTION));
		getBtnClose().setAction(mActionMap.get(SmartHFActionKey.KEY_CLOSE_ACTION));

		LOGGER.info("End installListener");
	}

	public SmartHFManagerPnl getParentContainer() {
		return mParentContainer;
	}
}