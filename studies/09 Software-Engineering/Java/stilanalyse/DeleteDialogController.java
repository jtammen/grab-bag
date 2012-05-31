/*
 * 	Autor : Marco Fahr
 *  Date  : 20.12.2005
 *  Klasse DeleteDialogController -> Controller des RomanDeleteDialogs
 * 
 */

package stilanalyse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;




/**
 * @author Marco Fahr
 *
 */
public class DeleteDialogController implements ActionListener {
	
	OverviewRomanTabModel model;
	DeleteDialogView view;
	
	/** Konstruktor der Controllerklasse für den RomanLöschen Dialog
	 * @param model
	 * @param view
	 */
	public DeleteDialogController(OverviewRomanTabModel model, DeleteDialogView view) {
		this.model = model;
		this.view = view;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand() == "String_Var_6") {
				if (model.getRowCount() > 0) {
					int row[] = view.getSelectedRows();
					//sebis
					//löschen mehrer Romane eingefügt 12.01.06
					//for-Schleife muss i-- sein da sonst fehler bei löschen und anzeigen entsteht!!
					for (int i = row.length-1 ; i >=0  ; i--)
					{
						String autor="";
						String titel="";
						autor = (String) model.getValueAt(row[i],1);
						titel = (String) model.getValueAt(row[i],0);
						try
						{
						Application.romanVerwaltung.loescheRoman(titel, autor);
						}
												
						catch (RomanNichtVorhandenException e) 
						{
							JOptionPane.showMessageDialog(new JPanel(),"Roman nicht vorhanden","Achtung",JOptionPane.WARNING_MESSAGE); 
						}
					}
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
						
					try {
						MainFrameView.wordTab.getTextArea().setText("");
						
						
				        Application.frame.getVergleichsTabView().updateAllCombo();
						Application.frame.getOverviewPanel().setRomanTable();
//					TODO knaegele: Aktualisierung nach Roman_loeschen 
//					FIXME Auskommentiert da NullPointerException geschmissen wird Marco 	
						//Application.frame.getSentenceAnalysisPanel().update();
					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
		}
		view.dispose();
	}

	}
