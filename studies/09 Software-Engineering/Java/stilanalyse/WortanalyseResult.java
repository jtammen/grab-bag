package stilanalyse;

import java.util.HashMap;

/**
 * Interface WortanalyseResult.
 *
 * Die Klasse, welches dieses Interface implementiert muss
 * außerdem das Interface "Serializable" implementieren.
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  André Erb (superb@fh-konstanz.de)
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
     * gespeicherten Wortzuordnugnen die Häufigkeiten der Wörter sowie 
     * die Häufigkeiten der Wortarten.
     */    
    void updateHaeufigkeiten();
    
    /**
     * Überprüft, ob das übergebene Wort w bereits in der Datenstruktur
     * <code>wortzuordnungen</code> enthalten ist.
     * Wenn dies der Fall ist, werden die entsprechenden Zähler erhöht und
     * <code>true</code> zurückgegeben, andernfalls wird <code>false</code>
     * zurückgegeben und das das Wort muss im Wörterbuch (siehe 
     * {@link Woerterbuch#gibWort(String)}) gesucht werden.
     * 
     * <ul>
     * 	<li>Ist nicht genügend Speicher zum Einfügen in die HashMap 
     * 		vorhanden, wird "WortHashMapOverflowException" geworfen</li>
     * </ul>
     * 
     * @param	w	das einzufügende/zu aktualisierende Wort
     * @return	boolean
     * @throws	WortHashMapOverflowException
     */
    boolean fuegeWortEin(String w) throws WortHashMapOverflowException;
    
    /**
     * Fügt ein neues Wort (eine Wortzuordnung) in die Datenstruktur
     * <code>wortzuordnungen</code> ein. Als Schlüssel dient dabei der
     * Name der Wortart; der Wert ist ein Datenstruktur, welche alle
     * zu dieser Wortart gehörenden Wörter enthält.
     * 
     * <ul>
     * 	<li>Wenn das übergebene Objekt ein leeres Wort bzw. keine
     * 		Wortarten enthält, wird eine Exception 
     * 		"UngueltigeWortzuordnungException" geworfen</li>
     * 	<li>Ist nicht genügend Speicher zum Einfügen in die HashMap 
     * 		vorhanden, wird "WortHashMapOverflowException" geworfen</li>
     * </ul>
     * 
     * @param	wz	die einzufügende Wortzuordnung
     * @throws	UngueltigeWortzuordnungException
     * @throws	WortHashMapOverflowException
     * @see		Wortzuordnung
     */
    void fuegeNeuesWortEin(Wortzuordnung wz) 
        throws UngueltigeWortzuordnungException, WortHashMapOverflowException;
    
    /**
     * Erhöht die Anzahl der Wörter um eins.
     * 
     * Wenn der Wertebereich von Integer überschritten wird, 
     * wird eine Exception "LongOverflowException" geworfen
     * 
     * @throws	LongOverflowException
     */
    void erhoeheAnzahlWoerter();
    
    /**
     * Erhöht die Anzahl der Wörter der übergebenen Wortart (wa) um eins.
     * 
     * Falls die Wortart in der Datenstruktur nicht existiert, 
     * wird eine Exception "WortartNichtVorhandenException" geworfen.
     * 
     * @param	wa	der Name der zu aktualisierenden Wortart
     * @throws	WortartNichtVorhandenException
     */
    void erhoeheAnzahlWortart(String wa) throws WortartNichtVorhandenException;
    
    /**
     * Erhöht die Anzahl des Wortes w um eins.
     * 
     * Falls das Wort in der Datenstruktur nicht existiert, 
     * wird eine Exception "WortNichtVorhandenException" geworfen.
     * 
     * @param	w	das zu aktualisierende Wort
     * @throws	WortNichtVorhandenException
     */
    void erhoeheAnzahlWort(String w) throws WortNichtVorhandenException;
}
