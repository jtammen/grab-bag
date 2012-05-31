package stilanalyse;

import java.util.HashMap;

/**
 * Interface SatzanalyseResult.
 *
 * Die Klasse, welches dieses Interface implementiert muss
 * au�erdem das Interface "Serializable" implementieren. 
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  Andr� Erb (superb@fh-konstanz.de)
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
     * @param	schnitt	die durchschnittliche Satzl�nge
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
     * Erh�ht die Anzahl der Nebens�tze um eins.
     * 
     * Wenn der Wertebereich von Integer �berschritten wird, 
     * wird eine Exception "IntegerOverflowException" geworfen.
     */
    void erhoeheAnzahlNebensaetze()
    	throws IntegerOverflowException;
    
    /**
     * Erh�ht die Anzahl der S�tze um eins.
     * 
     * Wenn der Wertebereich von Integer �berschritten wird, 
     * wird eine Exception "IntegerOverflowException" geworfen.
     */
    void erhoeheAnzahlSaetze()
    	throws IntegerOverflowException;
    
    /**
     * Erh�ht die Anzahl der S�tze mit der �bergebenen L�nge um eins.
     * 
     * Wenn der Wertebereich von Integer �berschritten wird, 
     * wird eine Exception "IntegerOverflowException" geworfen.
     * 
     * @param	satzlaenge	die Satzl�nge 
     */
    void erhoeheAnzahlSatzlaenge(int satzlaenge)
    	throws IntegerOverflowException;
}
