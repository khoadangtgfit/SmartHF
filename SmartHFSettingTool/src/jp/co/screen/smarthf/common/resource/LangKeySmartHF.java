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

package jp.co.screen.smarthf.common.resource;

/**
 * Resource of LBTS
 * @author hoavanngo.fd
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public interface LangKeySmartHF {
	
	static String RESOURCE_PATH = "SmartHFSettingTool.res";
	
	//error loading resource file
	String MSG_ERR_LOADFOLDERSTRUCTURENFLCFAIL = "msg.err.LoadFolderStructureNFLCFail";
	
	//error single instance
	String MSG_ERR_SINGLEINSTANCE = "msg.err.SingleInstance";
	
	//confirmed message read only mode
	String MSG_INF_READONLYMODECONFIRM = "msg.inf.ReadOnlyModeConfirm";
	
	//confirmed message exit
	String MSG_INF_EXITCONFIRM = "msg.inf.ExitConfirm";
	
	//dialog title
	String WORD_SMARTHFTITLE = "word.SmartHFTitle";
	
	//dialog title
	String WORD_SMARTHFPROPERTYTITLE = "word.SmartHFPropertyTitle";
	
	// label
	String WORD_SMARTHFLIST = "word.SmartHFList";

	// Column "HF Name" in table
	String WORD_HFNAME = "word.HFName";

	// Column "Rule" in table
	String WORD_RULE = "word.Rule";
	
	String MSG_ERR_BLANKORNULL_HFNAME = "msg.err.BlankOrNullHFName";
	
	String MSG_ERR_SPACEFRONTENDNAME = "msg.err.SpaceFrontEndName";
	
	String MSG_ERR_SAMEDATAEXISTS = "msg.err.SameDataExisits";
	
	String MSG_ERR_PROHIBITED_CHARACTER_HFNAME = "msg.err.ProhibitedCharacterHFName";

	String MSG_INF_DELETEHOTFOLDERCONFIRM = "msg.inf.DeleteHotfolderConfirm";
	
	String MSG_ERR_CANTDELETEHF = "msg.err.CantDeleteHF";
	
	String WORD_FILENAMERULESETTINGTITLE = "word.FileNameRuleSettingTitle";
	
	String WORD_FILENAMERULETITLE = "word.FileNameRuleTitle";
	
	String WORD_DEFAULT_HFNAME = "word.DefaultHFName";

	String MSG_INF_EXITDLGSMARTHFPROPERTY = "msg.inf.ExitDlgSmartHFProperty";
	
	String MSG_ERR_PROHIBITED_CHARACTER_SEPARATOR = "msg.err.ProhibitedCharacterSeparator";
	
	String MSG_ERR_BLANKORNULL_SEPARATOR = "msg.err.BlankOrNullSeparator";
	
	String MSG_ERR_LIMITEDLENGTHSEPARATOR = "msg.err.LimitedLengthSeparator";

	String MSG_ERR_FAIL_TO_USE_SAME_MACRO = "msg.err.FailToUseSameMacro";
	
	String MSG_ERR_NOT_ENOUGH_REQUIRE_MACRO = "msg.err.NotEnoughRequireMacro";
	
	String MSG_ERR_FAILTOUPDATEHF = "msg.err.FailToUpdateHF";

	String MSG_ERR_CHANGE_NAME_FOR_HF_NAME = "msg.err.ChangeNameForHFName";
	
	String MSG_INF_CHECK_VALID_BEFORE_EDIT_HF_NAME = "msg.inf.CheckValidBeforeEditHFName";

	String MSG_ERR_INVALID_MACRO = "msg.err.InvalidMacro";
	
	String MSG_ERR_CANT_COPY_HF = "msg.err.CantCopyHF";
	
	String MSG_ERR_CANT_DELETE_HF = "msg.err.CantDeleteHF";
}
