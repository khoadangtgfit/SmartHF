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

package jp.co.screen.smarthf.common;

import java.awt.Dimension;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JOptionPane;

import jp.co.screen.egleg.flc.bar.FlcConfigMap;
import jp.co.screen.egleg.util.fstr.FolderStructureReader;
import jp.co.screen.equios.ui.config.ClientConfigMap;
import jp.co.screen.smarthf.common.resource.LangKeySmartHF;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.utils.JOptionPaneUtils;
import jp.co.screen.tool.commonutil.InstanceLocker;
import jp.co.screen.tool.commonutil.PropertiesLoader;

import org.apache.log4j.Logger;

/**
 * Config class
 * @author hoavanngo.fd
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class ConfigManager {
	 private static Logger LOGGER=Logger.getLogger(ConfigManager.class);
	 
	 //properties
	 protected static Properties configProp;

	 /**
	  * load configuration
	  * 
	  * @return
	  * @author hoavanngo.fd
	  * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	  */
	public static boolean loadSmartHFConfig() {
		try {
			LOGGER.info("Start load config");

			// set product type
			ClientConfigMap.setProductName("SHFST");

			// client config file
			ClientConfigMap.setClientConfigFilePath(Constants.INI_CONFIG_FILE);

			// load config file
			try {
				ClientConfigMap.loadClientConfigFile();
				configProp = PropertiesLoader.load(Constants.INI_CONFIG_FILE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Fail to load config file.", "Error",
						JOptionPane.ERROR_MESSAGE);

				LOGGER.info("Fail to load config file");
				LOGGER.info(e.toString());
				e.printStackTrace();
				return false;
			}

			try {
				String localeStr = configProp.getProperty(Constants.KEY_LOCALE);
				if (localeStr == null) {
					localeStr = Locale.getDefault().toString();
				}
				Locale.setDefault(new Locale(localeStr));
			} catch (Exception e) {
				LOGGER.info("Fail to load locale config, set locale to default value");
				LOGGER.info(e.toString());
			}

			if (!SmartHFResource.init()) {
				JOptionPane.showMessageDialog(null,
						"Failed to load application resource file", "Error",
						JOptionPane.ERROR_MESSAGE);
				LOGGER.info("Fail to load resource file");
				return false;
			}

			try {
				FolderStructureReader.initialize();
				FlcConfigMap.initialize();
			} catch (Exception e) {

				JOptionPaneUtils
						.showErrorDialog(
								null,
								SmartHFResource
										.getResource(LangKeySmartHF.MSG_ERR_LOADFOLDERSTRUCTURENFLCFAIL));
				LOGGER.info("Fail to load FolderStructure and FlcConfig.");
				LOGGER.info(e.toString());
				e.printStackTrace();
				return false;
			}

			LOGGER.info("End load config");
			return true;
		} catch (Exception e) {

			JOptionPaneUtils.showErrorDialog(null, "Fail to load config.");
			LOGGER.info("Fail to load SHFST config");
			LOGGER.info(e.toString());
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * Checking single instance
	 * 
	 * @return
	 * @author hoavanngo.fd
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public static boolean checkSingleInstance(){
        final boolean lockInstance = InstanceLocker
                .lockInstance(Constants.SINGLE_INSTANCE_LOCKER_FILE);

        if (!lockInstance) {
            JOptionPaneUtils.showErrorDialog(null, SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_SINGLEINSTANCE));
            return false;
        }
        return true;
    }

	public static Dimension getWindowSize(){
        try{
            int width= Integer.parseInt(configProp.getProperty(Constants.WINDOW_WIDTH));
            int height= Integer.parseInt(configProp.getProperty(Constants.WINDOW_HEIGHT));
            return new Dimension(width, height);
        }catch (Exception e){
            LOGGER.info("Fail to get Width and Height, set default value");
            return new Dimension(Constants.WINDOW_WIDTH_DEFAULT,Constants.WINDOW_HEIGHT_DEFAULT);
        }
    }
	
}
