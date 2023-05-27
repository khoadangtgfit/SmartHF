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

package jp.co.screen.smarthf.utils;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JComponent;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import jp.co.screen.egleg.rlx.hfprop.HFprop;
import jp.co.screen.egleg.rlx.hfprop.SmartHotFolder;
import jp.co.screen.egleg.ruleEngine.RuleEngineMacro;
import jp.co.screen.smarthf.common.Constants;
import jp.co.screen.smarthf.common.resource.LangKeySmartHF;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.model.SmartHFPropertyFileModel;
import jp.co.screen.smarthf.model.SmartHFRulePropertyFileModel;
import jp.co.screen.smarthf.model.SmartHFTableModel;
import jp.co.screen.smarthf.utils.FileUtils;


/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class CommonUtils {
	private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);

	public static void copyHotfolder(String inSrcHFName, String inDstHFName) {
		LOGGER.info("Start CommonUtils.copyHotfolder");
		
		String hfRootPath = FileUtils.getSmartHotfolderRootPath();
		
		String srcHFPath = Paths.get(hfRootPath, inSrcHFName).toString();
		String dstHFPath = Paths.get(hfRootPath, inDstHFName).toString();
		
		File srcHFFile = new File(srcHFPath);
		File dstHFFile = new File(dstHFPath);
		
		if (!srcHFFile.exists()) {
			LOGGER.info("copyHotfolder: no source file: " + srcHFPath);
			LOGGER.info("End CommonUtils.copyHotfolder");
			return;
		}
		if (!srcHFFile.canRead()) {
			LOGGER.info("copyHotfolder: source file is unreadable: " + srcHFPath);
			LOGGER.info("End CommonUtils.copyHotfolder");
			return;
		}

		try {
			if (dstHFFile.mkdir()) {
				File srcPropertyFile = new File(Paths.get(srcHFPath, Constants.PROPERTY_FILE).toString());
				File srcRulePropertyFile = new File(Paths.get(srcHFPath, Constants.RULE_PROPERTY_FILE).toString());

				File dstPropertyFile = new File(Paths.get(dstHFPath, Constants.PROPERTY_FILE).toString());
				File dstRulePropertyFile = new File(Paths.get(dstHFPath, Constants.RULE_PROPERTY_FILE).toString());

				FileUtils.copy(srcPropertyFile, dstPropertyFile);
				FileUtils.copy(srcRulePropertyFile, dstRulePropertyFile);
			}
		} catch (IOException e) {
			LOGGER.info("Fail to copy Hotfolder");
		} finally {
		}
		
		LOGGER.info("End CommonUtils.copyHotfolder");
	}
	
	public static boolean deleteHotfolder(String inHFName) {
		LOGGER.info("Start CommonUtils.deleteHotfolder");

		File hotfolderDir = new File(FileUtils.getSmartHotfolderRootPath(),inHFName);
		if (!hotfolderDir.exists()) {
			LOGGER.info("Fail to delete Hotfolder, Hotfolder not exist: " + hotfolderDir.getAbsolutePath());
			LOGGER.info("End CommonUtils.deleteHotfolder()");
			return false;
		}

		File[] subFiles = hotfolderDir.listFiles();
		if (null != subFiles) {
			for (File subFile : subFiles) {
				subFile.delete();
			}
		}
		
		LOGGER.info("End CommonUtils.deleteHotfolder()");
		return hotfolderDir.delete();
	}
	
	public static boolean createHotFolder(String inHFName, SmartHFDataModel inDataModel, SmartHFTableModel inTableModel) {
		// Creat HotFolder
		String hfPath = Paths.get(FileUtils.getSmartHotfolderRootPath(), inHFName).toString();
		FileUtils.makeDirectory(hfPath);
		
		// Write Property file
		if (!writePropertyFile(hfPath, inDataModel.getSmartHFPropertyFileModel())) {
			return false;
		}
		
		// Write RuleProperty file
		if (!writeRulePropertyFile(hfPath, inDataModel.getSmartHFRulePropertyFileModel())) {
			return false;
		}
		
		// Update table
		inTableModel.addSmartHFData(inDataModel);
		
		return true;
	}

	public static boolean editHotFolder(String inHFName, SmartHFDataModel inDataModel, SmartHFTableModel inTableModel) {
		// Creat HotFolder
		String hfPath = Paths.get(FileUtils.getSmartHotfolderRootPath(), inHFName).toString();
		
		// Write Property file
		if (!writePropertyFile(hfPath, inDataModel.getSmartHFPropertyFileModel())) {
			return false;
		}
		
		// Write RuleProperty file
		if (!writeRulePropertyFile(hfPath, inDataModel.getSmartHFRulePropertyFileModel())){
			return false;
		}
		
		// Update table
		inTableModel.refresh();
		
		return true;
	}
	
	public static List<String> getHotfolderNameList(){
		File rootHF = new File(FileUtils.getSmartHotfolderRootPath());
		
		List<String> hfNameList = new ArrayList<String>();
		
		File[] subFiles = rootHF.listFiles();
		if (subFiles != null) {
			for (File file : subFiles) {
				if (FileUtils.isExistFolder(file.getAbsolutePath())) {
					String propertyFilePath = Paths.get(file.getAbsolutePath(), Constants.PROPERTY_FILE).toString();
					String rulePropertyFilePath = Paths.get(file.getAbsolutePath(), Constants.RULE_PROPERTY_FILE).toString();
					if (FileUtils.isExist(propertyFilePath) && FileUtils.isExist(rulePropertyFilePath)) {
						hfNameList.add(file.getName());
					}
				}
			}
		}
		
		return hfNameList;
	}

	public static SmartHFRulePropertyFileModel readRulePropertyFile(String inHFPath) {
		LOGGER.info("Start CommonUtils.readRulePropertyFile()");
		
		String ruleFilePath = Paths.get(inHFPath, Constants.RULE_PROPERTY_FILE).toString();
		if (!FileUtils.isExist(ruleFilePath)) {
			LOGGER.info("Failed to read rule property file, .ruleProperty file not exist: " + ruleFilePath);
			LOGGER.info("End CommonUtils.readRulePropertyFile()");
			
			return null;
		}

		InputStream input = null;
		Properties properties = new Properties();
		SmartHFRulePropertyFileModel rulePropertyModel = new SmartHFRulePropertyFileModel();

		try {
			input = new FileInputStream(ruleFilePath);

			properties.load(input);

			rulePropertyModel.setScript(properties.getProperty(Constants.KEY_SCRIPT));
			LOGGER.info("Script: " + properties.getProperty(Constants.KEY_SCRIPT));

			rulePropertyModel.setRule(properties.getProperty(Constants.KEY_RULE));
			LOGGER.info("Rule: " + properties.getProperty(Constants.KEY_RULE));
		} catch (IOException e) {
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		LOGGER.info("End CommonUtils.readRulePropertyFile()");
		return rulePropertyModel;
	}

	public static boolean writeRulePropertyFile(String inHFPath, SmartHFRulePropertyFileModel inRulePropertyModel) {
		LOGGER.info("Start CommonUtils.writeRulePropertyFile()");
		
		String ruleFilePath = Paths.get(inHFPath, Constants.RULE_PROPERTY_FILE).toString();
		Properties properties = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream(ruleFilePath);

			properties.setProperty(Constants.KEY_RULE, inRulePropertyModel.getRule());
			LOGGER.info("Rule: " + inRulePropertyModel.getRule());

			properties.setProperty(Constants.KEY_SCRIPT, inRulePropertyModel.getScript());
			LOGGER.info("Script: " + inRulePropertyModel.getScript());

			properties.store(output, Constants.RULE_PROPERTY_FILE);
		} catch (IOException e) {
			return false;
		} finally {
			if (null != output) {
				try {
					if (output != null) {
						output.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		LOGGER.info("End CommonUtils.writeRulePropertyFile()");
		return true;
	}

	public static SmartHFPropertyFileModel readPropertyFile(String inHFPath) {
		LOGGER.info("Start CommonUtils.readPropertyFile()");
		
		String propertyFilePath = Paths.get(inHFPath, Constants.PROPERTY_FILE).toString();
		if (!FileUtils.isExist(propertyFilePath)) {
			LOGGER.info("Failed to read property file, .property file not exist: " + propertyFilePath);
			LOGGER.info("End CommonUtils.readPropertyFile()");

			return null;
		}
		
		SmartHFPropertyFileModel property = new SmartHFPropertyFileModel();

		try {
			HFprop hfProp = new HFprop(propertyFilePath);

			property.setDisplayName(hfProp.getDisplayname());
			LOGGER.info("Displayname: " + hfProp.getDisplayname());

			property.setIconName(hfProp.getIconname());
			LOGGER.info("Iconname: " + hfProp.getIconname());

			property.setComment(hfProp.getComment());
			LOGGER.info("Comment: " + hfProp.getComment());

			property.setCreatedDate(hfProp.getCreated());
			LOGGER.info("Created: " + hfProp.getCreated());

			property.setLastModifiedDate(hfProp.getLastModified());
			LOGGER.info("LastModified: " + hfProp.getLastModified());

			property.setActivation(Boolean.valueOf(hfProp.getContent().getActivation()));
			LOGGER.info("Activation: " + hfProp.getContent().getActivation());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		LOGGER.info("End CommonUtils.readPropertyFile()");
		return property;
	}
	
	public static boolean writePropertyFile(String inHFPath, SmartHFPropertyFileModel inPropertyFileModel) {
		LOGGER.info("Start CommonUtils.writePropertyFile()");
		
		inPropertyFileModel.setActivation(true);
		
		String propertyFilePath = Paths.get(inHFPath, Constants.PROPERTY_FILE).toString();
		File propertyFile = new File(propertyFilePath);
		try {
			propertyFile.createNewFile();
		} catch (IOException e) {
			LOGGER.info("Failed to create property file.");
			LOGGER.info("End CommonUtils.writePropertyFile()");
			e.printStackTrace();
			
			return false;
		}


		SmartHotFolder smartHF = new SmartHotFolder();
		smartHF.setActivation(String.valueOf(inPropertyFileModel.getActivation()));
		
		HFprop hfProp = new HFprop();
		hfProp.setContent(smartHF);
		
		hfProp.setDisplayname(inPropertyFileModel.getDisplayName());
		
		hfProp.setIconname(inPropertyFileModel.getIconName());
		
		hfProp.setComment(inPropertyFileModel.getComment());
		
		if (inPropertyFileModel.getCreatedDate() != 0) {
			hfProp.setCreated(inPropertyFileModel.getCreatedDate());
		} else {
			hfProp.setCreated(DateTimeUtils.getCurTimeStamp());
		}
		
		hfProp.setLastModified(DateTimeUtils.getCurTimeStamp());
		
		OutputStreamWriter output = null;
		try {
			output = new OutputStreamWriter(new FileOutputStream(propertyFile), StandardCharsets.UTF_8);
			try {
				output.write("<?xml version=\"1.0\" encoding=\"" + "UTF-8" + "\"?>");
				output.write(hfProp.toString());
				output.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return true;
	}
	
	public static List<String> getMacroList(String inMacroString) {
		String macroPath = FileUtils.getRuleEngineMacroRootPath();
		
		File macroFile = new File(macroPath);
		RuleEngineMacro ruleEngine = new RuleEngineMacro(macroFile);
		
		return ruleEngine.getExistMacroList(inMacroString);
	}

	public static List<String> getSeparatorList(String inMacroString) {
		String macroPath = FileUtils.getRuleEngineMacroRootPath();
		
		File macroFile = new File(macroPath);
		RuleEngineMacro ruleEngine = new RuleEngineMacro(macroFile);
		
		return ruleEngine.getSeparatorList(inMacroString);
	}

	public static List<String> getRuleEngineMacroList() {
		List<String> ruleEngineMacroList = new LinkedList<String>();
		
		// Get Rule Engine
		String macroPath = FileUtils.getRuleEngineMacroRootPath();
		
		File macroFile = new File(macroPath);
		RuleEngineMacro ruleEngine = new RuleEngineMacro(macroFile);

		Map<String, List<String>> listKey = ruleEngine.getCsvDataMap();
		if(listKey != null && !listKey.isEmpty()) {
			for(Map.Entry<String, List<String>> entry : listKey.entrySet()) {
				ruleEngineMacroList.add(entry.getKey());
			}
		}

		return ruleEngineMacroList;
	}
	
	public static List<String> getRuleEngineRequiredMacroList() {
		List<String> ruleEngineRequiredMacroList = new LinkedList<String>();
		
		// Get Rule Engine
		String macroPath = FileUtils.getRuleEngineMacroRootPath();
		
		File macroFile = new File(macroPath);
		RuleEngineMacro ruleEngine = new RuleEngineMacro(macroFile);

		Map<String, List<String>> listKey = ruleEngine.getCsvDataMap();
		if(listKey != null && !listKey.isEmpty()) {
			for(Map.Entry<String, List<String>> entry : listKey.entrySet()) {
				if (entry.getValue().get(0).equals(Constants.TRUE_STRING)) {
					ruleEngineRequiredMacroList.add(entry.getKey());
				}
			}
		}

		return ruleEngineRequiredMacroList;
	}

	public static boolean checkHFName(Component inParentContainer, String inHFName, boolean inIsCheckExisted) {
		String errMsg = null;
		if (StringUtils.isBlankOrNull(inHFName)) {
			errMsg = SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_BLANKORNULL_HFNAME);
			JOptionPaneUtils.showErrorDialog(inParentContainer, errMsg);
			
			return false;
		}

		if (StringUtils.checkSpaceAndPeriodAtStartAnEnd(inHFName)) {
			errMsg = SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_SPACEFRONTENDNAME);		
			JOptionPaneUtils.showErrorDialog(inParentContainer, errMsg);
			
			return false;
		}

		if (inIsCheckExisted) {
			List<String> hfNameList = getHotfolderNameList();
			for (String hfName : hfNameList) {
				if (hfName.equals(inHFName)) {
					errMsg = SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_SAMEDATAEXISTS);
					JOptionPaneUtils.showErrorDialog(inParentContainer, errMsg);
					
					return false;
				}
			}
		}

		String prohibitedChars = StringUtils.checkProhibited(inHFName);
		if (!prohibitedChars.isEmpty()) {
			errMsg = String.format("%s%s", SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_PROHIBITED_CHARACTER_HFNAME), prohibitedChars);
			JOptionPaneUtils.showErrorDialog(inParentContainer, errMsg);
			
			return false;
		}
		
		return true;
	}
	
	public static boolean checkSeparator(Component inParentContainer, String inSeparator) {
		String errMsg = null;
		if (StringUtils.isBlankOrNull(inSeparator)) {
			errMsg = SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_BLANKORNULL_SEPARATOR);
			JOptionPaneUtils.showErrorDialog(inParentContainer, errMsg);
			
			return false;
		}

		if (inSeparator.getBytes().length >= 20) {
			errMsg = SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_LIMITEDLENGTHSEPARATOR);
			JOptionPaneUtils.showErrorDialog(inParentContainer, errMsg);
			
			return false;
		}

		if (StringUtils.isContainSpace(inSeparator)) {
			errMsg = String.format("%s %s", SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_PROHIBITED_CHARACTER_SEPARATOR), Constants.SPACE_STRING);
			JOptionPaneUtils.showErrorDialog(inParentContainer, errMsg);
			
			return false;
		}

		String prohibitedChars = StringUtils.checkProhibited(inSeparator);
		if (!prohibitedChars.isEmpty()) {
			errMsg = String.format("%s %s", SmartHFResource.getResource(LangKeySmartHF.MSG_ERR_PROHIBITED_CHARACTER_SEPARATOR), prohibitedChars);
			JOptionPaneUtils.showErrorDialog(inParentContainer, errMsg);
			
			return false;
		}
		
		return true;
	}

	public static List<String> getOtherFileList(String inHFName) {
		List<String> otherFileList = new LinkedList<String>();
		File hfFolder = new File(Paths.get(FileUtils.getSmartHotfolderRootPath(), inHFName).toString());
		File[] subFiles = hfFolder.listFiles();
		if (subFiles != null) {
			for (File file : subFiles) {
				if (!file.getName().equals(Constants.PROPERTY_FILE) && !file.getName().equals(Constants.RULE_PROPERTY_FILE)) {
					otherFileList.add(file.getName());
				}
			}
		}

		return otherFileList;
	}
	
	public static void renameHotfolder(String inOldHFName, String inNewHFName) {
		LOGGER.info("Start CommonUtils.copyHotfolder");
		
		String hfRootPath = FileUtils.getSmartHotfolderRootPath();
		
		String oldHFPath = Paths.get(hfRootPath, inOldHFName).toString();
		String newHFPath = Paths.get(hfRootPath, inNewHFName).toString();
		
		File oldHFFile = new File(oldHFPath);
		File newHFFile = new File(newHFPath);
		
		if (!oldHFFile.exists()) {
			LOGGER.info("renameHotfolder: Hot folder not exist: " + oldHFPath);
			LOGGER.info("End CommonUtils.renameHotfolder");
			return;
		}
		if (!oldHFFile.canRead()) {
			LOGGER.info("renameHotfolder: Hot folder is unreadable: " + oldHFPath);
			LOGGER.info("End CommonUtils.copyHotfolder");
			return;
		}

		oldHFFile.renameTo(newHFFile);
		LOGGER.info("End CommonUtils.renameHotfolder");
	}
}
