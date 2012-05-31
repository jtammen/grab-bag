import corbatermin._CallbackClientImplBase;

public class CallbackClientServant extends _CallbackClientImplBase {

	public CallbackClientServant() {
		super();
	}

	// Ein Servant der auf dem Client instanziert wird.
	// Er druckt die Message die vom CorbaServer beim Einfügen eines
	// Termins gesendet wird.

	// Um den Servant zu registrieren, muss in der MainMethode
	// der Klasse CallbackClient die Methode register aufgerufen werden.
	public void beobachter(String theMessage) {
		System.out.println("Nachricht vom CORBA Server: " + theMessage);
	}
}