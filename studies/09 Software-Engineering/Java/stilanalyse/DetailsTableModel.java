package stilanalyse;

import javax.swing.table.DefaultTableModel;

public class DetailsTableModel extends DefaultTableModel {
	
	private static final long serialVersionUID = 88287016186984017L;

	public boolean isCellEditable(int row, int col) {
        return false;
    }


}
