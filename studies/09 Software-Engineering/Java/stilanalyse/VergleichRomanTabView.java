/************************************/
/*Erstellt von:	Sebastian Stenzel  	*/
/*Erstellt am:	19.12.2005			*/
/************************************/

package stilanalyse;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JPanel;

public class VergleichRomanTabView
extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Vector panelVector = new Vector();
	public VergleichRomanTabView()
	{
		this.setLayout(new GridLayout(2,5,10,10));
		for(int i = 0 ; i<10; i++)
		{
			panelVector.add(new VergleichRomanPanelView());
			this.add((VergleichRomanPanelView) panelVector.lastElement());
		}
		updateAllCombo();
		
	}
	public void updateAllCombo()
	{
		for(int i= 0 ; i<10; i++)
		{
			((VergleichRomanPanelView)panelVector.get(i)).updateCombo();
		}
	
	}
}
