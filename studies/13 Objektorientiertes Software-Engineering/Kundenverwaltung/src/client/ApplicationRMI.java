package client;

import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import server.KundeFactoryIF;

public class ApplicationRMI {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Kundenverwaltung mainView = new Kundenverwaltung();
		Kundendarstellung view = new Kundendarstellung();
		mainView.add(view, BorderLayout.WEST);
		mainView.pack();

		KundeFactoryIF kundeFactory;
		try {
			kundeFactory = (KundeFactoryIF) Naming
					.lookup("rmi://localhost/KundeFactory");
			GFKundeEintragenRMI connector = new GFKundeEintragenRMI(view,
					kundeFactory.createKunde());
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}