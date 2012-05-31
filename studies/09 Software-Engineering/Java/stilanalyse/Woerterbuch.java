package stilanalyse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Interface Woerterbuch.
 *
 * Die Klasse, welches dieses Interface implementiert muss
 * au�erdem das Interface "Serializable" implementieren. 
 *
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  Andr� Erb (superb@fh-konstanz.de)
 * @date    2006-01-06
 */
public interface Woerterbuch {
    /**
     * Holt das <code>wortartPool</code>-Attribut.
     * 
     * Datenstruktur: (Schl�ssel => Wert)
     * Wortart-Name (<code>String</code>) => 
     * Wortart-Objekt (<code>stilanalyse.Wortart</code>)
     * 
     * @return	HashMap
     * @see     Wortart
     */
    HashMap getWortarten();
	
    /**
     * Liest den Inhalt der Datei ein und rekonstruiert daraus die Datenstruktur
     * des W�rterbuchs.
     * 
     * Falls die Datei (d) nicht vorhanden/lesbar ist, wird eine Exception
     * "FileNotFoundException" geworfen (bereits in Java vorhanden).
     * 
     * In der Klasse zu erg�nzen, welche dieses Interface implementiert.
     * 
     * @param	d	der absolute Dateipfad zur W�rterbuchdatei
     * @throws	FileNotFoundException
     * @return 	Woerterbuch
     */
    //public static Woerterbuch initialisiere(String d) 
    //	throws FileNotFoundException;
    
    /**
     * Schreibt die Datenstruktur des W�rterbuchs in die Datei zur�ck.
     * 
     * <ul>
     * 	<li>Falls die Datei (d) nicht vorhanden ist, wird eine Exception
     * 		"FileNotFoundException" geworfen (bereits in Java vorhanden).</li>
     *  <li>Falls die Datei (d) nicht schreibbar ist, wird eine Exception
     * 		"IOException" geworfen (bereits in Java vorhanden).</li>
     * </ul>
     * 
     * @param	d	der absolute Dateipfad zur W�rterbuchdatei
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
     * Andernfalls wird ein Wortzuordnungs-Objekt zur�ckgegeben, in 
     * dem das Wort (als String) sowie ein Vector von Strings mit 
     * den Namen der zum Wort geh�renden Wortarten enthalten sind.
     * 
     * @param 	w	das zu suchende Wort
     * @throws	WortNichtVorhandenException
     * @return	Wortzuordnung
     * @see		stilanalyse.Wortzuordnung
     */
    Wortzuordnung gibWort(String w) throws WortNichtVorhandenException;
    
    /**
     * �berpr�ft, ob das Wort im Wortpool vorhanden ist.
     * 
     * @param	w	das Wort
     * @return	boolean
     */
    boolean istWortVorhanden(String w);
    
    /**
     * F�gt eine neue Wortart ein.
     * 
     * Falls die Worart bereits existiert, wird eine Exception 
     * WortartBereitsVorhandenException geworfen.
     * 
     * @param	wa	der Name der neuen Wortart
     * @throws	WortartBereitsVorhandenException
     */
    void fuegeWortartEin(String wa) throws WortartBereitsVorhandenException;    
    
    /**
     * F�gt ein (neues) Wort in eine (neue) Wortart ein, bzw. 
     * f�gt eine Beziehung eines Wortes zu einer Wortart hinzu.  
     * 
     * <ul>
     *   <li>Falls das Wort (w) in der Wortart (wa) schon vorhanden ist,
     *       darf es nicht erneut eingef�gt werden, es wird eine Exception
     *       WortBereitsZugeordnetException geworfen.</li> 
     *   <li>Falls die Wortart noch nicht existiert, muss sie
     *       zuerst angelegt werden, es wird eine Exception 
     *       WortartNichtVorhandenException geworfen.</li>
     *   <li>Falls ein leeres, also ung�ltiges Wort �bergeben
     *       wird, wir eine Exception LeeresWortException geworfen.</li>
     * </ul>
     * 
     * @param	w	das einzuf�gende Wort
     * @param	wa	der Name der zum Wort geh�renden Wortart
     * @throws	WortartNichtVorhandenException
     * @throws	WortBereitsZugeordnetException
     * @throws  LeeresWortException
     * @see		WoerterbuchVerwaltung#nimmWortAuf(String, String)
     * @see		WoerterbuchVerwaltung#weiseWortZu(String, String)
     */
    void fuegeEin(String w, String wa) 
    	throws WortartNichtVorhandenException, WortBereitsZugeordnetException, LeeresWortException;
    
    /**
     * L�scht ein Wort aus <em>einer</em> Wortart.
     * 
     * <ul>
     *   <li>Falls das Wort (w) in der Wortart (wa) nicht existiert, wird eine
     *       Exception "WortNichtVorhandenException" geworfen.</li>
     *   <li>Falls die Wortart nicht existiert, wird eine
     *       Exception "WortartNichtVorhandenException" geworfen.</li>
     *   <li>Falls das Wort nach dem L�schen keiner weiteren Wortart zugeordnet
     *       ist, wird es automatisch in die Wortart "Sonstige" eingef�gt.</li>
     * </ul>
     * 
     * @param	w	das zu l�schende Wort
     * @param	wa	der Name der zum Wort geh�renden Wortart
     * @throws	WortNichtVorhandenException
     * @throws	WortartNichtVorhandenException
     * @see		WoerterbuchVerwaltung#loescheWort(String, String)
     */
    void loesche(String w, String wa)
    	throws WortNichtVorhandenException, WortartNichtVorhandenException;
    
    /**
     * L�scht ein Wort aus <em>allen</em> Wortarten.
     *  
     * Falls das Wort (w) nicht existiert, wird eine 
     * Exception "WortNichtVorhandenException" geworfen 
     * 
     * @param	w	das zu l�schende Wort in jeder Wortart
     * @see		WoerterbuchVerwaltung#loescheWort(String)
     */
    void loesche(String w) throws WortNichtVorhandenException;
    
    /**
     * Gibt die HashSet mit den zur Wortart (wa) geh�renden W�rtern 
     * (Objekte vom Typ <code>stilanalyse.Wort</code>) zur�ck.
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
