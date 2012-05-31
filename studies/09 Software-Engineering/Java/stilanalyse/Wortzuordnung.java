package stilanalyse;

import java.util.Vector;

/**
 * Interface Wortzuordnung.
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  André Erb (superb@fh-konstanz.de)
 * @date    2006-01-06 
 */
public interface Wortzuordnung {
    /**
     * Holt das <code>wort</code>-Attribut.
     * 
     * @return	String
     */    
    String getWort();
    
    /**
     * Setzt das <code>wort</code>-Attribut.
     * 
     * @param	w	das Wort
     */
    void setWort(String w);	
    
    /**
     * Holt das <code>wortarten</code>-Attribut.
     * 
     * Datenstruktur: Vector von Strings
     * 
     * @return  Vector
     */    
    Vector getWortarten();
    
    /**
     * Setzt das <code>wortarten</code>-Attribut.
     * 
     * @param  wa	die Struktur mit Wortart-Namen
     */    
    void setWortarten(Vector wa); 
}
