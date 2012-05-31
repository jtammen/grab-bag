/*
 * 	Autor : Marco Fahr
 *  Date  : 20.12.2005
 *  Klasse DialogTableEditor -> Ermöglicht das editieren und speichern der Werte in der Tabelle
 * 
 */

package stilanalyse;

import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

/**
 * @author Marco Fahr
 *
 */
public class DialogTableCellListener implements CellEditorListener {
	
	
	private static final long serialVersionUID = 4703989225103230217L;
	private DefaultCellEditor editor;
	private Vector data;
	private JTable table = new JTable();
	
	
	/**
	 * @param editor
	 * @param table
	 */
	public DialogTableCellListener(DefaultCellEditor editor, JTable table) {
		this.editor = editor;
		this.table = table;
		data = new Vector();
	}
	
	public void editingStopped(ChangeEvent arg0) {
		table.getModel().setValueAt(editor.getCellEditorValue().toString(),table.getSelectedRow(),table.getSelectedColumn());
	}
	public void editingCanceled(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return data
	 */
	public Vector getRomanDetails() {
		return data;
	}
	

}	

	