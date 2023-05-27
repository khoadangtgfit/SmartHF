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

package jp.co.screen.smarthf.view.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jp.co.screen.equios.ui.resource.lang.common.LangKeyCommon;
import jp.co.screen.smarthf.common.ConfigManager;
import jp.co.screen.smarthf.common.resource.IconKeySmartHF;
import jp.co.screen.smarthf.common.resource.LangKeySmartHF;
import jp.co.screen.smarthf.common.resource.SmartHFResource;

import org.apache.log4j.Logger;

/**
 * Main frame
 * @author syptn
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFManagerFrm extends JFrame {
	
	 private static Logger LOGGER=Logger.getLogger(SmartHFManagerFrm.class);
	 
	 private SmartHFManagerPnl mPanel;

	 private final boolean isReadOnly;
	 
	 public SmartHFManagerFrm(boolean inReadOnly) {
        super();
        LOGGER.info("Start HF main windows");

        isReadOnly = inReadOnly;
        mPanel = new SmartHFManagerPnl(this, isReadOnly);
        init();
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
        LOGGER.info("End.");
	 }
	 
	 /**
	  * get main panel
	  * 
	  * @return
	  * @author syptn
	  * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	  */
	 public SmartHFManagerPnl getPanel() {
	        return mPanel;
	    }
	 /**
	  * init all component of main frame
	  * 
	  * @author syptn
	  * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	  */
	 private void init(){
		 LOGGER.info("Start.");

		 add(getPanel());

		 ImageIcon img = new ImageIcon(IconKeySmartHF.LBRS_ICON);
		 setIconImage(img.getImage());

		 if (getSize() == null || (getSize().width <= 0 && getSize().height <= 0)) {
			 setPreferredSize(ConfigManager.getWindowSize());

			 pack();
		 }
		 
		 setTitle(SmartHFResource.getResource(LangKeySmartHF.WORD_SMARTHFTITLE));

		 setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		 LOGGER.info("End.");
	}
	 
	 /**
	  * confirm message exit
	  * 
	  * @author syptn
	  * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	  */
	 public void confirmExit(){
		 int confirmed = JOptionPane.showConfirmDialog(this,
             SmartHFResource.getResource(LangKeySmartHF.MSG_INF_EXITCONFIRM), 
             SmartHFResource.getResource(LangKeyCommon.WORD_CONFIRMATION),
             JOptionPane.YES_NO_OPTION);
			 if (confirmed == JOptionPane.YES_OPTION){
			     this.dispose();
			     LOGGER.info("Exit program");
			 }
	 }
}
