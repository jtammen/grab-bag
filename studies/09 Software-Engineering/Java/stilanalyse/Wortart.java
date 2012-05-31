package stilanalyse;

/**
 * Interface Wortart.
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
public interface Wortart {
    /**
     * Konstruktor mit �bergabeparameter Name der Wortart.
     * 
     * In der Klasse zu erg�nzen, welche dieses Interface implementiert.
     *
     * @param	name	der Name der Wortart
     */
    //Wortart(String name);
	
    /**
     * Holt das <code>name</code>-Attribut.
     * 
     * @return	String
     */
    String getName();
    
    /**
     * Gibt das n�chste Wort aus der <code>woerter</code>-HashSet 
     * (enth�lt Objekte vom Typ stilanalyse.Wort) zur�ck.
     * Im Konstruktor wird die aktuelle Position auf Null initialisiert.  
     * Bei leerem bzw. am Ende des Datencontainers wird null zur�ckgegeben.
     *
     * @return	Wort
     * @see		Wort
     */	
    Wort gibNaechstesWort();
    
    /**
     * F�gt eine Referenz auf das Wort w hinzu.
     * 
     * <ul>
     * 	<li>Falls das Wort (w) der Wort bereits zugeordnet ist, wird eine
     * 		Exception "WortBereitsZugeordnetException" geworfen</li>
     * </ul>
     * 
     * @param	w	das hinzuzuf�gende Wort
     * @throws	WortBereitsZugeordnetException
     * @see		Wort
     */
    void fuegeWortEin(Wort w)
    	throws WortBereitsZugeordnetException;
    
    /**
     * Entfernt die Referenz auf das Wort w.
     * 
     * <ul>
     * 	<li>Falls das Wort (w) der Wortart nicht zugeordnet ist, wird eine
     * 		Exception "WortNichtZugeordnetException" geworfen</li>
     * </ul>
     * 
     * @param	w	das zu l�schende Wort
     * @throws	WortNichtZugeordnetException
     * @see		Wort
     */    
    void loescheWort(Wort w)
    	throws WortNichtZugeordnetException;
}
