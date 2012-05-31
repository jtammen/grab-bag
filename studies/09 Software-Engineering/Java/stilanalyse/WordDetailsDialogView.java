/*
 * Autor : Marco Fahr
 * Date  : 12.12.2005
 * Klasse für die Darstellung der Worthäufigkeiten
 *  
 */

package stilanalyse;

import java.awt.BorderLayout;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class WordDetailsDialogView extends JDialog {

		
		private static final long serialVersionUID = 3772842201740954475L;

		private JPanel panel;
		private JPanel rightPanel;
		private JPanel leftPanel;
		private JScrollPane leftScrollPane;
		private JScrollPane rightScrollPane;
		private JTable leftTable;
		private JTable rightTable;
		private DetailsTableModel rightModel;
		private DetailsTableModel leftModel;

		
		public WordDetailsDialogView(String title) {
			super(Application.frame, title, true);
			rightModel= new DetailsTableModel();
			leftModel = new DetailsTableModel();
			setSize(1000,400);
			setLayout(new BorderLayout());
			add(setPanel(), BorderLayout.CENTER);
			setResizable(false);
			setLocationRelativeTo(Application.frame);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setVisible(true);
		}
		
		
		private JPanel setPanel() {
			panel = new JPanel();
			panel.setLayout(new BorderLayout());
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
			panel.add(setRightPanel(), BorderLayout.EAST);
			panel.add(setLeftPanel(), BorderLayout.WEST);
			return panel;
		}
		
		private JPanel setRightPanel() {
			rightPanel = new JPanel();
			rightPanel.setLayout(new BorderLayout());
			rightPanel.add(setRightScrollPane(),BorderLayout.CENTER);
			
			return rightPanel;
		}
		
		private JPanel setLeftPanel() {
			leftPanel = new JPanel();
			leftPanel.setLayout(new BorderLayout());
			leftPanel.add(setLeftScrollPane(), BorderLayout.CENTER);
			return leftPanel;
		}
		
		private JTable setRightTable() {
			rightTable = new JTable(rightModel);
			rightModel.addColumn("Wörter");
			rightModel.addColumn("Häufigkeit");	
			return rightTable;
		}
		
		
		
		private JTable setLeftTable() {
			leftTable = new JTable(leftModel);
			Vector romane = Application.romanVerwaltung.getRomanListe();
			Roman roman = (Roman) romane.elementAt(Application.index);	
			long anzahl = roman.getWortanalyseResult().getAnzahlWoerter();
			Long anzahlObjekt = new Long(anzahl);
			Vector gesamt = new Vector();
			gesamt.addElement("Gesamt");
			gesamt.addElement(anzahlObjekt);			
			leftModel.addColumn("Wörterbücher");
			leftModel.addColumn("Worte im Roman");
			leftModel.addRow(gesamt);
			
			leftTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

				public void valueChanged(ListSelectionEvent arg0) {
					if(!arg0.getValueIsAdjusting()) {
						int index = getLeftTable().getSelectedRow();
			//			System.out.println(getLeftTable().getValueAt(index,0));
						if (getLeftTable().getValueAt(index,0) == "Gesamt")
						{
							updateAllRightTable();
						}
						else {
							System.out.println("Muss noch implementiert werden");
							updateTypTable();
						}		
				}
				
			}});
			
			Iterator itKeys;
			Set keys;
			keys=((Roman)romane.get(Application.index)).getWortanalyseResult().getWortartenHaeufigkeiten().keySet();
			
				
			itKeys = keys.iterator();
			while (itKeys.hasNext()) {
				Vector row = new Vector();
				Object key = itKeys.next();
				Object value = ((Roman)romane.get(Application.index)).getWortanalyseResult().getWortartenHaeufigkeiten().get(key);
				row.addElement(key);
				row.addElement(value);
				leftModel.addRow(row);
			}
		return leftTable;
		}
		
		
		private void updateAllRightTable() {
			rightModel.getDataVector().removeAllElements();
			Vector romane = Application.romanVerwaltung.getRomanListe();			
			Iterator itKeys;
			Set keys;
			keys=((Roman)romane.get(Application.index)).getWortanalyseResult().getWortHaeufigkeiten().keySet();	
			itKeys = keys.iterator();
			
			
			while (itKeys.hasNext()) {
				if(true){
					
					Vector row = new Vector();
					Object key = itKeys.next();
					Object value = ((Roman)romane.get(Application.index)).getWortanalyseResult().getWortHaeufigkeiten().get(key);
					row.addElement(key);
					row.addElement(value);
					rightModel.addRow(row);
				}
				
			}
		}
		
		private void updateTypTable() {
			int index = getLeftTable().getSelectedRow();
			rightModel.getDataVector().removeAllElements();
			Roman roman = (Roman) Application.romanVerwaltung.getRomanListe().elementAt(index);
			Collection v = roman.getWortanalyseResult().getWortZuordnungen().values();
			Object[] t = v.toArray();
			Wortzuordnung s = new WortzuordnungImpl();	
			roman.getWortanalyseResult().getWortZuordnungen().containsKey(getLeftTable().getValueAt(index,0));
			for (int i = 0; i < v.size(); i++) {
				s = (Wortzuordnung) t[i];
				for (int j = 0; j < s.getWortarten().size(); j++) {
					String match;
					match = (String) getLeftTable().getValueAt(index,0);
					for (int k = 0; k < s.getWortarten().size(); k++) {
						if(match == s.getWortarten().elementAt(k)) {
							System.out.println(s.getWort());
						}
					}
					
//					System.out.println(s.getWortArten().elementAt(j));
//					System.out.println(y);
//					System.out.println(match);
					
					
					
					
				}
				
			}
			
		}
		
		
		private JScrollPane setLeftScrollPane() {
			leftScrollPane = new JScrollPane(setLeftTable());
			leftScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			leftScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			leftScrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
			return leftScrollPane;
			
		}
		
		
		private JScrollPane setRightScrollPane() {
			rightScrollPane = new JScrollPane(setRightTable());
			rightScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			rightScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			rightScrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
			return rightScrollPane;
		}
		
		private JTable getLeftTable() {
			return leftTable;
		}
		
}
	
	
	
	

