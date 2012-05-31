package stilanalyse;

import java.util.Vector;

/**
 * Interface Romanverwaltung.
 * 
 * Das Objekt wird über die GUI beim Öffnen serialisiert und beim 
 * Beenden deserialisiert.
 * Die Klasse, welches dieses Interface implementiert muss
 * außerdem das Interface "Serializable" implementieren. 
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  André Erb (superb@fh-konstanz.de)
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
     * gibt ihn zurück.
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
     * @param	titel	der Titel des zu löschenden Romans
     * @param	autor	der Autor des zu loschenden Romans
     * @throws	RomanNichtVorhandenException
     */
    void loescheRoman(String titel, String autor) throws RomanNichtVorhandenException;
    
    /**
     * Fügt Roman mit übergebenen Parametern in die Roman-Liste ein.
     * 
     * Falls der Roman bereits existiert (Titel und Autor), wird eine Exception
     * "RomanBereitsVorhandenException" geworfen.
     * 
     * @param	titel	der Titel des einzufügenden Romans
     * @param	autor	der Autor des einzufügenden Romans
     * @param	datum	das Datum des einzufügenden Romans
     * @param	anzahlVerkauft	die Anzahl verkaufter Exemplare des 
     * 							einzufügenden Romans
     * @param	dateipfad	der Dateipfad des einzufügenden Romans
     * @throws	RomanBereitsVorhandenException
     */
    void fuegeRomanEin(String titel, String autor, String datum,
                       String anzahlVerkauft, String dateipfad) 
    	throws RomanBereitsVorhandenException;
}
