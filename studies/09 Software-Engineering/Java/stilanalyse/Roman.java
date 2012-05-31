package stilanalyse;

import java.io.IOException;

/**
 * Interface Roman.
 * 
 * Die Klasse, welches dieses Interface implementiert muss
 * außerdem das Interface "Serializable" implementieren. 
 *
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  André Erb (superb@fh-konstanz.de)
 * @date	2005-12-22
 */
public interface Roman {
    /**
     * Holt das <code>text</code>-Attribut.
     * 
     * @return	String
     */
    String getText();
    
    /**
     * Setzt das <code>text</code>-Attribut.
     * 
     * Wenn der übergebene String leer ist, so wird die Methode vorzeitig
     * verlassen und der Wert nicht gesetzt.
     * 
     * @param	text	der Text des Romans
     */
    void setText(String text);
    
    /**
     * Holt das <code>wortanalyseResult</code>-Attribut.
     * 
     * @return	WortanalyseResult
     */
    WortanalyseResult getWortanalyseResult();
    
    /**
     * Holt das <code>satzanalyseResult</code>-Attribut.
     * 
     * @return	SatzanalyseResult
     */
    SatzanalyseResult getSatzanalyseResult();    
    
    /**
     * Holt das <code>titel</code>-Attribut.
     * 
     * @return String
     */
    String getTitel();
    
    /**
     * Setzt das <code>titel</code>-Attribut.
     * 
     * Wenn der übergebene String leer ist, so wird die Methode vorzeitig
     * verlassen und der Wert nicht gesetzt.
     * 
     * @param	titel	der Titel des Romans
     */
    void setTitel(String titel);
    
    /**
     * Holt das <code>autor</code>-Attribut.
     * 
     * @return	String
     */
    String getAutor();
    
    /**
     * Setzt das a<code>autor</code>-Attribut.
     * 
     * Wenn der übergebene String leer ist, so wird die Methode vorzeitig
     * verlassen und der Wert nicht gesetzt.
     * 
     * @param	author	der Autor des Romans
     */
    void setAutor(String author);
    
    /**
     * Holt das <code>datum</code>-Attribut.
     * 
     * @return	String
     */
    String getDatum();
    
    /**
     * Setzt das <code>datum</code>-Attribut.
     * 
     * Wenn das übergebene Datum leer ist, so wird die Methode vorzeitig
     * verlassen und der Wert nicht gesetzt.
     * 
     * @param	datum	das Erscheinungs-Datum des Romans
     */
    void setDatum(String datum);
    
    /**
     * Holt das <code>anzahlVerkauft</code>-Attribut.
     * 
     * @return	String
     */
    String getAnzahlVerkauft();
    
    /**
     * Setzt das <code>anzahlVerkauft</code>-Attribut.
     * 
     * Wenn der Wert <= 0 ist, so wird die Methode vorzeitig verlassen 
     * und der Wert nicht gesetzt.
     * 
     * @param	anzahlVerkauft	die Anzahl der verkauften Romane
     */
    void setAnzahlVerkauft(String anzahlVerkauft);
    
    /**
     * Holt das <code>dateipfad</code>-Attribut.
     * 
     * @return	String
     */
    String getDateipfad();
    
    /**
     * Setzt das <code>dateipfad</code>-Attribut.
     * 
     * Wenn der übergebene String leer ist, so wird die Methode vorzeitig
     * verlassen und der Wert nicht gesetzt.
     * 
     * @param	dateipfad	der Dateipfad des Romans
     */
    void setDateipfad(String dateipfad);
    
    /**
     * Öffnet die Textdatei (<code>dateipfad</code>) und liest deren
     * kompletten Inhalt in das Attribut <code>text</code> ein.
     * 
     * Fehlerbehandlung über die Standard-Java-Exception "IOException".
     * 
     * @throws	IOException
     */
    void leseTextEin() throws IOException;
}
