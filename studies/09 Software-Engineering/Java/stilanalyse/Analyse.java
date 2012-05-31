package stilanalyse;

/**
 * Interface Analyse.
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  Andr� Erb (superb@fh-konstanz.de)
 */
public interface Analyse {
    /**
     * Startet nacheinander die Wort- und Satzanalyse f�r den
     * �bergebenen Roman.
     * 
     * @param	wb	das W�rterbuch-Objekt
     * @param	r	der zu analysierende Roman
     * @see		Roman
     */
    void starteAnalyse(Woerterbuch wb, Roman r);
    
    /**
     * �bernimmt die vom Benutzer in der aktuellen Analyse 
     * (siehe {@link WortanalyseResult}) erstellten 
     * Wortzuordnungen in das globale W�rterbuch.
     * 
     * Wurden vom Benutzer noch nicht alle W�rter kategorisiert, wird 
     * eine Exception "ZuordnungIncompleteException" geworfen.
     * 
     * @param   r   der Roman, dessen Zuordnungen (in der Wortanalyse)
     *              in das W�rterbuch �bernommen werden sollen     
     * @throws	ZuordnungIncompleteException
     * @see		Woerterbuch
     */
    void updateWoerterbuch(Roman r) throws ZuordnungIncompleteException;    
}
