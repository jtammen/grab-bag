package decorator;
public class RahmenDecorator extends Decorator {
	private int rahmenbreite;

	public RahmenDecorator(VisuelleKomponente komponente, int rahmenbreite) {
		super(komponente);
		this.rahmenbreite = rahmenbreite;
	}

	@Override
	public void zeichne() {
		System.out.println("RahmenDecorator.zeichne()");
		super.zeichne();
		zeichneRahmen();
	}

	private void zeichneRahmen() {
		System.out.println("RahmenDecorator.zeichneRahmen()");
		System.out.println(rahmenbreite);
	}
}