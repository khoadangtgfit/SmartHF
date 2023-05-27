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

package jp.co.screen.smarthf.view.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import jp.co.screen.equios.ui.common.bean.table.JXTableEx;
import jp.co.screen.equios.ui.common.bean.table.TableDefaultInfo;
import jp.co.screen.equios.ui.common.bean.table.TableViewport;
import jp.co.screen.equios.ui.common.bean.table.renderer.DefaultTableRendererEx;
import jp.co.screen.equios.ui.common.util.comparator.XPStringComparator;
import jp.co.screen.equios.ui.common.util.guiproperties.GUIPropHolder;
import jp.co.screen.equios.ui.common.util.guiproperties.GUIProperties;
import jp.co.screen.smarthf.common.resource.LangKeySmartHF;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.model.SmartHFTableModel;
import jp.co.screen.smarthf.view.property.SmartHFPropertyFrm;

import org.apache.log4j.Logger;

/**
 * Class Description
 * @author syptn
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFMainContentPnl extends JPanel{
	
    private static Logger LOGGER=Logger.getLogger(SmartHFMainContentPnl.class);
	
    private JXTableEx mTable;
    
    private TableDefaultInfo mTblDefaultInfo;
    
    private SmartHFTableModel mTblModel;
    
    private JScrollPane mScrollPane;
    
    private String LBRS_PROP = "LBRS_PROP";

    private JPanel mPnlTop;
    
    private SmartHFManagerPnl mParentContainer;

    /**
     * 
     */
	public SmartHFMainContentPnl(SmartHFManagerPnl inParentContainer){
		super();
		
		mParentContainer = inParentContainer;
		
		init();
	}
	
	/**
	 * 
	 * Method description
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	private void init(){
		setLayout(new BorderLayout());
		add(getScrollPane(),BorderLayout.CENTER);
		add(getTopPnl(), BorderLayout.NORTH);
		
		// Create LEFT pannel
		JPanel leftPnl = new JPanel();
		leftPnl.setPreferredSize(new Dimension(20, 0));
		add(leftPnl, BorderLayout.WEST);
		
		// Create LEFT pannel
		JPanel bottomPnl = new JPanel();
		bottomPnl.setPreferredSize(new Dimension(0, 40));
		add(bottomPnl, BorderLayout.SOUTH);

	}

	/**
	 * 
	 * Method description
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	private JScrollPane getScrollPane(){
		if(this.mScrollPane == null){
			TableViewport viewPortr = new TableViewport();
			viewPortr.setView(getTable());
			this.mScrollPane = new JScrollPane();		
			this.mScrollPane.setViewport(viewPortr);		
		}
		
		return mScrollPane;
	}
	
	/**
	 * main table
	 * 
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public JXTableEx getTable(){
		if(mTable == null){
			mTable = new JXTableEx(getTblModel(), getGUIProp(), getTblDefaultInfo(), SmartHFResource.getInstance());
		
			mTable.addMouseListener (new MouseAdapter() {
				/**
				 * {@inheritDoc}
				 */
				@Override
				public void mouseClicked(MouseEvent inEvent) {
					int selectedRowCount = mTable.getSelectedRowCount();
					
					// Update buttons state
					mParentContainer.updateBtnsState(selectedRowCount);
					if (inEvent.getClickCount() == 2 && !mParentContainer.isReadOnly()) {
						new SmartHFPropertyFrm(mParentContainer.getBtnsPanel(), true);
					}
				}
			});
			
			mTable.setIntercellSpacing(new Dimension(0,0));
	
			mTable.setAutoResizeMode(JXTableEx.AUTO_RESIZE_NEXT_COLUMN);
			mTable.setAutoCreateRowSorter(true);
			mTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}

		return mTable;
	}
	
	/**
	 * table model
	 * 
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFTableModel getTblModel(){
		
		if(this.mTblModel == null){
			this.mTblModel = new SmartHFTableModel();
			
			for(int loop=0 ; loop<getTblDefaultInfo().getColumnDataCount() ; loop++){
				mTblModel.addColumn(getTblDefaultInfo().getColumnData(loop).getName()) ;
			}
		}
		
		return this.mTblModel;
	}
	
	/**
	 * table default info
	 * 
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	private TableDefaultInfo getTblDefaultInfo(){
		if(this.mTblDefaultInfo == null){
			this.mTblDefaultInfo = new TableDefaultInfo(LBRS_PROP);
			
			mTblDefaultInfo.addColumnData(LangKeySmartHF.WORD_HFNAME, 150, true,
					new DefaultTableRendererEx(), new XPStringComparator());
			mTblDefaultInfo.addColumnData(LangKeySmartHF.WORD_RULE, 250, true,
					new DefaultTableRendererEx(), new XPStringComparator());
		}
		
		return mTblDefaultInfo;
	}
 
	/**
	 * get GUI prop
	 * 
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
    public GUIProperties getGUIProp(){
    	return GUIPropHolder.getGUIProp( LBRS_PROP );
    }
    
	/**
	 * top panel for add label
	 * 
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	private JPanel getTopPnl(){
		if(mPnlTop == null){
			mPnlTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
			mPnlTop.add(new JLabel(SmartHFResource.getResource(LangKeySmartHF.WORD_SMARTHFLIST) + ":", JLabel.LEFT));
		}
		return this.mPnlTop;
	}
	
	public SmartHFDataModel getSmartHFSelectedRowData() {
		int selectedRowIdx =  mTable.getSelectedRow();
		if (selectedRowIdx != -1) {
			int modelIdx = mTable.convertRowIndexToModel(selectedRowIdx);
			
			return mTblModel.getSmartHFData(modelIdx);
		} else {
			return null;
		}
	}
	
	public List<SmartHFDataModel> getSmartHFSelectedRowsData() {
		List<SmartHFDataModel> dataList = new LinkedList<SmartHFDataModel>();
		int[] rowSelectedIdx = mTable.getSelectedRows();
		for (int idx = 0; idx < rowSelectedIdx.length; idx++) {
			int modelIdx = mTable.convertRowIndexToModel(rowSelectedIdx[idx]);
			SmartHFDataModel dataModel = mTblModel.getSmartHFData(modelIdx);
			if (dataModel != null) {
				dataList.add(dataModel);
			}
		}
		
		return dataList;
	}
}
