package bof.aufgabe2;

import java.util.Iterator;
import java.util.Vector;

/**
 * @file    Kundenliste.java
 * @author  Christoph Eck
 * @author  Jan Tammen
 */
public class Kundenliste extends DesPattSubject {
    private Vector<Kunde> kundenliste;
    private int aktuellePosition;
   
    Kundenliste() {
        this.kundenliste = new Vector<Kunde>();
        this.aktuellePosition = 0;
    }
    
    public void addKunde(Kunde o) {
        this.kundenliste.add(o);
        this.despattNotify();
    }
    
    public void removeKunde(Kunde o) {
        this.kundenliste.remove(o);

        if (this.aktuellePosition > this.kundenliste.size()-1)
            this.aktuellePosition--;
        
        if (this.kundenliste.isEmpty()) 
            this.aktuellePosition = -1;
        
        this.despattNotify();
    }
    
    public Kunde getKunde(int i) {
        if (i < 0) return null;
        return this.kundenliste.get(i);
    }
    
    public int getAktuellePosition() {
        return this.aktuellePosition;
    }
    
    public void setKunde(Kunde o, int i) {
        this.kundenliste.setElementAt(o, i);
        this.despattNotify();
    }
    
    public Iterator getKunden() {
        return this.kundenliste.iterator();
    }

    public void setAktuellePosition(int selectedIndex) {
        this.aktuellePosition = selectedIndex;
        this.despattNotify();
    }

    public Kunde getAktuellenKunden() {
        return this.getKunde(this.getAktuellePosition());
    }
}
