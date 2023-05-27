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

package jp.co.screen.smarthf.common.resource;

import java.io.File;
import java.util.Locale;

import jp.co.screen.equios.ui.common.resource.lang.LangResource;
import jp.co.screen.equios.ui.common.resource.lang.LangResourceHolder;
import jp.co.screen.equios.ui.config.ClientConfigMap;
import jp.co.screen.equios.ui.resource.lang.common.LangKeyCommon;

/**
 * Class Description
 * @author hoavanngo.fd
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFResource {
	protected static LangResource mLangResource;
	
	/**
	 * init resource
	 * 
	 * @return
	 * @author hoavanngo.fd
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
    public static boolean init(){
        File commonResFile= new File(createLocalePath(LangKeyCommon.RESOURCE_PATH));
        File lbrsResFile= new File(createLocalePath(LangKeySmartHF.RESOURCE_PATH));
        if( commonResFile.exists() && lbrsResFile.exists()){
            mLangResource= LangResourceHolder.getResource(LangKeySmartHF.RESOURCE_PATH);
            return true;
        }
        return false;
        
    }
    
    /**
     * get instance of resouce
     * 
     * @return
     * @author hoavanngo.fd
     * @since EQUIOS V2.00EQ001T1 EQF#C320-003
     */
    public static LangResource getInstance(){
        return mLangResource;
    }
    
    /**
     * get resource
     * 
     * @param inLangKey
     * @return
     * @author hoavanngo.fd
     * @since EQUIOS V2.00EQ001T1 EQF#C320-003
     */
    public static String getResource(String inLangKey){
        return mLangResource.getProperty(inLangKey);
    }
   
    /**
     * create locale path
     * 
     * @param inFilePath
     * @return
     * @author hoavanngo.fd
     * @since EQUIOS V2.00EQ001T1 EQF#C320-003
     */
    private final static String createLocalePath( String inFilePath ){
        return ClientConfigMap.getCanonicalPath( ClientConfigMap.getSysDirResource() + "/lang/" + Locale.getDefault().getLanguage() + "/" + inFilePath ) ;
    }
}
