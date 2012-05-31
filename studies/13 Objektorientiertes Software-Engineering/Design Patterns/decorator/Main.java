package decorator;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VisuelleKomponente k1 = new ScrollDecorator(new RahmenDecorator(
				new TextAnzeige(), 1));
		k1.zeichne();
	}
}