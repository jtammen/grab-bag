/*
 * 	Autor : Marco Fahr
 *  Date  : 20.12.2005
 *  Klasse RomanDialogController -> Controller des RomanEditierenDialogs
 * 
 */

package stilanalyse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import stilanalyse.Application;

/**
 * @author Marco Fahr
 *
 */
public class RomanDialogController implements ActionListener {
	
	private RomanDialogView view;

	/** Konstruktor der Controllerklasse für den RomanEditierenDialog
	 * @param view
	 */
	public RomanDialogController(RomanDialogView view) {
		this.view = view;
	}
	/**
	 * @return dialog
	 */
	public JDialog createOptionPane() 
	{ 
		JDialog dialog = new JDialog();
		JOptionPane.showMessageDialog(dialog,"Falsches Eingabeformat! Bitte in der Form:" +
				" Text, Text, 12.12.2000, 1234567 eingeben!","Vorsicht",JOptionPane.WARNING_MESSAGE);
		dialog.setLocationRelativeTo(view);
		return dialog;	
	}
	
	/**
	 * @return dialog
	 */
	public JDialog createNullOptionPane() {
		JDialog dialog = new JDialog();
		JOptionPane.showMessageDialog(dialog,"Keine Eingabe gemacht","Vorsicht",JOptionPane.WARNING_MESSAGE);
		dialog.setLocationRelativeTo(view);
		return dialog;	
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand() == "String_Var_4") {
			try {
				if(view.getTable().getSelectedColumn() == 4)
				{
					view.getEditor().stopCellEditing();		 // Bugfix wenn man gleich Übernehmen-Button drückt
				}
				String titel;	
				String autor;
				String datum;
				String anzahlVerkauft;
				String dateiPfad;
				
				for (int i = 0; i < view.getTableModel().getRowCount(); i++) {
					dateiPfad = view.getTableModel().getValueAt(i,0).toString();
					titel = view.getTableModel().getValueAt(i,1).toString();
					autor = view.getTableModel().getValueAt(i,2).toString();
					datum = view.getTableModel().getValueAt(i,3).toString();
					anzahlVerkauft = view.getTableModel().getValueAt(i,4).toString();
					//Sebis
					//Exceptionhandler eingefügt
					try
					{
						Application.romanVerwaltung.fuegeRomanEin(titel, autor, datum, anzahlVerkauft, dateiPfad);
					}
					catch (RomanBereitsVorhandenException e)
					{
						JOptionPane.showMessageDialog( new JDialog(),"Romanname bereits Vorhanden","Achtung",JOptionPane.WARNING_MESSAGE);	
					}

                    // TODO: Beim Integrieren eingefügt, damit auch Analyse gestartet wird
                    // 2006-01-19, 18:00
                    try
                    {
                        Application.analyse.starteAnalyse(Application.woerterVerwaltung.getWoerterbuch(), 
                        Application.romanVerwaltung.getRoman(titel, autor) );
                    }
                    catch (RomanNichtVorhandenException e)
                    {
                        JOptionPane.showMessageDialog( new JDialog(),"Romanname nicht vorhanden","Achtung",JOptionPane.WARNING_MESSAGE);  
                    }

                    // FIXME: wirft NullPointerException! 
					//aufruf zur aktuallisierung der combobox im Romanvergleich
                    Application.frame.getVergleichsTabView().updateAllCombo();
					Application.frame.getOverviewPanel().setRomanTable();
					Application.frame.getSentenceAnalysisPanel().update();
				}		
				view.dispose();
			} catch (ClassCastException e) {
				createOptionPane();
			}catch (NumberFormatException e) {
				createOptionPane();
			}catch (NullPointerException e) {
				createNullOptionPane();
			}
			
		}

	}


}
