package stilanalyse;

import java.util.Vector;

/**
 * Interface Romanverwaltung.
 * 
 * Das Objekt wird �ber die GUI beim �ffnen serialisiert und beim 
 * Beenden deserialisiert.
 * Die Klasse, welches dieses Interface implementiert muss
 * au�erdem das Interface "Serializable" implementieren. 
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  Andr� Erb (superb@fh-konstanz.de)
 * @date    2006-01-06
 */
public interface Romanverwaltung {
    /**
     * Holt das <code>romanListe</code>-Attribut.
     * 
     * @return	java.util.Vector
     */
    Vector getRomanListe();

    /**
     * Sucht in der Roman-Liste anhand von Titel und Autor nach dem Roman und
     * gibt ihn zur�ck.
     * 
     * Falls der Roman nicht existiert, wird eine Exception
     * "RomanNichtVorhandenException" geworfen.
     * 
     * @param	titel	der Titel des Romans
     * @param	autor	der Autor des Romans
     * @return	Roman
     * @throws	RomanNichtVorhandenException
     */
    Roman getRoman(String titel, String autor) throws RomanNichtVorhandenException;
    
    /**
     * Sucht in der Roman-Liste anhand von Titel und Autor nach dem Roman und
     * entfernt ihn.
     * 
     * Falls der Roman nicht existiert, wird eine Exception
     * "RomanNichtVorhandenException" geworfen.
     * 
     * @param	titel	der Titel des zu l�schenden Romans
     * @param	autor	der Autor des zu loschenden Romans
     * @throws	RomanNichtVorhandenException
     */
    void loescheRoman(String titel, String autor) throws RomanNichtVorhandenException;
    
    /**
     * F�gt Roman mit �bergebenen Parametern in die Roman-Liste ein.
     * 
     * Falls der Roman bereits existiert (Titel und Autor), wird eine Exception
     * "RomanBereitsVorhandenException" geworfen.
     * 
     * @param	titel	der Titel des einzuf�genden Romans
     * @param	autor	der Autor des einzuf�genden Romans
     * @param	datum	das Datum des einzuf�genden Romans
     * @param	anzahlVerkauft	die Anzahl verkaufter Exemplare des 
     * 							einzuf�genden Romans
     * @param	dateipfad	der Dateipfad des einzuf�genden Romans
     * @throws	RomanBereitsVorhandenException
     */
    void fuegeRomanEin(String titel, String autor, String datum,
                       String anzahlVerkauft, String dateipfad) 
    	throws RomanBereitsVorhandenException;
}
