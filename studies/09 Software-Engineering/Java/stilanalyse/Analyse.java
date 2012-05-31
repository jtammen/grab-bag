package stilanalyse;

/**
 * Interface Analyse.
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  André Erb (superb@fh-konstanz.de)
 */
public interface Analyse {
    /**
     * Startet nacheinander die Wort- und Satzanalyse für den
     * übergebenen Roman.
     * 
     * @param	wb	das Wörterbuch-Objekt
     * @param	r	der zu analysierende Roman
     * @see		Roman
     */
    void starteAnalyse(Woerterbuch wb, Roman r);
    
    /**
     * Übernimmt die vom Benutzer in der aktuellen Analyse 
     * (siehe {@link WortanalyseResult}) erstellten 
     * Wortzuordnungen in das globale Wörterbuch.
     * 
     * Wurden vom Benutzer noch nicht alle Wörter kategorisiert, wird 
     * eine Exception "ZuordnungIncompleteException" geworfen.
     * 
     * @param   r   der Roman, dessen Zuordnungen (in der Wortanalyse)
     *              in das Wörterbuch übernommen werden sollen     
     * @throws	ZuordnungIncompleteException
     * @see		Woerterbuch
     */
    void updateWoerterbuch(Roman r) throws ZuordnungIncompleteException;    
}
