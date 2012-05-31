package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KundeIF extends Remote {

	public abstract void find(int kundennummer) throws RemoteException;

	public abstract void save() throws RemoteException;

	public abstract String getAdresse() throws RemoteException;

	public abstract void setAdresse(String adresse) throws RemoteException;

	public abstract int getKundennummer() throws RemoteException;

	public abstract void setKundennummer(int kundennummer)
			throws RemoteException;

	public abstract String getName() throws RemoteException;

	public abstract void setName(String name) throws RemoteException;

}