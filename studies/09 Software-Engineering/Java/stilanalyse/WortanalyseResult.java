package stilanalyse;

import java.util.HashMap;

/**
 * Interface WortanalyseResult.
 *
 * Die Klasse, welches dieses Interface implementiert muss
 * au�erdem das Interface "Serializable" implementieren.
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  Andr� Erb (superb@fh-konstanz.de)
 */
public interface WortanalyseResult {
    /**
     * Holt das <code>anzahlWoerter</code>-Attribut.
     * 
     * @return	long
     */
    long getAnzahlWoerter();
    
    /**
     * Holt das <code>wortzuordnungen</code>-Attribut.
     * 
     * @return	HashMap
     */
    HashMap getWortZuordnungen();
    
    /**
     * Holt das <code>wortHaeufigkeiten</code>-Attribut.
     * 
     * @return	HashMap
     */	
    HashMap getWortHaeufigkeiten();
    
    /**
     * Holt das <code>wortartHaeufigkeiten</code>-Attribut.
     * 
     * @return	HashMap
     */	
    HashMap getWortartenHaeufigkeiten();	
    
    /**
     * Aktualisiert auf Basis der vom Benutzer nach der Analyse 
     * gespeicherten Wortzuordnugnen die H�ufigkeiten der W�rter sowie 
     * die H�ufigkeiten der Wortarten.
     */    
    void updateHaeufigkeiten();
    
    /**
     * �berpr�ft, ob das �bergebene Wort w bereits in der Datenstruktur
     * <code>wortzuordnungen</code> enthalten ist.
     * Wenn dies der Fall ist, werden die entsprechenden Z�hler erh�ht und
     * <code>true</code> zur�ckgegeben, andernfalls wird <code>false</code>
     * zur�ckgegeben und das das Wort muss im W�rterbuch (siehe 
     * {@link Woerterbuch#gibWort(String)}) gesucht werden.
     * 
     * <ul>
     * 	<li>Ist nicht gen�gend Speicher zum Einf�gen in die HashMap 
     * 		vorhanden, wird "WortHashMapOverflowException" geworfen</li>
     * </ul>
     * 
     * @param	w	das einzuf�gende/zu aktualisierende Wort
     * @return	boolean
     * @throws	WortHashMapOverflowException
     */
    boolean fuegeWortEin(String w) throws WortHashMapOverflowException;
    
    /**
     * F�gt ein neues Wort (eine Wortzuordnung) in die Datenstruktur
     * <code>wortzuordnungen</code> ein. Als Schl�ssel dient dabei der
     * Name der Wortart; der Wert ist ein Datenstruktur, welche alle
     * zu dieser Wortart geh�renden W�rter enth�lt.
     * 
     * <ul>
     * 	<li>Wenn das �bergebene Objekt ein leeres Wort bzw. keine
     * 		Wortarten enth�lt, wird eine Exception 
     * 		"UngueltigeWortzuordnungException" geworfen</li>
     * 	<li>Ist nicht gen�gend Speicher zum Einf�gen in die HashMap 
     * 		vorhanden, wird "WortHashMapOverflowException" geworfen</li>
     * </ul>
     * 
     * @param	wz	die einzuf�gende Wortzuordnung
     * @throws	UngueltigeWortzuordnungException
     * @throws	WortHashMapOverflowException
     * @see		Wortzuordnung
     */
    void fuegeNeuesWortEin(Wortzuordnung wz) 
        throws UngueltigeWortzuordnungException, WortHashMapOverflowException;
    
    /**
     * Erh�ht die Anzahl der W�rter um eins.
     * 
     * Wenn der Wertebereich von Integer �berschritten wird, 
     * wird eine Exception "LongOverflowException" geworfen
     * 
     * @throws	LongOverflowException
     */
    void erhoeheAnzahlWoerter();
    
    /**
     * Erh�ht die Anzahl der W�rter der �bergebenen Wortart (wa) um eins.
     * 
     * Falls die Wortart in der Datenstruktur nicht existiert, 
     * wird eine Exception "WortartNichtVorhandenException" geworfen.
     * 
     * @param	wa	der Name der zu aktualisierenden Wortart
     * @throws	WortartNichtVorhandenException
     */
    void erhoeheAnzahlWortart(String wa) throws WortartNichtVorhandenException;
    
    /**
     * Erh�ht die Anzahl des Wortes w um eins.
     * 
     * Falls das Wort in der Datenstruktur nicht existiert, 
     * wird eine Exception "WortNichtVorhandenException" geworfen.
     * 
     * @param	w	das zu aktualisierende Wort
     * @throws	WortNichtVorhandenException
     */
    void erhoeheAnzahlWort(String w) throws WortNichtVorhandenException;
}
