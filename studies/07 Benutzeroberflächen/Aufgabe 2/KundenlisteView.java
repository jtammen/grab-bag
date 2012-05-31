/**
 * 
 */
package bof.aufgabe2;

import java.awt.Graphics;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;

/**
 * @author jt
 * 
 */
@SuppressWarnings("serial")
public class KundenlisteView extends Panel implements DesPattObserver {
    
    private Kundenliste model;
    private List anzeigeliste;
    
    KundenlisteView(Kundenliste m) {
        this.model = m;
        this.model.attach(this);
        this.anzeigeliste = new List();
        // anonyme Klasse new ItemListener
        this.anzeigeliste.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                // Dem Model mitteilen, dass wir einen neuen
                // Eintrag ausgewählt haben.
                model.setAktuellePosition(anzeigeliste.getSelectedIndex());
            }
        });
        this.add(this.anzeigeliste);
        this.setVisible(true);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see gmvc.DesPattObserver#update()
     */
    public void update() {
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        Iterator kundenit = this.model.getKunden();
        this.anzeigeliste.removeAll();
        while (kundenit.hasNext()) {
            Kunde kunde = (Kunde)kundenit.next();
            this.anzeigeliste.add(kunde.toString());
        }
        this.anzeigeliste.setVisible(true);
        
        if (this.anzeigeliste.getItemCount() > 0) {
            this.anzeigeliste.select(this.model.getAktuellePosition());
        }
    }
}
