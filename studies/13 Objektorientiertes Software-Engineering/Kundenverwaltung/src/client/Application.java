package client;

import java.awt.BorderLayout;

public class Application {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Kundenverwaltung mainView = new Kundenverwaltung();
		Kundendarstellung view = new Kundendarstellung();
		mainView.add(view, BorderLayout.WEST);
		mainView.pack();
		GFKundeEintragen connector = new GFKundeEintragen(view, new Kunde());
	}
}