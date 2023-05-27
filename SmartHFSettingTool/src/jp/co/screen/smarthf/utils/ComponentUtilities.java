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

		// �R���|�[�l���g�̕������킹��.
		setAllCompWidthMaxSize( inComponents );

		// X�����̈ʒu�𒆉��ɃZ�b�g����.
		for( JComponent comp : inComponents ) {
			comp.setAlignmentX( JComponent.CENTER_ALIGNMENT );
		}
	}
	
	public static void setAllCompWidthMaxSize( List<? extends JComponent> inComponents ) {

		if( inComponents == null || inComponents.isEmpty() ) {
			return;
		}

		//EqTr-UI-0416 �{�^�������݂��邩�H
		boolean isButtton = false;
		for (JComponent component : inComponents) {
			if(component instanceof JButton){
				isButtton = true;
				break;
			}
		}

		// �ő啝�����߂�.
		int maxwidth = 0;
		for( JComponent comp : inComponents ) {

			//EqTr-UI-0416 Mac�Ń{�^�������݂���ꍇ�́A�����T�C�Y��傫�߂ɂ��Ă����B"Cancel" �� "Can..." �ƂȂ��Ă����B
			//�����{�^�������łȂ���΁A�{�^�������݂��邩�Ƃ����������l�������B
			int compW = comp.getPreferredSize().width;

			maxwidth = maxwidth < compW ? compW : maxwidth;
		}

		// �S�R���|�[�l���g�̕����ő啝�ɃZ�b�g����.
		for( JComponent comp : inComponents ) {
			Dimension new_size = new Dimension( maxwidth, comp.getPreferredSize().height );
			comp.setMaximumSize( new_size );
			comp.setPreferredSize( new_size );
		}

	}
}
