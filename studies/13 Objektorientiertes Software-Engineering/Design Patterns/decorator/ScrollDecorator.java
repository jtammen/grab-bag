package decorator;
public class ScrollDecorator extends Decorator {
	public ScrollDecorator(VisuelleKomponente komponente) {
		super(komponente);
	}

	@Override
	public void zeichne() {
		System.out.println("ScrollDecorator.zeichne()");
		super.zeichne();
	}
}