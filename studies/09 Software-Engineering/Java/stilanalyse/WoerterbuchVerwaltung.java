package stilanalyse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Interface WoerterbuchVerwaltung.
 * 
 * @author  Christoph Eck (christoph.eck@fh-konstanz.de)
 * @author  Jan Tammen (jan.tammen@fh-konstanz.de)
 * @author  Daniel Seidel (daniel.seidel@fh-konstanz.de)
 * @author  Andr� Erb (superb@fh-konstanz.de)
 * @date	2005-12-22
 */
public interface WoerterbuchVerwaltung {
    /**
     * Gibt die Instanz des Woerterbuch-Objekts zur�ck.
     * 
     * @return	Woerterbuch
     */
	Woerterbuch getWoerterbuch();
	
    /**
     * Holt das <code>wortartPool</code>-Attribut.
     * 
     * @return	HashMap
     */
	HashMap getWortarten();

    /**
     * Schreibt die Datenstruktur des W�rterbuchs in die Datei zur�ck.
     * 
     * <ul>
     * 	<li>Falls die Datei (d) nicht vorhanden ist, wird eine Exception
     * 		"FileNotFoundException" geworfen (bereits in Java vorhanden).</li>
     *  <li>Falls die Datei (d) nicht schreibbar ist, wird eine Exception
     * 		"IOException" geworfen (bereits in Java vorhanden).</li>
     * </ul>
     * 
     * @param	d	der absolute Dateipfad zur W�rterbuchdatei
     * @throws	FileNotFoundException
     * @throws	IOException
     */
    void deinitialisiere(String d)
    	throws FileNotFoundException, IOException;
	
    /**
     * Nimmt ein <em>neues</em> Wort in eine Wortart auf.
     * 
     * <ul>
     *   <li>Falls das Wort (w) in der Wortart (wa) schon vorhanden ist,
     *       darf es nicht erneut eingef�gt werden, es wird eine Exception
     *       WortBereitsZugeordnetException geworfen.</li> 
     *   <li>Falls die Wortart noch nicht existiert, muss sie
     *       zuerst angelegt werden, es wird eine Exception 
     *       WortartNichtVorhandenException geworfen.</li>
     * </ul>
     * 
     * @param	w	das einzuf�gende Wort
     * @param	wa	der Name der zum Wort geh�renden Wortart
     * @throws	WortBereitsZugeordnetException
     * @throws	WortartNichtVorhandenException
     * @throws  LeeresWortException
     */	
    void nimmWortAuf(String w, String wa) 
    	throws WortBereitsZugeordnetException, WortartNichtVorhandenException, LeeresWortException;
    
    /**
     * F�gt eine neue Wortart ein.
     * 
     * Falls die Worart bereits existiert, wird eine Exception 
     * WortartBereitsVorhandenException geworfen.
     * 
     * @param	wa	der Name der neuen Wortart
     * @throws	WortartBereitsVorhandenException
     */
    void fuegeWortartEin(String wa) throws WortartBereitsVorhandenException;
    
    /**
     * L�scht ein Wort aus <em>einer</em> Wortart.
     * 
     * <ul>
     *   <li>Falls das Wort (w) in der Wortart (wa) nicht existiert, wird eine
     *       Exception "WortNichtVorhandenException" geworfen.</li>
     *   <li>Falls die Wortart nicht existiert, wird eine
     *       Exception "WortartNichtVorhandenException" geworfen.</li>
     *   <li>Falls das Wort nach dem L�schen keiner weiteren Wortart zugeordnet
     *       ist, wird es automatisch in die Wortart "Sonstige" eingef�gt.</li>
     * </ul>
     * 
     * @param	w	das zu l�schende Wort
     * @param	wa	der Name der zum Wort geh�renden Wortart
     * @throws	WortNichtVorhandenException
     * @throws	WortartNichtVorhandenException 
     */
    void loescheWort(String w, String wa) 
    	throws WortNichtVorhandenException, WortartNichtVorhandenException;
    
    /**
     * L�scht ein Wort aus <em>allen</em> Wortarten.
     * 
     * Falls das Wort (w) nicht existiert, wird eine 
     * Exception "WortNichtVorhandenException" geworfen.
     * 
     * @param	w	das zu l�schende Wort
     * @throws	WortNichtVorhandenException
     */	
    void loescheWort(String w) throws WortNichtVorhandenException;
    
    /**
     * Gibt die HashSet mit den zur Wortart (wa) geh�renden W�rtern zur�ck.
     *
     * Falls die Wortart nicht existiert, wird eine
     * Exception "WortartNichtVorhandenException" geworfen.
     * 
     * @param	wa	der Name der Wortart
     * @throws	WortartNichtVorhandenException
     * @return	HashSet
     */
    HashSet listeWoerterAuf(String wa)
    	throws WortartNichtVorhandenException;
    
    /**
     * Weist einer <em>existierenden</em> Wortart ein 
     * <em>existierendes</em> Wort zu.
     * 
     * <ul>
     *   <li>Falls das Wort (w) in der Wortart (wa) schon vorhanden ist,
     *       darf es nicht erneut eingef�gt werden.</li>
     *   <li>Falls die Wortart nicht existiert, wird eine
     *       Exception "WortartNichtVorhandenException" geworfen.</li>
     *   <li>Falls das Wort (w) nicht existiert, wird eine Exception 
     *       "WortNichtVorhandenException" geworfen.</li>
     * </ul>
     * 
     * @param	w	das zuzuweisende Wort
     * @param	wa	der Name der zum Wort geh�renden Wortart
     * @throws	WortNichtVorhandenException
     * @throws	WortartNichtVorhandenException
     */		
    void weiseWortZu(String w, String wa)
    	throws WortNichtVorhandenException, WortartNichtVorhandenException;
    
    /**
     * Weist einer <em>existierenden</em> Wortart 
     * alle in der Datei enthaltenen W�rter zu.
     * 
     * <ul>
     *   <li>Falls die Wortart nicht existiert, wird eine
     *       Exception "WortartNichtVorhandenException" geworfen.</li>
     *   <li>Falls die Datei (d) nicht existiert ist, wird eine
     *       Expcetion "FileNotFoundException" geworfen (bereits in Java vorhanden)</li>
     *   <li>Falls die Datei (d) nicht lesbar ist, wird eine
     *       Expcetion "IOException" geworfen (bereits in Java vorhanden)</li>
     *   <li>Falls ein Wort in der Wortart (wa) schon vorhanden ist,
     *       darf es nicht erneut eingef�gt werden.</li>
     * </ul>
     * 
     * @param	d	der absolute Dateipfad
     * @param	wa	der Name der Wortart
     * @throws	WortartNichtVorhandenException
     * @throws	FileNotFoundException
     * @throws	IOException
     */			
    void weiseTextdateiZu(String d, String wa)
    	throws WortartNichtVorhandenException, FileNotFoundException, IOException;
}
