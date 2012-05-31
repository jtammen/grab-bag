package stilanalyse;

/**
 * Interface Satzanalyse.
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  Andr� Erb (superb@fh-konstanz.de)
 */
public interface Satzanalyse {
    /**
     * F�hrt die Satzanalyse f�r den �bergebenen Roman durch.
     * 
     * Kann die Analyse nicht vollst�ndig durchgef�hrt werden, wird eine
     * Exception "SatzanalyseIncompleteException" geworfen.
     * 
     * @param	r	der zu analysierende Roman
     * @throws	SatzanalyseIncompleteException
     * @see		Roman
     */
    void fuehreAnalyseDurch(Roman r) throws SatzanalyseIncompleteException;
}
