import corbatermin.TerminFactoryHelper;
import corbatermin.TerminFactory;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class CallbackClient {
	public static void main(String[] args) {
		try {
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get the root naming context
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContext ncRef = NamingContextHelper.narrow(objRef);
			System.out.println("NameService resoved");

			// Der Servant welcher auf dem Client ausgeführt wird, wird nun
			// instanziert und mit dem ORB verbunden.
			CallbackClientServant ccs = new CallbackClientServant();
			orb.connect(ccs);
			
			// Die Referenz des Servant TerminFactory wird bezogen.
			NameComponent nc = new NameComponent("TerminFactory", "");
			NameComponent path[] = {nc};
			TerminFactory tfRef = TerminFactoryHelper.narrow(ncRef.resolve(path));
			System.out.println("TerminFactory resolved");

			// Die Referenz des instanzierten Servants wird an den CORBA Servesr
			// weitergeleitet und dort mit der Methode register auf dem
			// TerminFactoryServant in einem Vector gespeichert. Falls ein
			// Termin eingetragen oder geaendert wird, wird der local
			// instanzierte Servant veranlasst, eine Message auf
			// dem Bildschirm auszugeben.
			tfRef.register(ccs);
			System.out
					.println("Der Client wartet nun auf eine Nachricht vom CORBA Server");

			// wait for invocations from clients
			java.lang.Object sync = new java.lang.Object();
			synchronized (sync) {
				sync.wait();
			}
		} catch (Exception e) {
			System.out.println("ERROR : " + e);
			e.printStackTrace(System.out);
		}
	}
}
