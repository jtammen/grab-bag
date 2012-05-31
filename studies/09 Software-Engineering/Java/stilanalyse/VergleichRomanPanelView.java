/************************************/
/*Erstellt von:	Sebastian Stenzel  	*/
/*Erstellt am:	19.12.2005			*/
/************************************/

package stilanalyse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import stilanalyse.Application;

public class VergleichRomanPanelView 
extends JPanel
implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1028194650367484411L;
	private JComboBox cobRomanAuswahl = new JComboBox();
	private DefaultTableModel dtmSatzAnalyse = new DefaultTableModel();
	private JTable tblSatzAnalyse = new JTable(dtmSatzAnalyse);
	private DefaultTableModel dtmWortAnalyse = new DefaultTableModel();
	private JTable tblWortAnalyse = new JTable(dtmWortAnalyse);
	int intAuswahl= 0;
	
	public VergleichRomanPanelView()
	{
		
		Vector tempRomanListe = Application.romanVerwaltung.getRomanListe();
		Roman tempRoman;
		//Layout setzen
		cobRomanAuswahl.addActionListener(this);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.add(cobRomanAuswahl,BorderLayout.NORTH);
	
		for (int i= 0 ; i < tempRomanListe.size(); i++)
		{
			tempRoman = (Roman) tempRomanListe.get(i);
			cobRomanAuswahl.addItem(tempRoman.getTitel());
		}
		JPanel PanelTabellen = new JPanel(new BorderLayout());		
		
		JPanel PanelWort  = new JPanel(new BorderLayout());
		PanelWort.add(new JLabel("Wortanalyse:"),BorderLayout.NORTH);
		PanelWort.add(tblWortAnalyse,BorderLayout.CENTER);

		PanelTabellen.add(PanelWort,BorderLayout.NORTH);
		//this.add(tblWortAnalyse,BorderLayout.CENTER);
		
		JPanel PanelSatz  = new JPanel(new BorderLayout());
		PanelSatz.add(new JLabel("Satzanalyse:"),BorderLayout.NORTH);
		PanelSatz.add(tblSatzAnalyse,BorderLayout.CENTER);
		PanelTabellen.add(PanelSatz,BorderLayout.CENTER);

		JScrollPane P = new JScrollPane(PanelTabellen);
		P.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		P.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//P.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.add(P,BorderLayout.CENTER);
		
		//this.add(tblSatzAnalyse,BorderLayout.SOUTH);
		setSatzTable();
		setWortTable();

		
	}
	
	public void setSatzTable()
	{
		dtmSatzAnalyse = new DefaultTableModel();
		tblSatzAnalyse.setModel(dtmSatzAnalyse);
		
		tblSatzAnalyse.setEnabled(false);
		tblSatzAnalyse.setCellSelectionEnabled(false);
		tblSatzAnalyse.setRowSelectionAllowed(true);
		//tblSatzAnalyse.setColumnSelectionAllowed(true);
		tblSatzAnalyse.setBackground(Color.lightGray);
		tblSatzAnalyse.setFocusable(false);
		
		//zwei Spaten erstellen
		dtmSatzAnalyse.addColumn("1");
		dtmSatzAnalyse.addColumn("2");
		
		if( intAuswahl != 0 && cobRomanAuswahl.getItemCount() != 0) 
		{
			//Ausgewählter Roman
			Roman roman = (Roman) Application.romanVerwaltung.getRomanListe().get(intAuswahl-1 );
			//SatzanalyseResult des Ausgewählten Romans
			SatzanalyseResult satzRes = (SatzanalyseResult) roman.getSatzanalyseResult();
			
//			Vector zur Erstellung von Tabelleneinträgen
			Vector vec = new Vector();
				
			Iterator itKeys;
			Set keys;
			int kurzeSaetze = 0;
			int mittlereSaetze = 0; 
			int langeSaetze = 0;
			
			keys=roman.getSatzanalyseResult().getAnzahlSatzlaenge().keySet();
			itKeys = keys.iterator();
			while ( itKeys.hasNext() )
			{
				Object key =itKeys.next();
				int i = Integer.parseInt(key.toString());
				
				if(i < Application.UNTERESATZGRENZE)
				{
					kurzeSaetze +=Integer.parseInt(roman.getSatzanalyseResult().getAnzahlSatzlaenge().get(key).toString());
				}
				else if(i<Application.OBERESATZGRENZE+1)
				{
					mittlereSaetze += Integer.parseInt(roman.getSatzanalyseResult().getAnzahlSatzlaenge().get(key).toString());
				}
				else
				{
					langeSaetze += Integer.parseInt(roman.getSatzanalyseResult().getAnzahlSatzlaenge().get(key).toString());
				}
			}
//			kurze Sätze
			vec.add("kurz (<"+ Integer.toString(Application.UNTERESATZGRENZE) +")");
			vec.add(""+kurzeSaetze);
			dtmSatzAnalyse.addRow(vec);
			
//			mittlere Sätze
			vec = new Vector();
			vec.add("mittel");
			vec.add(""+mittlereSaetze);
			dtmSatzAnalyse.addRow(vec);
			
//			lange Sätze
			vec = new Vector();
			vec.add("lang(>"+ Integer.toString(Application.OBERESATZGRENZE) +")");
			vec.add(""+langeSaetze);
			dtmSatzAnalyse.addRow(vec);
			
//			leere Zeile
			vec = new Vector();
			dtmSatzAnalyse.addRow(vec);
			
			vec = new Vector();
			vec.add("durch. Satzlänge");
			vec.add(""+satzRes.getDurchschnittlicheSatzlaenge());
			dtmSatzAnalyse.addRow(vec);
			
			vec = new Vector();
			vec.add("Anz. Sätze");
			vec.add(""+satzRes.getAnzahlSaetze());
			dtmSatzAnalyse.addRow(vec);
			
			vec = new Vector();
			vec.add("Anz. Nebensätze");	
			vec.add(""+satzRes.getAnzahlNebensaetze());
			dtmSatzAnalyse.addRow(vec);
		}
		
		
	}
	
	public void setWortTable()
	{
		dtmWortAnalyse = new DefaultTableModel();
		tblWortAnalyse.setModel(dtmWortAnalyse);
		
		tblWortAnalyse.setEnabled(false);
		tblWortAnalyse.setCellSelectionEnabled(false);
		tblWortAnalyse.setRowSelectionAllowed(true);
		//tblSatzAnalyse.setColumnSelectionAllowed(true);
		tblWortAnalyse.setBackground(Color.lightGray);
		tblWortAnalyse.setFocusable(false);
		
		//zwei Spaten erstellen
		dtmWortAnalyse.addColumn("1");
		dtmWortAnalyse.addColumn("2");
		
		if (intAuswahl != 0 && cobRomanAuswahl.getItemCount() != 0) 
		{
			//Ausgewählter Roman
			Roman roman = (Roman) Application.romanVerwaltung.getRomanListe().get(intAuswahl-1);
			//WortanalyseResult des Ausgewählten Romans
			WortanalyseResult wortRes = (WortanalyseResult) roman.getWortanalyseResult();

//			Vector zur Erstellung von Tabelleneinträgen
			Vector vec = new Vector();
			
			Set woerterbuecher = wortRes.getWortartenHaeufigkeiten().keySet();
			Iterator it = woerterbuecher.iterator();
			Object wBuchName;
			
			while( it.hasNext())
			{
				wBuchName = it.next();
				vec = new Vector();
				
				vec.add(wBuchName);
				vec.add(wortRes.getWortartenHaeufigkeiten().get(wBuchName));
				dtmWortAnalyse.addRow(vec);
			}
		}
		
		
	}
	public void updateCombo()
	{	
		cobRomanAuswahl.removeAllItems();
		Vector tempRomanListe = Application.romanVerwaltung.getRomanListe();
		Roman tempRoman;
		cobRomanAuswahl.addItem(new String("keine Auswahl"));
		for (int i= 0 ; i < tempRomanListe.size(); i++)
		{
			tempRoman = (Roman) tempRomanListe.get(i);
			cobRomanAuswahl.addItem(tempRoman.getTitel());
		}
	}
	
	/*
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) 
	{
		//Nummer des ausgewählten Romans
		intAuswahl = cobRomanAuswahl.getSelectedIndex();
		setSatzTable();	
		setWortTable();
	}



}
