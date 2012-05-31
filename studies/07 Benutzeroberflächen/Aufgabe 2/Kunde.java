package bof.aufgabe2;

public class Kunde {
    String nummer;

    String name;

    String vorname;

    Adresse adresse;

    Kunde(String nr, String name, String vorname, Adresse adr) {
        this.nummer = nr;
        this.name = name;
        this.vorname = vorname;
        this.adresse = adr;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String toString() {
        return this.name + ", " + this.vorname;
    }
}
