package stilanalyse;

/**
 * Interface Wort.
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
public interface Wort {
    /**
     * Konstruktor mit Übergabeparameter Wort.
     * 
     * In der Klasse zu ergänzen, welche dieses Interface implementiert.
     * 
     * @param	w	das Wort
     */
    //Wort(String w);	
	
    /**
     * Holt das <code>wort</code>-Attribut.
     * 
     * @return	String
     */    
    String getWort();
        
    /**
     * Gibt die nächste Wortart aus der <code>wortarten</code>-HashSet 
     * (enthält Objekte vom Typ stilanalyse.Wortart) zurück.
     * Im Konstruktor wird die aktuelle Position auf Null initialisiert.  
     * Bei leerem bzw. am Ende des Datencontainers wird null zurückgegeben.
     *
     * @return	Wortart
     * @see		Wortart
     */
    Wortart gibNaechsteWortart();
    
    /**
     * Fügt eine Referenz auf die Wortart wa hinzu.
     * 
     * <ul>
     * 	<li>Falls die Wortart (wa) dem Wort bereits zugeordnet ist, wird eine
     * 		Exception "WortartBereitsZugeordnetException" geworfen</li>
     * </ul>
     * 
     * @param	wa	die hinzuzufügende Wortart
     * @throws	WortartBereitsZugeordnetExpcetion
     * @see		Wortart
     */    
    void fuegeWortartEin(Wortart wa) 
    	throws WortartBereitsZugeordnetExpcetion;
    
    /**
     * Entfernt die Referenz auf die Wortart wa.
     * 
     * <ul>
     * 	<li>Falls die Wortart (wa) dem Wort nicht zugeordnet ist, wird eine
     * 		Exception "WortartNichtZugeordnetException" geworfen</li>
     * </ul>
     * 
     * @param	wa	die zu löschende Wortart
     * @throws	WortartNichtZugeordnetException
     * @see		Wortart 
     */    
    void loescheWortart(Wortart wa)
    	throws WortartNichtZugeordnetException;
}
