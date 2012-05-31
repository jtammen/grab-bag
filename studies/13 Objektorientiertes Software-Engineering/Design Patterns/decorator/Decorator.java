package decorator;
public abstract class Decorator extends VisuelleKomponente {
	private VisuelleKomponente komponente;

	public Decorator(VisuelleKomponente komponente) {
		this.komponente = komponente;
	}

	@Override
	public void zeichne() {
		System.out.println("Decorator.zeichne()");
		komponente.zeichne();
	}
}