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

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import jp.co.screen.equios.ui.common.bean.table.AbstractTableModelEx;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.utils.CommonUtils;
import jp.co.screen.smarthf.utils.FileUtils;
import jp.co.screen.smarthf.common.resource.LangKeySmartHF;;

/**
 * table model
 * @author hoavanngo.fd
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFTableModel extends AbstractTableModelEx{

	private List<SmartHFDataModel> mResourceList= new ArrayList<SmartHFDataModel>();
	
	public SmartHFTableModel() {
		super();
		init();
	}
	
	private void init (){
		loadSmartHFData();
	}
	
	public void refresh()
	{
		fireTableDataChanged();
	}
	
	/**
	 * 
	 * Method description
	 * @param inLstObj
	 * @author hoavanngo.fd
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public void setResourceList(List<SmartHFDataModel> inLstObj){
		this.mResourceList = inLstObj;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int getRowCount() {
		// TODO Auto-generated method stub
        if( mResourceList == null ) return 0;
	        return mResourceList.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if( !checkValuePosition(rowIndex, columnIndex)){
            return null;
        }
		
		SmartHFDataModel obj = mResourceList.get(rowIndex);
		
		if(obj == null)
			return null;
		
		String colName = getColumnName( columnIndex );
		
		return convertToColumnValue(obj, colName);
	}

	private Object convertToColumnValue (SmartHFDataModel obj, String colName){
		
		//compare with the resource of each column and return corresponding value
		if (colName.equals(LangKeySmartHF.WORD_HFNAME)) {
			return obj.getSmartHFPropertyFileModel().getDisplayName();
		}
		if (colName.equals(LangKeySmartHF.WORD_RULE)) {
			return obj.getSmartHFRulePropertyFileModel().getRule();
		}
		
		return null;
	}
	
	private void loadSmartHFData() {
		List<String> hfList = CommonUtils.getHotfolderNameList();
		for (int idx = 0; idx < hfList.size(); idx++) {
			SmartHFPropertyFileModel propertyFileModel = CommonUtils.readPropertyFile(Paths.get(FileUtils.getSmartHotfolderRootPath(), hfList.get(idx)).toString());
			SmartHFRulePropertyFileModel rulePropertyFileModel = CommonUtils.readRulePropertyFile(Paths.get(FileUtils.getSmartHotfolderRootPath(), hfList.get(idx)).toString());
			
			if (propertyFileModel != null && rulePropertyFileModel != null) {
				SmartHFDataModel dataModel = new SmartHFDataModel();
				dataModel.setSmartHFPropertyFileModel(propertyFileModel);
				dataModel.setSmartHFRulePropertyFileModel(rulePropertyFileModel);
				
				mResourceList.add(dataModel);
			}
		}
	}
	
	public void addSmartHFData(SmartHFDataModel inSmartHFData) {
		mResourceList.add(inSmartHFData);
		refresh();
	}
	
	public void removeSmartHFData(SmartHFDataModel inSmartHFData) {
		mResourceList.remove(inSmartHFData);
		refresh();
	}
	
	public SmartHFDataModel getSmartHFData(int inRowIdx) {
		SmartHFDataModel dataModel = null;
		if(inRowIdx < mResourceList.size()){
			dataModel = mResourceList.get(inRowIdx);
		}
		
		return dataModel;
	}
}
