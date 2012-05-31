package stilanalyse;

import javax.swing.table.DefaultTableModel;
//import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
//Alexander Züfle,
//Stefan Rehm



public class MVCTableModel extends DefaultTableModel{
	
	private static final long serialVersionUID = 8150590264347055263L;
	private HashMap map=Application.woerterVerwaltung.getWortarten();

	
	
	      public Class getColumnClass(int c)
	      {
	        return getValueAt(0, c).getClass();
	      }

	      public boolean isCellEditable(int row, int col)
	      {
	    	  if (col>0)		// Spalte 0 nicht editierbar (Wort), andere editierbar!
	          return true;
	    	  else return false;  
	      }

	      public void showWortArt(String woaStr)
	      {
	    	  this.columnIdentifiers=null;
	    	  this.dataVector=null;
	    	  // Tabelle komplett löschen
		Vector <Object> Zeile=new Vector <Object> ();
		Zeile.add("Wort");
		Set s= map.keySet();
		Iterator itblabalba = s.iterator();
		while (itblabalba.hasNext()) Zeile.add(itblabalba.next().toString());
		this.setColumnIdentifiers(Zeile);
		Wortart woa = (Wortart) map.get(woaStr);
		Wort tempwort;
		this.fireTableDataChanged();
		String wortStr;
		
		while ((tempwort=woa.gibNaechstesWort())!=null) 
		{
			Vector <Object> v=new Vector <Object> ();
			wortStr=tempwort.getWort();
			
			
			v.add(wortStr);
			for (int i=1; i<this.getColumnCount(); i++)
			{
				if (((String) Zeile.get(i)==woaStr)) v.add(Boolean.TRUE);
				else {
					Wort vergleichsWort;
					Wortart andereWortart = (Wortart) map.get(Zeile.get(i));
					// in andereWortart steht jetzt das Wörterbuch zur aktuellen Spalte
					Boolean istEnthalten = Boolean.FALSE;
					
					while ((vergleichsWort=andereWortart.gibNaechstesWort())!=null)
					{
						if (vergleichsWort.getWort().equals(wortStr)) istEnthalten=Boolean.TRUE;
			
					}
					v.add(istEnthalten);
					}
			
			}
			this.addRow(v);
			
		}
	      }

	      public void setValueAt(Object val, int row, int col)
	      {
		if ((Boolean) val)
                                        {

                                                try
                                                {
                                                        Application.woerterVerwaltung.nimmWortAuf((String)this.getValueAt(row,0), (String) this.getColumnName(col));
                                                }
                                                catch (WortartNichtVorhandenException eWNV)
                                                {
                                                        System.out.println("Wortart nicht vorhanden!");
                                                }
                                                catch (LeeresWortException eLW)
                                                {
                                                        System.out.println("Leeres Wort!");
                                                }
                                                catch (WortBereitsZugeordnetException eWBZ)
                                                {
                                                        System.out.println("Wort ist bereits der Wortart zugeordnet!");
                                                }
                                                
                                        }
               if (!(Boolean) val) 
                                        {
                                                try
                                                {
                                                        Application.woerterVerwaltung.loescheWort((String)this.getValueAt(row,0), (String) this.getColumnName(col));
                                                }
                                                catch (WortartNichtVorhandenException eWANVl)
                                                {
                                                        System.out.println("Wortart nicht vorhanden!");
                                                }
                                                catch ( WortNichtVorhandenException eWNVl)
                                                {
                                                        System.out.println("Wort nicht vorhanden!");
                                                }
                                        }
	       Vector rowvec=(Vector) this.dataVector.get(row);		// hier umständlich, weil Daten vom DefaultTableModel in Vektoren von Vektoren
	       				// nächstes Mal besser gleich AbstractTableModel nehmen!
	       // rowvec=(Vector) this.dataVector.get(row+1);
	       
	       rowvec.setElementAt(val, col);
	       this.fireTableCellUpdated(row, col);
	       if ((Boolean) val & (! this.getColumnName(col).equals("Sonstige"))) rowvec.setElementAt(Boolean.FALSE, this.findColumn("Sonstige")) ;   					// wenn andere Wortklasse als "Sonstige" markiert, Haken bei "Sonstige" löschen!
	       this.fireTableCellUpdated(row, this.findColumn("Sonstige"));
	       
	       
	      }
	      

    };
