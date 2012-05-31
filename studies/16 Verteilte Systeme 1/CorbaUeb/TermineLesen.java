import corbatermin.TerminFactoryHelper;
import corbatermin.Tag;
import corbatermin.KonkreterTermin;
import corbatermin.TerminFactory;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;


public class TermineLesen {
public TermineLesen() {
	super();
}

public static void main(String[] args) {
	try {
		// create and initialize the ORB
		ORB orb = ORB.init(args, null);

		// get the root naming context
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContext ncRef = NamingContextHelper.narrow(objRef);
		System.out.println("NameService resoved");
	
		// Die Referenz des Servant TerminFactory wird bezogen.
		NameComponent nc = new NameComponent("TerminFactory", "");
		NameComponent path[] = {nc};
		TerminFactory tfRef = TerminFactoryHelper.narrow(ncRef.resolve(path));
		System.out.println("TerminFactory resolved");
		
		//Die Referenz auf das Objekt Tag beziehen, welches ein Array der
		//Objekte vom Typ KonkreterTermin enthält.
		Tag tag = tfRef.findTag("21.11.2006", "0");
		
		//Array auslesen
		KonkreterTermin[] aKonkreteTermine = tag.getTermine(); 

		//Schleife durchlaeuft alle Elemente des Arrays und druckt sie auf die Bildschirmausgabe
		for (int i = 0; i < aKonkreteTermine.length; i++) {
			KonkreterTermin aTermin = aKonkreteTermine[i];
			System.out.println("=================================");
			System.out.println("Terminname: " + aTermin.getTerminname());
			System.out.println("Datum: " + aTermin.getDatum());
			System.out.println("Uhrzeit: " + aTermin.getUhrzeit());
			System.out.println("Dauer: " + aTermin.getDauer());
			System.out.println("=================================");
		}
		System.out.println("Die Termine wurden ausgegeben.");
	} catch (Exception e) {
		System.out.println("ERROR : " + e);
		e.printStackTrace(System.out);
		System.out.println("Datumsformat ueberpruefen");
	}
}
}
