package composite.example;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ordner o1 = new Ordner();
		Datei f1 = new Datei(23);
		Datei f2 = new Datei(42);

		o1.add(f1);
		o1.add(f2);

		Ordner o2 = new Ordner();
		Datei f3 = new Datei(124);

		o2.add(f3);
		o1.add(o2);
		
		System.out.println(o1.getGröße());
	}
}