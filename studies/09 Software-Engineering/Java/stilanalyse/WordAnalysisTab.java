/*
 * Autor : Marco Fahr
 * Date  : 12.12.2005
 * Klasse Worttab
 *  
 */


package stilanalyse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 * @author Marco Fahr
 *
 */
public class WordAnalysisTab extends JSplitPane{
	
	private static final long serialVersionUID = -7382918065314497458L;
	/**
	 * 	TextArea zum anzeigen des Romantextes
	 */
	private JTextArea romanTextArea;
	private JPanel wordAnalysePanel;
	private JScrollPane leftWordScrollPane;
	private JScrollPane rightWordScrollPane;
	private JScrollPane romanTextsSrollPane;
	private WordAnalysisMouseListener listener = new WordAnalysisMouseListener(this);
	private JTable wordLengthTable;
	private JTable rightTable;
	private WordAnalysisTabTableModel linkeTabelle;
	private WordAnalysisTabTableModel rechteTabelle;

	/**
	 *  Konstruktor des Tabs für die Satzanalyse
	 */
	public WordAnalysisTab() {
		super();
		setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		setDividerLocation(334);
		setOrientation(JSplitPane.VERTICAL_SPLIT);
		setLeftComponent(getRomanTextScrollPane());
		setRightComponent(getWordAnalysePanel());
	}
	
	private JScrollPane getRomanTextScrollPane() {
		if (romanTextsSrollPane == null) {
			romanTextsSrollPane = new JScrollPane();
			romanTextsSrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			romanTextsSrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			romanTextsSrollPane.setBackground(Color.blue);
			romanTextsSrollPane.setViewportView(getRomanTextArea());
		}
		return romanTextsSrollPane;
	}
	
	private JTextArea getRomanTextArea() {
		if (romanTextArea == null) {
			romanTextArea = new JTextArea();
			romanTextArea.setLineWrap(true);
			romanTextArea.setWrapStyleWord(true);
			romanTextArea.setBorder(new BevelBorder(BevelBorder.LOWERED));
			Roman roman = new RomanImpl();
				if(Application.index >= 0) {
					roman = (Roman)Application.romanVerwaltung.getRomanListe().elementAt(Application.index); // Bugfix Marco
					romanTextArea.setText(roman.getText());
				}
			romanTextArea.setEditable(false);	
		}
		return romanTextArea;
	}
	
	private JPanel getWordAnalysePanel() {
		if (wordAnalysePanel == null) {
			wordAnalysePanel = new JPanel();
			JLabel labelo = new JLabel(" "); 
			wordAnalysePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			wordAnalysePanel.setLayout(new BorderLayout());
			wordAnalysePanel.add(labelo,BorderLayout.NORTH);
			wordAnalysePanel.add(getRightPanel(), BorderLayout.EAST);
			wordAnalysePanel.add(getLeftPanel(), BorderLayout.WEST);
		}
		return wordAnalysePanel;
	}
	
	private JScrollPane getLeftWordScrollPane() {
		if (leftWordScrollPane == null) {
			leftWordScrollPane = new JScrollPane();
			leftWordScrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			leftWordScrollPane.setViewportView(setLeftTable());
		}
		return leftWordScrollPane;
	}
	
	private JScrollPane getRightWordScrollPane() {
		if (rightWordScrollPane == null) {
			rightWordScrollPane = new JScrollPane();
			rightWordScrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			rightWordScrollPane.setViewportView(createRightWordTable());
		}
		return rightWordScrollPane;
	}
	
	/** Initiert die linke Tablelle 
	 * @return wordlengthTable
	 */
	public JTable setLeftTable() {
		linkeTabelle = new WordAnalysisTabTableModel();
		wordLengthTable = new JTable(linkeTabelle);
		wordLengthTable.addMouseListener(listener);
		wordLengthTable.setSelectionMode(0);
		wordLengthTable.setFocusable(false);
		linkeTabelle.addColumn("Wörterbücher");
		linkeTabelle.addColumn("Worte im Roman");
		return wordLengthTable;
	}

	/**
	 * 	Aktuallisiert das Textfeld und die beiden Tabellen
	 */
	public void updateWordTab() {
		Roman roman = (Roman)Application.romanVerwaltung.getRomanListe().elementAt(Application.index);
		Vector row1 = new Vector();																								
		romanTextArea.setText(roman.getText());
		long value = roman.getWortanalyseResult().getAnzahlWoerter();
		Long v = new Long(value);
		if(Application.index > -1) {
			row1.addElement("Gesamt");
			linkeTabelle.addRow(row1);
			linkeTabelle.setValueAt(v,0,1);	
			}
			Iterator itKeys1;
			Set keys1;
			keys1 = roman.getWortanalyseResult().getWortartenHaeufigkeiten().keySet();
			itKeys1 = keys1.iterator();
			while (itKeys1.hasNext()) {
				Vector row2 = new Vector();
				Object key1 = itKeys1.next();
				Object keyValue1 = roman.getWortanalyseResult().getWortartenHaeufigkeiten().get(key1);
				row2.addElement(key1);
				row2.addElement(keyValue1);
				linkeTabelle.addRow(row2);
				}
			}
			
	private JTable createRightWordTable() {
		if (rightTable == null) {
			rechteTabelle = new WordAnalysisTabTableModel();
			rechteTabelle.addColumn("Wörter");
			rechteTabelle.addColumn("Anzahl");
			rightTable = new JTable(rechteTabelle);
			rightTable.setFocusable(false);
			rightTable.setEnabled(false);
		}	
		return rightTable;
	}
	
	
	private JPanel getLeftPanel() {
		JPanel panel = new JPanel();
		JLabel labelo = new JLabel(" "); 
		JLabel labelu = new JLabel(" ");
		JLabel labelr = new JLabel("          "); 
		JLabel labell = new JLabel("          ");
		panel.setLayout(new BorderLayout());
		panel.add(labelo, BorderLayout.NORTH);
		panel.add(labelu, BorderLayout.SOUTH);
		panel.add(labelr, BorderLayout.EAST);
		panel.add(labell, BorderLayout.WEST);
		panel.add(getLeftWordScrollPane(),BorderLayout.CENTER);
		
		
		return panel;
	}
	
	private JPanel getRightPanel() {
		JPanel panel = new JPanel();
		JLabel labelo = new JLabel(" "); 
		JLabel labelu = new JLabel(" ");
		JLabel labelr = new JLabel("          "); 
		JLabel labell = new JLabel("          ");
		panel.setLayout(new BorderLayout());
		panel.add(labelo, BorderLayout.NORTH);
		panel.add(labelu, BorderLayout.SOUTH);
		panel.add(labelr, BorderLayout.EAST);
		panel.add(labell, BorderLayout.WEST);
		panel.add(getRightWordScrollPane(),BorderLayout.CENTER);
		
		
		return panel;
	}
	
	/** Liefert das Textfeld
	 * @return romanTextArea
	 */
	public JTextArea getTextArea() {
		return romanTextArea;
	}
	
	/** Liefert das Modell der linken Tabelle
	 * @return linkeTabelle
	 */
	public WordAnalysisTabTableModel getLeftTableModel() {
		return linkeTabelle;
	}
	
	/** Liefert das Modell der rechten Tabelle
	 * @return rechteTabelle
	 */
	public WordAnalysisTabTableModel getRightTableModel() {
		return rechteTabelle;
	}
	
	/** Liefert die linke JTable
	 * @return wordLengthTable
	 */
	public JTable getWordLengthTable() {
		return wordLengthTable;
	}
	
	
	

}
