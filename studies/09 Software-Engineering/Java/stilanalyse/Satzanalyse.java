package stilanalyse;

/**
 * Interface Satzanalyse.
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  André Erb (superb@fh-konstanz.de)
 */
public interface Satzanalyse {
    /**
     * Führt die Satzanalyse für den übergebenen Roman durch.
     * 
     * Kann die Analyse nicht vollständig durchgeführt werden, wird eine
     * Exception "SatzanalyseIncompleteException" geworfen.
     * 
     * @param	r	der zu analysierende Roman
     * @throws	SatzanalyseIncompleteException
     * @see		Roman
     */
    void fuehreAnalyseDurch(Roman r) throws SatzanalyseIncompleteException;
}
