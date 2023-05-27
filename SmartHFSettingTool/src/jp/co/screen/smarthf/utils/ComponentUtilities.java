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

import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;

public class ComponentUtilities {
	public static void setButtonPanelComponentsProperties( List<JComponent> inComponents  ) {

		if( inComponents == null || inComponents.isEmpty() ) {
			return;
		}

		// コンポーネントの幅を合わせる.
		setAllCompWidthMaxSize( inComponents );

		// X方向の位置を中央にセットする.
		for( JComponent comp : inComponents ) {
			comp.setAlignmentX( JComponent.CENTER_ALIGNMENT );
		}
	}
	
	public static void setAllCompWidthMaxSize( List<? extends JComponent> inComponents ) {

		if( inComponents == null || inComponents.isEmpty() ) {
			return;
		}

		//EqTr-UI-0416 ボタンが存在するか？
		boolean isButtton = false;
		for (JComponent component : inComponents) {
			if(component instanceof JButton){
				isButtton = true;
				break;
			}
		}

		// 最大幅を求める.
		int maxwidth = 0;
		for( JComponent comp : inComponents ) {

			//EqTr-UI-0416 Macでボタンが存在する場合は、推奨サイズを大きめにしておく。"Cancel" ⇒ "Can..." となっていた。
			//もしボタンだけでなければ、ボタンが存在するかという条件を考え直す。
			int compW = comp.getPreferredSize().width;

			maxwidth = maxwidth < compW ? compW : maxwidth;
		}

		// 全コンポーネントの幅を最大幅にセットする.
		for( JComponent comp : inComponents ) {
			Dimension new_size = new Dimension( maxwidth, comp.getPreferredSize().height );
			comp.setMaximumSize( new_size );
			comp.setPreferredSize( new_size );
		}

	}
}
