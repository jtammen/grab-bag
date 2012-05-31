package stilanalyse;

import java.util.Vector;
import javax.swing.table.*;

import stilanalyse.Application;

public class OverviewRomanTabModel 
extends AbstractTableModel
{
	
	private static final long serialVersionUID = -1925982365136250260L;
	private int size;
	private Vector data;
	private Vector Tabellenueberschriften = new Vector();
	

	//	Konstruktor
	public OverviewRomanTabModel(int size)
	{
		Tabellenueberschriften.add("Titel");
		Tabellenueberschriften.add("Autor");
		Tabellenueberschriften.add("erschienen");
		Tabellenueberschriften.add("Verkauft");
		this.size = size;
		this.data = Application.romanVerwaltung.getRomanListe();
	}


	public int getRowCount() 
	{
		return size;
	}

	public int getColumnCount() 
	{
		return Tabellenueberschriften.size();
	}

	public String getColumnName(int columnIndex)
	{
		return (String)Tabellenueberschriften.elementAt(columnIndex);
	}

	
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		String ret;

		Roman roman = (Roman)data.get(rowIndex);

		switch (columnIndex)
		{
			case 0: 
				ret = roman.getTitel();
				break;
			case 1: 
				ret = roman.getAutor();
				break;
			case 2: 
				//ret = roman.getTitel();
				ret = roman.getDatum();
				break;
			case 3: 
				ret = "" + roman.getAnzahlVerkauft();
				break;
			default:
				ret = "-";
		}
		return ret;
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		
	}

	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return false;
	}
}
