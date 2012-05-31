/*
 * 	Autor : Marco Fahr
 *  Date  : 20.12.2005
 *  Klasse MainFrameController -> View des RomanEditierenDialogs
 * 
 */

package stilanalyse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Vector;


import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableColumn;

/**
 * @author Marco Fahr
 *
 */
public class RomanDialogView extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel labelL;
	private JLabel labelR;
	private JPanel southPanel;
	private JButton button;
	private RomanDialogController controller;
	private RomanDialogTableModel model;		
	private DefaultCellEditor editor;
	private DialogTableCellListener listener;
	private Vector rows;

	/**
	 * @param view
	 * @param title
	 */
	public RomanDialogView(MainFrameView view, String title)
	{
		super(view, title, true);
		controller = new RomanDialogController(this);
		model = new RomanDialogTableModel();
		rows = new Vector();
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
		model.addColumn("Dateipfad");
		model.addColumn("Titel");
		model.addColumn("Autor");
		model.addColumn("erschienen");
		model.addColumn("verkauft");
		
		TableColumn column = null;
		table = new JTable(model);
		table.setAutoCreateColumnsFromModel(true);
		table.setRowSelectionAllowed(false);
		JTextField field = new JTextField();
		
		for (int i = 0; i < MainFrameView.size; i++) {
			rows = new Vector();
			rows.addElement(MainFrameView.files[i]);
			model.addRow(rows);
		}
		for (int i = 0; i < 5; i++) {
			column = table.getColumnModel().getColumn(i);
		    if (i == 0) {
		        column.setPreferredWidth(300);   
		    } 
		    else {
		    	editor = new DefaultCellEditor(field);
		    	listener = new DialogTableCellListener(editor,table);
		    	editor.addCellEditorListener(listener);
		   		column.setCellEditor(editor);
		    	column.setPreferredWidth(50);
		    	field.setBackground(Color.red); 
		    }
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
		button = new JButton("Übernehmen");
		button.setActionCommand("String_Var_4");
		button.addActionListener(controller);
		return button;
	}
	
	/**
	 * @return model
	 */
	public RomanDialogTableModel getTableModel() {
		return model;
	}
	
	/**
	 * @return table
	 */
	public JTable getTable() {
		return table;
	}
	
	/**
	 * @return editor
	 */
	public DefaultCellEditor getEditor() {
		return editor;
	}
}