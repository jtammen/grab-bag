/*
 * Woerterbuch.java
 *
 * Datum: 13. Dezember 2005
 *
 * Autoren: Jens Pfeifer, Ralf Lehmann, Matthias Larisch, Bekim Qela
 */

package stilanalyse;

import stilanalyse.Wortzuordnung;
import stilanalyse.Wortart;
import stilanalyse.Wort;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Vector;
import java.io.Serializable;

public class WoerterbuchImpl implements Woerterbuch, Serializable {
    private HashMap wortPool;
    private HashMap wortartPool;

    /** Creates a new instance of Woerterbuch */
    public WoerterbuchImpl() {
        wortPool=new HashMap();
        wortartPool=new HashMap();
        wortartPool.put("Sonstige", new WortartImpl("Sonstige"));
    }
    
    /**
     * Holt das <code>wortartPool</code>-Attribut.
     * 
     * @return	HashMap
     */
    public HashMap getWortarten(){
        return wortartPool;
    }
    
    /**
     * Schreibt die Datenstruktur des Wörterbuchs in die Datei zurück.
     * 
     * <ul>
     * 	<li>Falls die Datei (d) nicht vorhanden ist, wird eine Exception
     * 		"FileNotFoundException" geworfen (bereits in Java vorhanden).</li>
     *  <li>Falls die Datei (d) nicht schreibbar ist, wird eine Exception
     * 		"IOException" geworfen (bereits in Java vorhanden).</li>
     * </ul>
     * 
     * @param	d	der absolute Dateipfad zur Wörterbuchdatei
     * @throws	FileNotFoundException
     * @throws	IOException
     */
    public void deinitialisiere(String d) throws
            FileNotFoundException, IOException {
        FileOutputStream file=new FileOutputStream(d);
        ObjectOutputStream os = new ObjectOutputStream(file);
        os.writeObject(this);
        os.close();
    }
    
    /**
     * Sucht das Wort in der Datenstruktur <code>wortPool</code>.
     * 
     * Falls das Wort (w) nicht existiert, wird eine Exception
     * "WortNichtVorhandenException" geworfen.
     * Andernfalls wird ein Wortzuordnungs-Objekt zurückgegeben, in 
     * dem das Wort (als String) sowie ein Vector von Strings mit 
     * den Namen der zum Wort gehörenden Wortarten enthalten sind.
     * 
     * @param 	w	das zu suchende Wort
     * @throws	WortNichtVorhandenException
     * @return	Wortzuordnung
     * @see		stilanalyse.Wortzuordnung
     */
    public Wortzuordnung gibWort(String w) throws
     WortNichtVorhandenException {
        Wort ow=(Wort)wortPool.get(w);
        if(ow==null) throw new WortNichtVorhandenException();
        Wortzuordnung wz=new WortzuordnungImpl();
        wz.setWort(w);
        Vector v=new Vector();
        Wortart wa=ow.gibNaechsteWortart();
        while(wa!=null)
        {
            v.add(wa.getName());
            wa=ow.gibNaechsteWortart();
        }
        wz.setWortarten(v);
        return wz;
    }
  
    /**
     * Überprüft, ob das Wort im Wortpool vorhanden ist.
     * 
     * @param	w	das Wort
     * @return	boolean
     */
    public boolean istWortVorhanden(String w){
        return wortPool.containsKey(w);
    }
    
    /**
     * Fügt eine neue Wortart ein.
     * 
     * Falls die Worart bereits existiert, wird eine Exception 
     * WortartBereitsVorhandenException geworfen.
     * 
     * @param	wa	der Name der neuen Wortart
     * @throws	WortartBereitsVorhandenException
     */
    public void fuegeWortartEin(String wa) throws
     WortartBereitsVorhandenException {
        if(wa != "")
        {
            if(!wortartPool.containsKey(wa)) wortartPool.put(wa, new WortartImpl(wa));
            else throw new WortartBereitsVorhandenException();
        }
    }
    
    /**
     * Fügt ein (neues) Wort in eine (neue) Wortart ein, bzw. 
     * fügt eine Beziehung eines Wortes zu einer Wortart hinzu.  
     * 
     * <ul>
     *   <li>Falls das Wort (w) in der Wortart (wa) schon vorhanden ist,
     *       darf es nicht erneut eingefügt werden, es wird eine Exception
     *       WortBereitsZugeordnetException geworfen.</li> 
     *   <li>Falls die Wortart noch nicht existiert, muss sie
     *       zuerst angelegt werden, es wird eine Exception 
     *       WortartNichtVorhandenException geworfen.</li>
     *   <li>Falls ein leeres, also ungültiges Wort übergeben
     *       wird, wir eine Exception LeeresWortException geworfen.</li>
     * </ul>
     * 
     * @param	w	das einzufügende Wort
     * @param	wa	der Name der zum Wort gehörenden Wortart
     * @throws	WortartNichtVorhandenException
     * @throws	WortBereitsZugeordnetException
     * @throws  LeeresWortException
     * @see		WoerterbuchVerwaltung#nimmWortAuf(String, String)
     * @see		WoerterbuchVerwaltung#weiseWortZu(String, String)
     */
    public void fuegeEin(String w, String wa) throws
     LeeresWortException, WortartNichtVorhandenException,
     WortBereitsZugeordnetException {
        if(w == "") throw new LeeresWortException();
        Wortart owa=(Wortart) wortartPool.get(wa);
        if(owa==null) throw new WortartNichtVorhandenException();
        Wort ow=(Wort) wortPool.get(w);
        if(ow==null) ow=new WortImpl(w);
        wortPool.put(w, ow);
        owa.fuegeWortEin(ow);
        try{
            ow.fuegeWortartEin(owa);
        }catch (WortartBereitsZugeordnetExpcetion ex) {}

        // Wörter wurden immer aus Sonstige ausgetragen,
        // auch wenn wa == "Sonstige"
        if(wa!="Sonstige") {
        	owa = (Wortart) wortartPool.get("Sonstige");
        	try{
        		ow.loescheWortart(owa);
        	}catch (WortartNichtZugeordnetException ex){}
        	try {
        		owa.loescheWort(ow);
        	}catch (WortNichtZugeordnetException ex) {}
        }							
    }
    
    /**
     * Löscht ein Wort aus <em>einer</em> Wortart.
     * 
     * <ul>
     *   <li>Falls das Wort (w) in der Wortart (wa) nicht existiert, wird eine
     *       Exception "WortNichtVorhandenException" geworfen.</li>
     *   <li>Falls die Wortart nicht existiert, wird eine
     *       Exception "WortartNichtVorhandenException" geworfen.</li>
     *   <li>Falls das Wort nach dem Löschen keiner weiteren Wortart zugeordnet
     *       ist, wird es automatisch in die Wortart "Sonstige" eingefügt.</li>
     * </ul>
     * 
     * @param	w	das zu löschende Wort
     * @param	wa	der Name der zum Wort gehörenden Wortart
     * @throws	WortNichtVorhandenException
     * @throws	WortartNichtVorhandenException
     * @see		WoerterbuchVerwaltung#loescheWort(String, String)
     */
    public void loesche(String w, String wa) throws
     WortartNichtVorhandenException, WortNichtVorhandenException {
        Wort ow=(Wort) wortPool.get(w);
        Wortart owa=(Wortart) wortartPool.get(wa);
        if(ow==null) throw new WortNichtVorhandenException();
        if(owa==null) throw new WortartNichtVorhandenException();
        try{
            ow.loescheWortart(owa);
        }catch (WortartNichtZugeordnetException ex) {} 
        try{
            owa.loescheWort(ow);
        }catch (WortNichtZugeordnetException ex) {}
        if(ow.gibNaechsteWortart() == null)
        {
            owa=(Wortart) wortartPool.get("Sonstige");
            try{
                owa.fuegeWortEin(ow);
            }catch (WortBereitsZugeordnetException ex) {}
            try{
                ow.fuegeWortartEin(owa);
            }catch (WortartBereitsZugeordnetExpcetion ex) {}
        }
    }
    
    /**
     * Löscht ein Wort aus <em>allen</em> Wortarten.
     *  
     * Falls das Wort (w) nicht existiert, wird eine 
     * Exception "WortNichtVorhandenException" geworfen 
     * 
     * @param	w	das zu löschende Wort in jeder Wortart
     * @see		WoerterbuchVerwaltung#loescheWort(String)
     */
    public void loesche(String w) throws
     WortNichtVorhandenException {
        Wort ow=(Wort) wortPool.get(w);
        if(ow==null) throw new WortNichtVorhandenException();
        Wortart owa=ow.gibNaechsteWortart();
        while(owa!=null)
        {
            try{
                owa.loescheWort(ow);
            }catch (WortNichtZugeordnetException ex) {}
            try{
                ow.loescheWortart(owa);
            }catch (WortartNichtZugeordnetException ex) {}

            owa=ow.gibNaechsteWortart();
        }
        wortPool.remove(ow.getWort());
    }
    
    /**
     * Gibt die HashSet mit den zur Wortart (wa) gehörenden Wörtern zurück.
     *
     * Falls die Wortart nicht existiert, wird eine
     * Exception "WortartNichtVorhandenException" geworfen.
     * 
     * @param	wa	der Name der Wortart
     * @throws	WortartNichtVorhandenException
     * @return	HashSet
     */
    public HashSet listeWoerterAuf(String wa) throws
     WortartNichtVorhandenException {
        Wortart owa=(Wortart) wortartPool.get(wa);
        if(owa==null) throw new WortartNichtVorhandenException();
        Wort w=owa.gibNaechstesWort();
        HashSet woerter=new HashSet();
        while(w!=null)
        {
            woerter.add(w);
            w=owa.gibNaechstesWort();
        }
        return woerter;
    }
}
