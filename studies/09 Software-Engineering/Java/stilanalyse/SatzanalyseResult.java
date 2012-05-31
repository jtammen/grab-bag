package stilanalyse;

import java.util.HashMap;

/**
 * Interface SatzanalyseResult.
 *
 * Die Klasse, welches dieses Interface implementiert muss
 * außerdem das Interface "Serializable" implementieren. 
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  André Erb (superb@fh-konstanz.de)
 * @date	2005-12-22
 */
public interface SatzanalyseResult {
    /**
     * Holt das <code>durchschnittlicheSatzlaenge</code>-Attribut.
     * 
     * @return	double
     */
    double getDurchschnittlicheSatzlaenge();	
    
    /**
     * Setzt das <code>durchschnittlicheSatzlaenge</code>-Attribut.
     *
     * Ist der Parameter negativ, wird eine Exception 
     * "NegativValueException" geworfen
     * 
     * @param	schnitt	die durchschnittliche Satzlänge
     */
    void setDurchschnittlicheSatzlaenge(double schnitt) 
    	throws NegativValueException;
    
    /**
     * Holt das <code>anzahlNebensaetze</code>-Attribut.
     * 
     * @return	int
     */
    int getAnzahlNebensaetze();
    
    /**
     * Holt das <code>anzahlSaetze</code>-Attribut.
     * 
     * @return	int
     */
    int getAnzahlSaetze();
    
    /**
     * Holt das <code>anzahlSatzlaenge</code>-Attribut.
     * 
     * @return	HashMap
     */
    HashMap getAnzahlSatzlaenge();
    
    /**
     * Erhöht die Anzahl der Nebensätze um eins.
     * 
     * Wenn der Wertebereich von Integer überschritten wird, 
     * wird eine Exception "IntegerOverflowException" geworfen.
     */
    void erhoeheAnzahlNebensaetze()
    	throws IntegerOverflowException;
    
    /**
     * Erhöht die Anzahl der Sätze um eins.
     * 
     * Wenn der Wertebereich von Integer überschritten wird, 
     * wird eine Exception "IntegerOverflowException" geworfen.
     */
    void erhoeheAnzahlSaetze()
    	throws IntegerOverflowException;
    
    /**
     * Erhöht die Anzahl der Sätze mit der übergebenen Länge um eins.
     * 
     * Wenn der Wertebereich von Integer überschritten wird, 
     * wird eine Exception "IntegerOverflowException" geworfen.
     * 
     * @param	satzlaenge	die Satzlänge 
     */
    void erhoeheAnzahlSatzlaenge(int satzlaenge)
    	throws IntegerOverflowException;
}
