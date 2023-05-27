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

package jp.co.screen.smarthf.model;

/**
 * Class Description
 * @author 26212009
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropertyFileModel {

	private String mDisplayName;
	private String mIconName;
	private boolean mActivation;
	private String mComment;
	private long mCreatedDate;
	private long mLastModifiedDate;
	
	/** 
	 * Constructor of SmartHFPropertyFileModel.java
	 *
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFPropertyFileModel() {
		if (mDisplayName == null){
			mDisplayName = new String();
		}
		
		if (mIconName == null){
			mIconName = new String();
		}
		
		if (mComment == null){
			mComment = new String();
		}
		
		mActivation = false;
		mCreatedDate = 0;
		mLastModifiedDate = 0;
	}

	public String getDisplayName() {
		return mDisplayName;
	}
	
	public void setDisplayName(String inDisplayName){
		mDisplayName = inDisplayName;
	}
	
	public String getIconName() {
		return mIconName;
	}
	
	public void setIconName(String inIconName) {
		mIconName = inIconName;
	}
	
	public boolean getActivation() {
		return mActivation;
	}
	
	public void setActivation(boolean inActivation) {
		mActivation = inActivation;
	}
	
	public String getComment() {
		return mComment;
	}
	
	public void setComment(String inComment) {
		mComment = inComment;
	}
	
	public long getCreatedDate() {
		return mCreatedDate;
	}
	
	public void setCreatedDate(long inCreatedDate) {
		mCreatedDate = inCreatedDate;
	}
	
	public long getLastModifiedDate() {
		return mLastModifiedDate;
	}
	
	public void setLastModifiedDate(long inLastModifiedDate) {
		mLastModifiedDate = inLastModifiedDate;
	}
}
