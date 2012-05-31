package client;

import java.awt.Button;
import java.awt.Color;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

public class Kundendarstellung extends Panel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private TextField kundennummerField;

	private TextField nameField;

	private TextField adresseField;

	private Button saveButton;

	private Vector<KundeSaveListener> saveListeners;

	public Kundendarstellung() {
		kundennummerField = new TextField();
		nameField = new TextField();
		adresseField = new TextField();

		saveButton = new Button("Speichern");
		saveButton.setActionCommand("SAVE");
		saveButton.addActionListener(this);

		add(kundennummerField);
		add(nameField);
		add(adresseField);
		add(saveButton);

		setSize(300, 300);
		setVisible(true);
		setBackground(Color.GRAY);

		saveListeners = new Vector<KundeSaveListener>();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("SAVE")) {
			System.out
					.println("Kundendarstellung.actionPerformed() - saveButton pressed");
			notifyListeners();
		}
	}

	private void notifyListeners() {
		// Hier ohne threadsafety...
		Iterator<KundeSaveListener> it = saveListeners.iterator();
		while (it.hasNext()) {
			KundeSaveListener l = (KundeSaveListener) it.next();
			l.savePerformed();
		}
	}

	public void addSaveListener(KundeSaveListener o) {
		saveListeners.add(o);
	}

	public void removeSaveListener(KundeSaveListener o) {
		saveListeners.remove(o);
	}

	public TextField getAdresseField() {
		return adresseField;
	}

	public TextField getKundennummerField() {
		return kundennummerField;
	}

	public TextField getNameField() {
		return nameField;
	}
}