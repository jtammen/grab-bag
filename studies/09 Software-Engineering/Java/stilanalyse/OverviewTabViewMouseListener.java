/*
 *  Autor : Marco Fahr
 * Date  : 23.01.2005
 * Klasse OverViweTabViewMouseListener
 *  
 */

package stilanalyse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



/**
 * @author Marco Fahr
 *
 */
public class OverviewTabViewMouseListener implements MouseListener {

	static int x  = 0;
	private OverviewTabView view ;
	private WordAnalysisTab tabView;
	
	/** Konstruktor des Listeners für den Übersichtspanel
	 * @param view
	 * @param tabView
	 */
	public OverviewTabViewMouseListener(OverviewTabView view, WordAnalysisTab tabView) {
		this.view = view;
		this.tabView = tabView;
	}
	
	public void mouseClicked(MouseEvent arg0) {
	
	}

	public void mousePressed(MouseEvent arg0) {
		
	}

	/**
	 * 	Beim anwählen eines Romans in der Übersicht wird die WordAnalyseTab bei bestehenden Tabellen von
	 * 	einem vorher ausgewählten Roman gelöscht und mit dem aktuell gewählten aktuallisiert.
	 * 	Der Index in der Application wird auf den Aktuellen Roman gesetzt.
	 * 	Die beiden Analyseübersichten in der Übersicht werden auf den aktuellen Roman aktuallisiert
	 */
	
	public void mouseReleased(MouseEvent arg0) {
		try {
			
			if(MainFrameView.wordTab.getRightTableModel().getRowCount() > 0) {
				int rightgroesse = MainFrameView.wordTab.getRightTableModel().getRowCount();
				for (int i = 0; i < rightgroesse; i++) {
					MainFrameView.wordTab.getRightTableModel().removeRow(0);
				}
			}
			int leftgroesse = MainFrameView.wordTab.getWordLengthTable().getModel().getRowCount();
				for (int i = 0; i < leftgroesse; i++) {
				MainFrameView.wordTab.getLeftTableModel().removeRow(0);
				}	
			view.updateSatzAnalyseTable();
			view.updateWortAnalyseTable();
			Application.index = view.getRomanTable().getSelectedRow();
			tabView.getRightTableModel().getDataVector().removeAllElements();
			tabView.updateWordTab();
//			TODO knaegele: Aktualisierung nach Fokuswechsel auf anderen Roman 
			Application.frame.getSentenceAnalysisPanel().update();
			
			}
			catch(Exception e_) {
			
			}
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

}
