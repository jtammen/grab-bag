package stilanalyse;

/**
 * Interface Wortanalyse.
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  Andr� Erb (superb@fh-konstanz.de)
 */
public interface Wortanalyse {
	/**
	 * Konstruktor mit Parameter W�rterbuch-Objekt.
	 * 
     * In der Klasse zu erg�nzen, welche dieses Interface implementiert.
     * 
	 * @param	wb	das W�rterbuch
	 */
	//Wortanalyse(Woerterbuch wb);
	
    /**
     * F�hrt die Wortanalyse f�r den �bergebenen Roman durch.
     * 
     * Kann die Analyse nicht vollst�ndig durchgef�hrt werden, wird eine
     * Exception "WortanalyseIncompleteException" geworfen.
     * 
     * @param	r	der zu analysierende Roman
     * @throws	WortanalyseIncompleteException
     * @see		Roman
     */
    void fuehreAnalyseDurch(Roman r) throws WortanalyseIncompleteException;
    
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
