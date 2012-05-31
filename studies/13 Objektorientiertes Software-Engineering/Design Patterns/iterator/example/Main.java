package iterator.example;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DepotverwaltungV2 dp = new DepotverwaltungV2CachedSuche();
		dp.einf�genImpl(new Element("foo"));
		dp.einf�genImpl(new Element("bar"));
		dp.einf�genImpl(new Element("baz"));

		DepotIterator iter = dp.createIterator();
		while (!iter.isDone()) {
			Element element = (Element) iter.next();
			System.out.println(element.getName());
		}
	}
}