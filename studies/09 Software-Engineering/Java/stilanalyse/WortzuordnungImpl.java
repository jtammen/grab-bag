/*
 * Wortzuordnung.java
 *
 * Datum: 13. Dezember 2005
 *
 * Autoren: Jens Pfeifer, Ralf Lehmann, Matthias Larisch, Bekim Qela
 */

package stilanalyse;

import java.util.Vector;

public class WortzuordnungImpl implements Wortzuordnung {
    private String wort;
    private Vector wortarten;
    
    /**
     * Holt das <code>wort</code>-Attribut.
     * 
     * @return	String
     */ 
    public String getWort(){
        return new String(wort);
    }
    
    /**
     * Setzt das <code>wort</code>-Attribut.
     * 
     * @param	w	das Wort
     */
    public void setWort(String w){
        wort=new String(w);
    }
    
    /**
     * Holt das <code>wortarten</code>-Attribut.
     * 
     * @return  Vector
     */  
    public Vector getWortarten(){
        return wortarten;
    }
    
    /**
     * Setzt das <code>wortarten</code>-Attribut.
     * 
     * @param  wa	die Struktur mit Wortart-Namen
     */ 
    
    public void setWortarten(Vector wa){
        wortarten=new Vector(wa);
    }
}
