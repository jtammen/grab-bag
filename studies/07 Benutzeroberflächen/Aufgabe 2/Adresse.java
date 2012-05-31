package bof.aufgabe2;

public class Adresse {
	String plz;
	String ort;
	String strasse;
	String nr;
	
	/**
     * @param nr
     * @param ort
     * @param plz
     * @param strasse
     */
    public Adresse(String nr, String ort, String plz, String strasse) {
        super();
        this.nr = nr;
        this.ort = ort;
        this.plz = plz;
        this.strasse = strasse;
    }
    public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
}
