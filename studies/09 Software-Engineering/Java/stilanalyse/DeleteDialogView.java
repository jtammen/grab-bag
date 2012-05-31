/*
 * 	Autor : Marco Fahr
 *  Date  : 12.12.2005
 *  Klasse DeleteDialogView -> View des RomanLöschenDialogs
 * 
 */

package stilanalyse;


import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

/**
 * @author Marco Fahr
 *
 */
public class DeleteDialogView extends JDialog{

	
	private static final long serialVersionUID = 3772842201740954475L;

	private JLabel label;
	private JPanel panel;
	private JScrollPane scrollPane;
	/**
	 * 
	 */
	private JTable table;
	private JLabel labelL;
	private JLabel labelR;
	private JPanel southPanel;
	private JButton button;
	private DeleteDialogController controller;	
	private OverviewRomanTabModel model;
	private DeleteDialogListener listener;
	/**
	 *  Die aktuell gewählte Tabellenreihe
	 */
	public int index;
	/**
	 * @param view
	 * @param title
	 */
	public DeleteDialogView(MainFrameView view, String title) {
		super(view, title, true);
		model = new OverviewRomanTabModel(Application.romanVerwaltung.getRomanListe().size());
		controller = new DeleteDialogController(model, this);
		table = new JTable();
		listener = new DeleteDialogListener(this);
		setSize(900,200);
		setLayout(new BorderLayout());
		add(setPanel(), BorderLayout.CENTER);
		setResizable(false);
		setLocationRelativeTo(view);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	
	private JPanel setPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel.add(setLabel(), BorderLayout.NORTH);
		panel.add(setScrollPane(), BorderLayout.CENTER);
		panel.add(setLeftLabel(), BorderLayout.WEST);
		panel.add(setRightLabel(), BorderLayout.EAST);
		panel.add(getSouthPanel(), BorderLayout.SOUTH);
		return panel;
	}
	
	private JLabel setLabel() {
		label = new JLabel("          ");
		return label;
	}
	
	private JLabel setLeftLabel() {
		labelL = new JLabel("          ");
		return labelL;
	}
	
	private JLabel setRightLabel() {
		labelR = new JLabel("          ");
		return labelR;
	}
	
	
	private JTable setTable() {
		if(model.getRowCount() < 1){
			table = new JTable();
		}
		else {
		table = new JTable(model);
		table.addMouseListener(listener);
		}
		return table;
	}
	
	private JScrollPane setScrollPane() {
		scrollPane = new JScrollPane(setTable());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
		return scrollPane;
		
	}
	
	private JPanel getSouthPanel() {
		southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		southPanel.add(new JLabel("                   "), BorderLayout.NORTH);
		southPanel.add(new JLabel("                                          "), BorderLayout.EAST);
		southPanel.add(new JLabel("                   "), BorderLayout.SOUTH);
		southPanel.add(new JLabel("                                          "), BorderLayout.WEST);
		southPanel.add(getSaveButton(), BorderLayout.CENTER);
		return southPanel;
	}
	
	private JButton getSaveButton() {
		button = new JButton("Löschen");
		button.addActionListener(controller);
		button.setActionCommand("String_Var_6");
		return button;
	}
	

	public void setIndex(int index) {
		this.index = index;
	}


	public JTable getRomanTable() {
		return table;
	}
	
	/**
	 * @return Feld von gewählten Tabelleneinträgen
	 */
	public int[] getSelectedRows(){
			return table.getSelectedRows();
	}
	
}