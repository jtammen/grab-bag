
import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface Converter extends EJBObject {
 
   public double DemToEuro(double dem) throws RemoteException;
   public double EuroToDem(double euro) throws RemoteException;
}
