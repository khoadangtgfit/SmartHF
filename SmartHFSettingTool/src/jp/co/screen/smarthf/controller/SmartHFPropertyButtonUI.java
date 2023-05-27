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

package jp.co.screen.smarthf.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Class Description
 * 
 * @author hoavanngo.fd
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFPropertyButtonUI extends BasicButtonUI {

  private int mBtnSize;
  private boolean mIsAddBtn;

  /**
   * Constructor of LBRSAddAction.java
   *
   * @param inParent
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  public SmartHFPropertyButtonUI() {
    mBtnSize = 20;
    mIsAddBtn = true;
  }

  /**
   * Constructor of LBRSAddAction.java
   *
   * @param inParent
   * @since EQUIOS V2.00EQ001T1 EQF#C320-003
   */
  public SmartHFPropertyButtonUI(int inButtonSize, boolean inIsAddButton) {
    mBtnSize = inButtonSize < 20 ? 20 : inButtonSize;
    mIsAddBtn = inIsAddButton;
  }

  /**
   * {@inheritDoc}
   */
  protected void installDefaults(AbstractButton b) {
    // load shared instance defaults
    super.installDefaults(b);
    b.setBorder(new EmptyBorder(0, 0, 0, 0));
    b.setContentAreaFilled(false);
    b.setOpaque(true);

    Dimension size = new Dimension(mBtnSize, mBtnSize);
    b.setPreferredSize(size);
    b.setMaximumSize(size);
    b.setMinimumSize(size);
  }

  public void paint(Graphics g, JComponent c) {
    Graphics2D g2D = (Graphics2D) g;

    int rectWidth = mBtnSize / 2;
    int length = 4;

    int x = 0;
    int y = 0;
    if (mIsAddBtn) {
      g2D.setColor(Color.GREEN);
      g2D.fillOval(0, 0, mBtnSize, mBtnSize);

      g2D.setColor(Color.WHITE);

      x = (mBtnSize - rectWidth) / 2;
      y = (mBtnSize - length) / 2;
      g2D.fillRect(x, y, rectWidth, length);

      x = (mBtnSize - length) / 2;
      y = (mBtnSize - rectWidth) / 2;
      g2D.fillRect(x, y, length, rectWidth);
    } else {
      g2D.setColor(Color.RED);
      g2D.fillOval(0, 0, mBtnSize, mBtnSize);

      g2D.setColor(Color.WHITE);

      x = (mBtnSize - rectWidth) / 2;
      y = (mBtnSize - length) / 2;
      g2D.fillRect(x, y, rectWidth, length);
    }

    Stroke oldStroke = g2D.getStroke();
    g2D.setStroke(new BasicStroke(1));
    g2D.drawOval(2, 2, mBtnSize - 4, mBtnSize - 4);
    g2D.setStroke(oldStroke);
  }
}
