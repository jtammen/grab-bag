package stilanalyse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Marco Fahr
 *
 */
public class DeleteDialogListener implements MouseListener {

	private DeleteDialogView view;
	
	/** Konstruktor der Listener Klasse für den Romanlöschen Dialog
	 * @param view
	 */
	public DeleteDialogListener(DeleteDialogView view) {
		this.view = view;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		
		view.setIndex(view.getRomanTable().getSelectedRow());
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
