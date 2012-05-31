package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class KundeFactoryImpl extends UnicastRemoteObject implements
		KundeFactoryIF {

	protected KundeFactoryImpl() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	public KundeIF createKunde(int kundennummer) throws RemoteException {
		KundeImpl k = new KundeImpl();
		k.find(kundennummer);
		return k;
	}

	public KundeIF createKunde() throws RemoteException {
		return new KundeImpl();
	}
}