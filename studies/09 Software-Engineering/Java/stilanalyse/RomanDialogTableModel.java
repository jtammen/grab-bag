package stilanalyse;

import javax.swing.table.DefaultTableModel;

/**
 * @author Marco Fahr
 *
 */
public class RomanDialogTableModel extends DefaultTableModel {
	
	
	private static final long serialVersionUID = 7900267968283003261L;

	/**
	 *  
	 */
	public RomanDialogTableModel() {
	}
	
	public boolean isCellEditable(int row, int col) {
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }

}
