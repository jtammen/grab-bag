package stilanalyse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Interface Woerterbuch.
 *
 * Die Klasse, welches dieses Interface implementiert muss
 * außerdem das Interface "Serializable" implementieren. 
 *
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  André Erb (superb@fh-konstanz.de)
 * @date    2006-01-06
 */
public interface Woerterbuch {
    /**
     * Holt das <code>wortartPool</code>-Attribut.
     * 
     * Datenstruktur: (Schlüssel => Wert)
     * Wortart-Name (<code>String</code>) => 
     * Wortart-Objekt (<code>stilanalyse.Wortart</code>)
     * 
     * @return	HashMap
     * @see     Wortart
     */
    HashMap getWortarten();
	
    /**
     * Liest den Inhalt der Datei ein und rekonstruiert daraus die Datenstruktur
     * des Wörterbuchs.
     * 
     * Falls die Datei (d) nicht vorhanden/lesbar ist, wird eine Exception
     * "FileNotFoundException" geworfen (bereits in Java vorhanden).
     * 
     * In der Klasse zu ergänzen, welche dieses Interface implementiert.
     * 
     * @param	d	der absolute Dateipfad zur Wörterbuchdatei
     * @throws	FileNotFoundException
     * @return 	Woerterbuch
     */
    //public static Woerterbuch initialisiere(String d) 
    //	throws FileNotFoundException;
    
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
    void deinitialisiere(String d)
    	throws FileNotFoundException, IOException;
    
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
    Wortzuordnung gibWort(String w) throws WortNichtVorhandenException;
    
    /**
     * Überprüft, ob das Wort im Wortpool vorhanden ist.
     * 
     * @param	w	das Wort
     * @return	boolean
     */
    boolean istWortVorhanden(String w);
    
    /**
     * Fügt eine neue Wortart ein.
     * 
     * Falls die Worart bereits existiert, wird eine Exception 
     * WortartBereitsVorhandenException geworfen.
     * 
     * @param	wa	der Name der neuen Wortart
     * @throws	WortartBereitsVorhandenException
     */
    void fuegeWortartEin(String wa) throws WortartBereitsVorhandenException;    
    
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
    void fuegeEin(String w, String wa) 
    	throws WortartNichtVorhandenException, WortBereitsZugeordnetException, LeeresWortException;
    
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
    void loesche(String w, String wa)
    	throws WortNichtVorhandenException, WortartNichtVorhandenException;
    
    /**
     * Löscht ein Wort aus <em>allen</em> Wortarten.
     *  
     * Falls das Wort (w) nicht existiert, wird eine 
     * Exception "WortNichtVorhandenException" geworfen 
     * 
     * @param	w	das zu löschende Wort in jeder Wortart
     * @see		WoerterbuchVerwaltung#loescheWort(String)
     */
    void loesche(String w) throws WortNichtVorhandenException;
    
    /**
     * Gibt die HashSet mit den zur Wortart (wa) gehörenden Wörtern 
     * (Objekte vom Typ <code>stilanalyse.Wort</code>) zurück.
     *
     * Falls die Wortart nicht existiert, wird eine
     * Exception "WortartNichtVorhandenException" geworfen.
     * 
     * @param	wa	der Name der Wortart
     * @throws	WortartNichtVorhandenException
     * @return	HashSet
     * @see     Wort
     */
    HashSet listeWoerterAuf(String wa)
    	throws WortartNichtVorhandenException;
}
