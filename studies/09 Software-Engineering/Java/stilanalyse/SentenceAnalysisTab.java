package stilanalyse;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class SentenceAnalysisTab extends JSplitPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4148634200472256538L;
	private Roman roman;
	private JScrollPane sentenceLengthScrollPane;
	private JPanel sentenceAnalysePanel;
	

	public SentenceAnalysisTab() {

		super();
		setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		setDividerLocation(334);
		setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		if(Application.getRomanIndex() >= 0){
			roman = (Roman) Application.romanVerwaltung.getRomanListe().elementAt(Application.getRomanIndex());
			setLeftComponent(getSentenceLengthScrollPane());
			setRightComponent(getSentenceAnalysePanel());
		}
		else
		{
			sentenceLengthScrollPane = new JScrollPane();
			setLeftComponent(getSentenceLengthScrollPane());
			sentenceAnalysePanel = new JPanel();
			setRightComponent(getSentenceAnalysePanel());
		}
	}

	
	public void update(){
		
		if(Application.getRomanIndex() != -1) {
			remove(sentenceLengthScrollPane);
			remove(sentenceAnalysePanel);
			roman = (Roman) Application.romanVerwaltung.getRomanListe().elementAt(Application.getRomanIndex());
			setLeftComponent(getSentenceLengthScrollPane());
			setRightComponent(getSentenceAnalysePanel());
//			this.repaint();
		}
			
		
	}
	
	
	private JScrollPane getSentenceLengthScrollPane() {

		if(Application.getRomanIndex() >= 0){
			return new JScrollPane(getSentenceTable());
		}
		else
		{
			return sentenceLengthScrollPane;
		}	

	}
	
	
	private JPanel getSentenceAnalysePanel() {
		if(Application.getRomanIndex() >= 0){
			sentenceAnalysePanel = new JPanel();
			sentenceAnalysePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
			sentenceAnalysePanel.setLayout(new BorderLayout());
			sentenceAnalysePanel.add(getNebensatzScrollPane(),BorderLayout.NORTH);
			sentenceAnalysePanel.add(getDurchschnittScrollPane(),BorderLayout.SOUTH);
			return sentenceAnalysePanel;
		}
		else
		{
			return sentenceAnalysePanel;
		}		

	}
	
	private JTable getSentenceTable() {
		TableModel dataModel = new AbstractTableModel() {
			
			public  String getColumnName( int columnIndex ){
				String[] title = {"Satzlänge", "Häufigkeit"};				
				return title[columnIndex];
			}
			
			public boolean isCellEditable( int rowIndex, int columnIndex )
			{
				  return false;
			}
			public int getColumnCount() {
				return 2;
			}
		
			public int getRowCount() {
				return getAnzahlSatzlaengeVector().size();
			}
		
			public Object getValueAt(int row, int col) {
				if (col == 0) {
					return new Integer(row);
				} else // if (col == 1)
				{
					Integer x= (Integer) getAnzahlSatzlaengeVector().get(row);
					if(x==null) return null;
					else return x;
						
				}
			}
		};
		
		JTable sentenceTable = new JTable(dataModel);
		
		return sentenceTable;

	}
	
	private JScrollPane getNebensatzScrollPane() {
		return new JScrollPane(getNebensatzPanel());
	}
	
	private JPanel getNebensatzPanel() {
		JPanel nebensatzPanel = new JPanel();
		nebensatzPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		nebensatzPanel.setLayout(new BorderLayout());
		JLabel nebensatz = new JLabel("Nebensätze: ".concat(new Integer(roman.getSatzanalyseResult().getAnzahlNebensaetze()).toString()));
		nebensatzPanel.add(nebensatz, BorderLayout.NORTH);
		return nebensatzPanel;
	}
	
	private JScrollPane getDurchschnittScrollPane() {
		return new JScrollPane(getDurchschnittPanel());
	}
	
	private JTable getDurchschnittPanel() {

		DefaultTableModel model = new DefaultTableModel(new String[][]{{getMinimaleSatzlaenge().toString(),getDurchschnittlicheSatzlaenge(),getMaximaleSatzlaenge().toString()}},new String[]{"kürzester Satz","durchschnittliche Satzlänge","längster Satz"});
		JTable durchschnittTable = new JTable(model);
		durchschnittTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		durchschnittTable.setLayout(new BorderLayout());
		return durchschnittTable;
	}
	
	private Integer getMinimaleSatzlaenge() {
		return getKeys()[0];
	}
	
	private String getDurchschnittlicheSatzlaenge() {
		Double avg= new Double(roman.getSatzanalyseResult().getDurchschnittlicheSatzlaenge());
		return avg.toString();
		
	}
	
	private Integer getMaximaleSatzlaenge() {
		return getKeys()[getKeys().length - 1];
	}

	private Integer[] getKeys() {
		Integer keys[] = (Integer[]) getAnzahlSatzLaenge().keySet().toArray(new Integer[getAnzahlSatzLaenge().size()]);
		Arrays.sort(keys);

		return keys;
	}
	
	private HashMap getAnzahlSatzLaenge() {
		return roman.getSatzanalyseResult().getAnzahlSatzlaenge();
	}

	private Vector getAnzahlSatzlaengeVector() {
		Vector anzahlSatzLaengenVector = new Vector();
		for (int i = 0; i <= getMaximaleSatzlaenge().intValue(); i++) {
			if (getAnzahlSatzLaenge().containsKey(new Integer(i))) {
				anzahlSatzLaengenVector.add(i, getAnzahlSatzLaenge().get(
						new Integer(i)));
			}
				else {
			anzahlSatzLaengenVector.add(i, new Integer(0));
			}
		}
		return anzahlSatzLaengenVector;
	}













}
