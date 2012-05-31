import java.util.*;
import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface Cart extends EJBObject
{
	void addBook(String titel) throws RemoteException;
	void removeBook(String titel) throws RemoteException, BookException;
	Vector getContents() throws RemoteException;
}