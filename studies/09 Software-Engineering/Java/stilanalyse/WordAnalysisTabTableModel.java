package stilanalyse;

import javax.swing.table.DefaultTableModel;

/**
 * @author Marco Fahr
 *
 */
public class WordAnalysisTabTableModel extends DefaultTableModel {
	
	
	private static final long serialVersionUID = -265752972545875374L;

	public boolean isCellEditable(int row, int col) {
	        return false;
	    }


}
