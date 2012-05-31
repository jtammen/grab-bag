package decorator;
public class TextAnzeige extends VisuelleKomponente {
	@Override
	public void zeichne() {
		System.out.println("TextAnzeige.zeichne()");
	}
}