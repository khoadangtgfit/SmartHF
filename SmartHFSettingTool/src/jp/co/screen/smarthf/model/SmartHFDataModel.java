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

package jp.co.screen.smarthf.model;

import jp.co.screen.egleg.cfw.chef.InprogressResourceInfo;
import jp.co.screen.smarthf.model.*;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFDataModel {

	private SmartHFPropertyFileModel mPropertyFileModel;
	private SmartHFRulePropertyFileModel mRulePropertyFileModel;
	
	/** 
	 * Constructor of SmartHFDataModel.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFDataModel() {
		if (mPropertyFileModel == null) {
			mPropertyFileModel = new SmartHFPropertyFileModel();
		}
		
		if (mRulePropertyFileModel == null) {
			mRulePropertyFileModel = new SmartHFRulePropertyFileModel();
		}
	}

	public SmartHFPropertyFileModel getSmartHFPropertyFileModel() {
		return mPropertyFileModel;
	}
	
	public void setSmartHFPropertyFileModel (SmartHFPropertyFileModel inSmartHFPropertyFileModel) {
		mPropertyFileModel = inSmartHFPropertyFileModel;
	}
	
	public SmartHFRulePropertyFileModel getSmartHFRulePropertyFileModel() {
		return mRulePropertyFileModel;
	}
	
	public void setSmartHFRulePropertyFileModel(SmartHFRulePropertyFileModel inSmartHFRulePropertyFileModel) {
		mRulePropertyFileModel = inSmartHFRulePropertyFileModel;
	}
	
	public SmartHFDataModel  clone() {   
		SmartHFDataModel newDataModel = new SmartHFDataModel();
		
		SmartHFPropertyFileModel newPropertyFileModel = newDataModel.getSmartHFPropertyFileModel();
		newPropertyFileModel.setDisplayName(new String(mPropertyFileModel.getDisplayName()));
		newPropertyFileModel.setActivation(mPropertyFileModel.getActivation());
		newPropertyFileModel.setComment(new String(mPropertyFileModel.getComment()));
		newPropertyFileModel.setIconName(new String(mPropertyFileModel.getIconName()));
		newPropertyFileModel.setCreatedDate(mPropertyFileModel.getCreatedDate());
		newPropertyFileModel.setLastModifiedDate(mPropertyFileModel.getLastModifiedDate());
		
		SmartHFRulePropertyFileModel  newRulePropertyFileModel = newDataModel.getSmartHFRulePropertyFileModel();
		newRulePropertyFileModel.setRule(new String(mRulePropertyFileModel.getRule()));
		newRulePropertyFileModel.setScript(new String(mRulePropertyFileModel.getScript()));
		
		return newDataModel;
	}
}
