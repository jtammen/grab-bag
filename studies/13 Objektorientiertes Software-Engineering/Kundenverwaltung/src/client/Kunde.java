package client;

public class Kunde {
	private String name;

	private int kundennummer;

	private String adresse;

	public Kunde() {
		name = "Unbekannt";
		kundennummer = 0;
		adresse = "Unbekannt";
	}

	public void find(int kundennummer) {
		System.out.println("Kunde.find()");
	}

	public void save() {
		System.out.println("Kunde.save()");
		System.out.println(this.adresse);
		System.out.println(this.name);
		System.out.println(this.kundennummer);
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getKundennummer() {
		return kundennummer;
	}

	public void setKundennummer(int kundennummer) {
		this.kundennummer = kundennummer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
