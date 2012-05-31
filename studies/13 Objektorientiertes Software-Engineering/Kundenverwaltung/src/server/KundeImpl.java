package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KundeImpl extends UnicastRemoteObject implements KundeIF {
	private static final long serialVersionUID = 1L;

	private String name;

	private int kundennummer;

	private String adresse;

	public KundeImpl() throws RemoteException {
		name = "Unbekannt";
		kundennummer = 0;
		adresse = "Unbekannt";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see server.KundeIF#find(int)
	 */
	public void find(int kundennummer) throws RemoteException {
		System.out.println("Kunde.find()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see server.KundeIF#save()
	 */
	public void save() throws RemoteException {
		System.out.println("Kunde.save()");
		System.out.println(this.adresse);
		System.out.println(this.name);
		System.out.println(this.kundennummer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see server.KundeIF#getAdresse()
	 */
	public String getAdresse() throws RemoteException {
		return adresse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see server.KundeIF#setAdresse(java.lang.String)
	 */
	public void setAdresse(String adresse) throws RemoteException {
		this.adresse = adresse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see server.KundeIF#getKundennummer()
	 */
	public int getKundennummer() throws RemoteException {
		return kundennummer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see server.KundeIF#setKundennummer(int)
	 */
	public void setKundennummer(int kundennummer) throws RemoteException {
		this.kundennummer = kundennummer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see server.KundeIF#getName()
	 */
	public String getName() throws RemoteException {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see server.KundeIF#setName(java.lang.String)
	 */
	public void setName(String name) throws RemoteException {
		this.name = name;
	}
}