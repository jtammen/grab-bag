package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class DynamicRMIKundeServer {

	public static void main(String[] args) {
		try {
			KundeFactoryImpl factory = new KundeFactoryImpl();
			Naming.rebind("rmi://localhost/KundeFactory", factory);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}