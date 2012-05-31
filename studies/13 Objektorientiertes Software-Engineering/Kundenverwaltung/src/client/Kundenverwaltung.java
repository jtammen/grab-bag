package client;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Kundenverwaltung extends Frame {
	private static final long serialVersionUID = 1L;

	public Kundenverwaltung() {
		super();

		setSize(800, 600);
		setTitle("Kundenverwaltung");
		setVisible(true);

		// Window-Listener registrieren
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
				System.exit(0);
			}
		});
	}
}
