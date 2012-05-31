/*
 * Wort.java
 *
 * Datum: 13. Dezember 2005
 *
 * Autoren: Jens Pfeifer, Ralf Lehmann, Matthias Larisch, Bekim Qela
 */

package stilanalyse;

import stilanalyse.Wort;
import stilanalyse.Wortart;
import stilanalyse.WortartNichtVorhandenException;
import stilanalyse.WortBereitsZugeordnetException;
import stilanalyse.WortartNichtZugeordnetException;
import java.util.HashSet;
import java.util.Iterator;
import java.io.Serializable;

public class WortImpl implements Wort, Serializable
{
    private String wort;
    private HashSet wortart;
    private transient Iterator it;
    
    /**
     * Konstruktor mit �bergabeparameter Wort.
     * 
     * In der Klasse zu erg�nzen, welche dieses Interface implementiert.
     * 
     * @param	w	das Wort
     */
    public WortImpl(String w)
    {
        wort=new String(w);
        wortart=new HashSet();
        it=wortart.iterator();
    }

    /**
     * Holt das <code>wort</code>-Attribut.
     * 
     * @return	String
     */   
    public String getWort(){
        return new String(wort);
    }

    /**
     * Gibt die n�chste Wortart aus der <code>wortarten</code>-HashSet zur�ck.
     * Im Konstruktor wird die aktuelle Position auf Null initialisiert.  
     * Bei leerem bzw. am Ende des Datencontainers wird null zur�ckgegeben.
     *
     * @return	Wortart
     * @see		Wortart
     */
    public Wortart gibNaechsteWortart()
    {
        if(it == null) it = wortart.iterator();
        if(it.hasNext()) return (Wortart)it.next();
        else
        {
            it=wortart.iterator();
            return null;
        }
    }

    /**
     * F�gt eine Referenz auf die Wortart wa hinzu.
     * 
     * <ul>
     * 	<li>Falls die Wortart (wa) dem Wort bereits zugeordnet ist, wird eine
     * 		Exception "WortartBereitsZugeordnetException" geworfen</li>
     * </ul>
     * 
     * @param	wa	die hinzuzuf�gende Wortart
     * @throws	WortartBereitsZugeordnetExpcetion
     * @see		Wortart
     */   
    public void fuegeWortartEin(Wortart wa) throws
     WortartBereitsZugeordnetExpcetion {
        if(!wortart.add(wa)) throw new WortartBereitsZugeordnetExpcetion();
        it=wortart.iterator();
    }

    /**
     * Entfernt die Referenz auf die Wortart wa.
     * 
     * <ul>
     * 	<li>Falls die Wortart (wa) dem Wort nicht zugeordnet ist, wird eine
     * 		Exception "WortartNichtZugeordnetException" geworfen</li>
     * </ul>
     * 
     * @param	wa	die zu l�schende Wortart
     * @throws	WortartNichtZugeordnetException
     * @see		Wortart 
     */    
    public void loescheWortart(Wortart wa) throws
     WortartNichtZugeordnetException {
        if(!wortart.remove(wa)) throw new WortartNichtZugeordnetException();
        it=wortart.iterator();
    }
}
