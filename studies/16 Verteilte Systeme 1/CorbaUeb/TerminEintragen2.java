import corbatermin.TerminFactoryHelper;
import corbatermin.KonkreterTermin;
import corbatermin.TerminFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;


public class TerminEintragen2 {
public TerminEintragen2() {
	super();
}

public static void main(String[] args) {
	try {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//Einlesen der Termine
		System.out.println("============ Termin erfassen ==========\n");
		System.out.println("Terminname: ");
		String terminname = in.readLine();
		System.out.print("Beschreibung: ");
		String beschreibung = in.readLine();
		System.out.print("Datum (tt.mm.jjjj): ");
		String datum = in.readLine();
		System.out.print("Uhrzeit (HH:MM): ");
		String uhrzeit = in.readLine();
		System.out.print("Dauer (HH:MM): ");
		String dauer = in.readLine();
		System.out.println("=======================================");

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

		// Ein neues Objekt: Konkreter Termin wird erzeugt
		// Die Mandantennummer wir dazu uebergeben.
		// Bitte Mandant 0 verwenden, damit der Termin auf dem
		// Servlet sichtbar ist.
		System.out.println("Creating Termin ......");
		corbatermin.KonkreterTermin termin = tfRef.createKonkreterTermin("0");
		
		//Datums und Zeitformate muessen folgendes Format erhalten.
		//Datum tt.mm.jjjj
		//Uhrzeit, Dauer hh:mm
		//Pruefung auf null da null eine Exception verursacht.
		if (terminname != null) termin.setTerminname(terminname);
		if (beschreibung != null) termin.setBeschreibung(beschreibung);
		if (datum != null) termin.setDatum(datum);
		if (uhrzeit != null) termin.setUhrzeit(uhrzeit);
		if (dauer != null) termin.setDauer(dauer);
		
		//Der Termin wird nun validiert und gespeichert
		termin.speichern();
		
		System.out.println("Der Termin wurde erfolgreich eingetragen");
	} catch (Exception e) {
		System.out.println("ERROR : " + e);
		e.printStackTrace(System.out);
	}
}
}
