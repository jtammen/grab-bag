/*
 * 	Autor : Marco Fahr
 *  Date  : 12.12.2005
 *  Klasse MainFrameController -> Controllerklasse des Anwendungsfensters(MainFrame)
 * 
 */

package stilanalyse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import stilanalyse.WordbookEditor;

/**
 * @author Marco Fahr
 *
 */
public class MainFrameController implements ActionListener {

	private MainFrameView frame;
	
	/**
	 * @param frame
	 */
	public MainFrameController(MainFrameView frame)
	{
		this.frame = frame;
	}	
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand() == "String_Var_1") {
			frame.createFileChooser();
		}
		else if(arg0.getActionCommand() == "String_Var_2") {	
			frame.createDeleteDialog();
		}
		else if(arg0.getActionCommand() == "String_Var_3") 
		{
			//WordbookEditor editor = new WordbookEditor(frame,"Wörterbucheditor");
			
			WordbookEditor editor = new WordbookEditor(frame, "Wörtbucheditor");
			//editor.setSize(600,400);
			editor.setResizable(false);
			editor.setAlwaysOnTop(true);
			editor.setModal(true);
			editor.setLocation((frame.getX() + frame.getWidth()/2) - editor.getWidth()/2, (frame.getY() + frame.getHeight()/2) - editor.getHeight()/2);
			editor.setVisible(true);	
		}
		else if (arg0.getActionCommand() == "String_Var_6") {
			
		}
	}
}
