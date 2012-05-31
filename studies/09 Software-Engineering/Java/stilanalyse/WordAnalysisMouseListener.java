/*
 *  Autor : Marco Fahr
 * Date  : 23.01.2005
 * Klasse OverViweTabViewMouseListener
 *  
 */

package stilanalyse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 * @author Marco Fahr
 *
 */
public class WordAnalysisMouseListener implements MouseListener {

	private WordAnalysisTab view;
	private int index;
	
	/** Konstruktor des Listeners für den WordAnalyseTab
	 * @param view
	 * @param tabView
	 */
	public WordAnalysisMouseListener(WordAnalysisTab view) {
		this.view = view;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * 	Reagiert auf das selektieren der linkenTabelle und aktuallisiert die rechte Tabelle 
	 * 	mit dem gerade gewählten Wortart
	 */
	
	public void mousePressed(MouseEvent arg0) {
		index = MainFrameView.wordTab.getWordLengthTable().getSelectedRow();
		Application.woerterVerwaltung.getWortarten();	
		Roman roman = (Roman) Application.romanVerwaltung.getRomanListe().elementAt(Application.index);
		if (MainFrameView.wordTab.getWordLengthTable().getValueAt(index,0) == "Gesamt") {
			view.getRightTableModel().getDataVector().removeAllElements();
			Iterator itKeys2;
			Set keys2;
			keys2 = roman.getWortanalyseResult().getWortHaeufigkeiten().keySet();
			itKeys2 = keys2.iterator();
			while (itKeys2.hasNext()) {
				Vector row3 = new Vector();
				Object key2 = itKeys2.next();
				Object keyValue2 = roman.getWortanalyseResult().getWortHaeufigkeiten().get(key2);
				row3.addElement(key2);
				row3.addElement(keyValue2);
				view.getRightTableModel().addRow(row3);
				}	
			}
			else {
				view.getRightTableModel().getDataVector().removeAllElements();
				Vector fass  = new Vector();
					
					Iterator itKeys3;
					Set keys3;
					keys3 = roman.getWortanalyseResult().getWortartenHaeufigkeiten().keySet();
					itKeys3 = keys3.iterator();		
					while (itKeys3.hasNext()) {
						Object key3 = itKeys3.next();
						String skey3 = (String)key3;
						String sValue3 = (String) view.getLeftTableModel().getValueAt(index,0);
						if(sValue3 == skey3) {
							Iterator becks;
							Set jever;
							jever = roman.getWortanalyseResult().getWortZuordnungen().keySet();
							becks = jever.iterator();
							while (becks.hasNext()) {
								Object heinecken = becks.next();
								Object fürstenberg = roman.getWortanalyseResult().getWortZuordnungen().get(heinecken);
								fass = (Vector) fürstenberg;
								if(heinecken == sValue3) {
									for (int i = 0; i < fass.size(); i++) {
										String tmp = (String) fass.elementAt(i);
											Vector row = new Vector();
											row.addElement(tmp);
											row.addElement(roman.getWortanalyseResult().getWortHaeufigkeiten().get(tmp));
											view.getRightTableModel().addRow(row);
									}
								}
								
							}	
					}
				}	
			}		
}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
