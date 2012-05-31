/*
 * Wortart.java
 *
 * Datum: 13. Dezember 2005
 *
 * Autoren: Jens Pfeifer, Ralf Lehmann, Matthias Larisch, Bekim Qela
 */

package stilanalyse;

//import stilanalyse.Wortart;
/*
import stilanalyse.WortNichtVorhandenException;
import stilanalyse.WortBereitsZugeordnetException;
import stilanalyse.WortartNichtVorhandenException;
*/
import java.util.HashSet;
import java.util.Iterator;
import java.io.Serializable;

public class WortartImpl implements Wortart, Serializable
{
    private String name;
    private HashSet woerter;
    private transient Iterator it;

    /**
     * Konstruktor mit Übergabeparameter Name der Wortart.
     * 
     * In der Klasse zu ergänzen, welche dieses Interface implementiert.
     *
     * @param	name	der Name der Wortart
     */
    public WortartImpl(String wa) {
        name=new String(wa);
        woerter=new HashSet();
        it=woerter.iterator();
    }

    /**
     * Holt das <code>name</code>-Attribut.
     * 
     * @return	String
     */
    public String getName() {
        return new String(name);
    }

    /**
     * Gibt das nächste Wort aus der <code>woerter</code>-HashSet zurück.
     * Im Konstruktor wird die aktuelle Position auf Null initialisiert.  
     * Bei leerem bzw. am Ende des Datencontainers wird null zurückgegeben.
     *
     * @return	Wort
     * @see		Wort
     */	
    public Wort gibNaechstesWort() {
        if(it == null) it = woerter.iterator();
        if(it.hasNext()) return (Wort)it.next();
        else
        {
            it=woerter.iterator();
            return null;
        }
    }

   /**
     * Fügt eine Referenz auf das Wort w hinzu.
     * 
     * <ul>
     * 	<li>Falls das Wort (w) der Wort bereits zugeordnet ist, wird eine
     * 		Exception "WortBereitsZugeordnetException" geworfen</li>
     * </ul>
     * 
     * @param	w	das hinzuzufügende Wort
     * @throws	WortBereitsZugeordnetException
     * @see		Wort
     */
    public void fuegeWortEin(Wort w) throws
     WortBereitsZugeordnetException {
        if(!woerter.add(w)) throw new WortBereitsZugeordnetException();
        it=woerter.iterator();
    }

    /**
     * Entfernt die Referenz auf das Wort w.
     * 
     * <ul>
     * 	<li>Falls das Wort (w) der Wortart nicht zugeordnet ist, wird eine
     * 		Exception "WortNichtZugeordnetException" geworfen</li>
     * </ul>
     * 
     * @param	w	das zu löschende Wort
     * @throws	WortNichtZugeordnetException
     * @see		Wort
     */  
    public void loescheWort(Wort w) throws
     WortNichtZugeordnetException {
        if(!woerter.remove(w)) throw new WortNichtZugeordnetException();
        it=woerter.iterator();
    }
}
