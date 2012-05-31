package stilanalyse;

/**
 * Interface Wortanalyse.
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  André Erb (superb@fh-konstanz.de)
 */
public interface Wortanalyse {
	/**
	 * Konstruktor mit Parameter Wörterbuch-Objekt.
	 * 
     * In der Klasse zu ergänzen, welche dieses Interface implementiert.
     * 
	 * @param	wb	das Wörterbuch
	 */
	//Wortanalyse(Woerterbuch wb);
	
    /**
     * Führt die Wortanalyse für den übergebenen Roman durch.
     * 
     * Kann die Analyse nicht vollständig durchgeführt werden, wird eine
     * Exception "WortanalyseIncompleteException" geworfen.
     * 
     * @param	r	der zu analysierende Roman
     * @throws	WortanalyseIncompleteException
     * @see		Roman
     */
    void fuehreAnalyseDurch(Roman r) throws WortanalyseIncompleteException;
    
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
