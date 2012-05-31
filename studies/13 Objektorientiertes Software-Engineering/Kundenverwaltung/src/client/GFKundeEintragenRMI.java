package client;

import java.rmi.RemoteException;

import server.KundeIF;

public class GFKundeEintragenRMI implements KundeSaveListener {

	Kundendarstellung view;

	KundeIF kunde;

	public GFKundeEintragenRMI(Kundendarstellung view, KundeIF kunde) {
		view.addSaveListener(this);
		this.view = view;
		this.kunde = kunde;
	}

	public void savePerformed() {
		System.out.println("GFKundeEintragen.savePerformed()");

		String adresse = view.getAdresseField().getText();
		if (!adresse.equals("")) {
			try {
				kunde.setAdresse(adresse);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		String kundennummer = view.getKundennummerField().getText();
		if (!kundennummer.equals("")) {
			try {
				kunde.setKundennummer(Integer.parseInt(kundennummer));
			} catch (NumberFormatException e) {
				// do nothing
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String name = view.getNameField().getText();
		if (!name.equals("")) {
			try {
				kunde.setName(name);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			kunde.save();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}