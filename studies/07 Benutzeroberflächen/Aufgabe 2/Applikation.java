package bof.aufgabe2;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Applikation extends Frame {
    public Applikation() throws HeadlessException {
        super();
        // Window-Listener registrieren
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                System.exit(0);
            }
        });
        
        Kundenliste model = new Kundenliste();
        model.addKunde(new Kunde("1", "Tammen", "Jan", new Adresse("1",
                "Testort", "65432", "Testweg 1")));
        model.addKunde(new Kunde("1", "Eck", "Christoph", new Adresse("2",
                "Testort", "12345", "Testweg 2")));
        KundenlisteView view_liste = new KundenlisteView(model);
        KundeView view_kunde = new KundeView(model);
        this.setTitle("Kundenverwaltung");
        this.add(view_liste,BorderLayout.WEST);
        this.add(view_kunde);
        //this.pack();
        this.setSize(400,220);
        this.setVisible(true);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Applikation();
    }
}