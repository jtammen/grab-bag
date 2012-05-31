import java.rmi.RemoteException; 
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class ConverterEJB implements SessionBean {

	public double DemToEuro(double dem) 
	{
		return dem / 1.95583;

	}

	public double EuroToDem(double euro)
	{
		return euro * 1.95583;
	}

   public ConverterEJB() {}
   public void ejbCreate() {}
   public void ejbRemove() {}
   public void ejbActivate() {}
   public void ejbPassivate() {}
   public void setSessionContext(SessionContext sc) {}

} // ConverterEJB
