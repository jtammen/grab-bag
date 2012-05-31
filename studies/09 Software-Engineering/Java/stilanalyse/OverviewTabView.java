package stilanalyse;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.table.*;
import stilanalyse.Application;

public class OverviewTabView 

{
	private JPanel overviewPanel;
	private JProgressBar progressBar;
	private JSplitPane overviewSplit1;
	private JSplitPane overviewSplit2;
	private JTable satzAnalyseTable;
	private DefaultTableModel satzAnalyseTableModel = new DefaultTableModel();
	private JTable wortAnalyseTable;
	private DefaultTableModel wortAnalyseTableModel = new DefaultTableModel();
	private OverviewRomanTabModel tableModel;
	private JTable romanTable = new JTable(tableModel);
	private OverviewTabViewMouseListener listener = new OverviewTabViewMouseListener(this,MainFrameView.wordTab);


	public OverviewTabView()
	{
		setRomanTable();
		romanTable.addMouseListener(listener);
		
	}
	public JPanel getOverviewPanel() 
	{
		if (overviewPanel == null) 
		{
			overviewPanel = new JPanel();
			overviewPanel.setLayout(new BorderLayout());
			
			overviewPanel.add(getProgressBar(), BorderLayout.SOUTH);
			overviewPanel.add(getOverviewSplit1(), BorderLayout.CENTER);
		
		}
		return overviewPanel;
	}
	
	
	public JSplitPane getOverviewSplit1() {
		if (overviewSplit1 == null) 
		{
			JPanel Panel1 = new JPanel();
			overviewSplit1 = new JSplitPane();
			
			overviewSplit1.setDividerLocation(460);
			overviewSplit1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			
			Panel1.setLayout(new BorderLayout());
			Panel1.add(new JLabel("Eingelesene Roman:"), BorderLayout.NORTH);
			Panel1.add(new JScrollPane(getRomanTable()), BorderLayout.CENTER);
			
			overviewSplit1.setLeftComponent(Panel1);
			overviewSplit1.setRightComponent(getOverviewSplit2());
		}
		return overviewSplit1;
	}
	
	public JSplitPane getOverviewSplit2() {
		if (overviewSplit2 == null) 
		{
			overviewSplit2 = new JSplitPane();
			JPanel Panel1 = new JPanel();
			JPanel Panel2 = new JPanel();
			
			overviewSplit2.setDividerLocation(500);
			
			Panel1.setLayout(new BorderLayout());
			Panel1.add(new JLabel("Wortanalyse Überblick:"), BorderLayout.NORTH);
			Panel1.add(new JScrollPane(getWortAnalyseTable()), BorderLayout.CENTER);
			
			Panel2.setLayout(new BorderLayout());
			Panel2.add(new JLabel("Satzanalyse Überblick:"), BorderLayout.NORTH);
			Panel2.add(new JScrollPane(getSatzAnalyseTable()), BorderLayout.CENTER);
			
			overviewSplit2.setLeftComponent(Panel1);
			overviewSplit2.setRightComponent(Panel2);
		}
		return overviewSplit2;
	}
	
	public JTable getRomanTable() 
	{	
		return romanTable;
	}
	
	public void setRomanTable()
	{
		Vector romane = Application.romanVerwaltung.getRomanListe();
		tableModel = new OverviewRomanTabModel(romane.size());
		romanTable.setModel(tableModel);
		romanTable.setCellSelectionEnabled(false);
		romanTable.setRowSelectionAllowed(true);
		romanTable.setSelectionMode(0);
	}
	
	public JTable getWortAnalyseTable() {
		if (wortAnalyseTable == null) 
		{
			wortAnalyseTable = new JTable(wortAnalyseTableModel);
			wortAnalyseTable.setCellSelectionEnabled(false);
			wortAnalyseTable.setRowSelectionAllowed(true);
			wortAnalyseTable.setSelectionMode(0);
			wortAnalyseTable.setShowGrid(false);
			wortAnalyseTable.setEnabled(false);
			wortAnalyseTableModel.addColumn("Bezeichnung");
			wortAnalyseTableModel.addColumn("Anzahl");
		}
		return wortAnalyseTable;
	}
	
	public void updateWortAnalyseTable()
	{
		
		int index = getRomanTable().getSelectedRow();
		Object[] rowData = new Object[2];
//		WortanalyseResult tempWResult;
		Vector romane = Application.romanVerwaltung.getRomanListe();
		wortAnalyseTableModel.getDataVector().removeAllElements();
		
		
//		tempWResult = (WortanalyseResult)((Roman)romane.get(index)).getWortanalyseResult();
		
//		rowData[0]="kurze Wörter";
//		rowData[1]="= " + tempWResult.getAnzahlWoerter();
//		wortAnalyseTableModel.addRow(rowData);
//		rowData[0]="mittlere Wörter";
//		rowData[1]="= " + tempWResult.getAnzahlWoerter();
//		wortAnalyseTableModel.addRow(rowData);
//		rowData[0]="lange Wörter";
//		rowData[1]="= " + tempWResult.getAnzahlWoerter();
//		wortAnalyseTableModel.addRow(rowData);
//		rowData[0]="test";
//		rowData[1]="= " + ((Roman)romane.get(index)).getTitel();
//		wortAnalyseTableModel.addRow(rowData);
//		rowData[0]="test1";
//		rowData[1]="= " + ((Roman)romane.get(index)).getAutor();
//		wortAnalyseTableModel.addRow(rowData);
		
		Iterator itKeys;
		Set keys;
		
		keys=((Roman)romane.get(index)).getWortanalyseResult().getWortartenHaeufigkeiten().keySet();
		
		itKeys = keys.iterator();
		while ( itKeys.hasNext() )
		{
			Object key =itKeys.next();
			rowData[0]=key.toString();
			rowData[1]="= " + ((Roman)romane.get(index)).getWortanalyseResult().getWortartenHaeufigkeiten().get(key);
			wortAnalyseTableModel.addRow(rowData);
		}
		
	}
	
	public JTable getSatzAnalyseTable() {
		if (satzAnalyseTable == null) 
		{
			//Vector romane = romanVerwaltung.getRomanListe();
			satzAnalyseTableModel.addColumn("Bezeichnung");
			satzAnalyseTableModel.addColumn("Anzahl");
			
			
			satzAnalyseTable = new JTable(satzAnalyseTableModel);
			satzAnalyseTable.setCellSelectionEnabled(false);
			satzAnalyseTable.setRowSelectionAllowed(true);
			satzAnalyseTable.setSelectionMode(0);
			satzAnalyseTable.setShowGrid(false);
			satzAnalyseTable.setEnabled(false);
		}
		return satzAnalyseTable;
	}
	
	public  void updateSatzAnalyseTable()
	{
		int index = getRomanTable().getSelectedRow();
		Object[] rowData = new Object[2];
		SatzanalyseResult tempSResult;
		Vector romane = Application.romanVerwaltung.getRomanListe();
		
	
		tempSResult = (SatzanalyseResult)((Roman)romane.get(index)).getSatzanalyseResult();

		satzAnalyseTableModel.getDataVector().removeAllElements();

		Iterator itKeys;
		Set keys;
		int kurzeSaetze = 0;
		int mittlereSaetze = 0; 
		int langeSaetze = 0;
		keys=((Roman)romane.get(index)).getSatzanalyseResult().getAnzahlSatzlaenge().keySet();
		itKeys = keys.iterator();
		while ( itKeys.hasNext() )
		{
			Object key =itKeys.next();
			int i = Integer.parseInt(key.toString());
			
			if(i < Application.UNTERESATZGRENZE)
			{
				kurzeSaetze +=Integer.parseInt(((Roman)romane.get(index)).getSatzanalyseResult().getAnzahlSatzlaenge().get(key).toString());
			}
			else if(i<Application.OBERESATZGRENZE+1)
			{
				mittlereSaetze += Integer.parseInt(((Roman)romane.get(index)).getSatzanalyseResult().getAnzahlSatzlaenge().get(key).toString());
			}
			else
			{
				langeSaetze += Integer.parseInt(((Roman)romane.get(index)).getSatzanalyseResult().getAnzahlSatzlaenge().get(key).toString());
			}
			//rowData[1]="= " + ((Roman)romane.get(index)).getSatzanalyseResult().getAnzahlSatzlaenge().get(key);
			//satzAnalyseTableModel.addRow(rowData);
		}
		
		rowData[0]="kurze Sätze(<"+ new Integer(Application.UNTERESATZGRENZE).toString() + " Zeichen)";
		rowData[1]="= " + kurzeSaetze;
		satzAnalyseTableModel.addRow(rowData);
		rowData[0]="mittlere Sätze";
		rowData[1]="= " + mittlereSaetze;
		satzAnalyseTableModel.addRow(rowData);
		rowData[0]="lange Sätze(>"+ new Integer(Application.OBERESATZGRENZE).toString() + " Zeichen)";
		rowData[1]="= " + langeSaetze;
		satzAnalyseTableModel.addRow(rowData);
		rowData[0]="";
		rowData[1]=""; 
		satzAnalyseTableModel.addRow(rowData);
		rowData[0]="Anzahl Sätze";
		rowData[1]="= " + tempSResult.getAnzahlSaetze();
		satzAnalyseTableModel.addRow(rowData);
		rowData[0]="Sätze mit Nebensätzen";
		rowData[1]="= " + tempSResult.getAnzahlNebensaetze();
		satzAnalyseTableModel.addRow(rowData);
		rowData[0]="durchschnittliche Satzlänge (in Zeichen)";
		rowData[1]="= " + tempSResult.getDurchschnittlicheSatzlaenge();
		satzAnalyseTableModel.addRow(rowData);
	}
	
	public int getSelectedRoman()
	{
		return getRomanTable().getSelectedRow();
	}
	
	private JProgressBar getProgressBar() {
		if (progressBar == null) 
		{
			progressBar = new JProgressBar();
		}
		return progressBar;
	}
	
	public JTable getTModel() {
		return romanTable;
	}

}