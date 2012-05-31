package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KundeFactoryIF extends Remote {
	public KundeIF createKunde(int kundennummer) throws RemoteException;

	public KundeIF createKunde() throws RemoteException;
}