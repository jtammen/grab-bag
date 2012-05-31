/*
 * WoerterbuchVerwaltung.java
 *
 * Datum: 13. Dezember 2005
 *
 * Autoren: Jens Pfeifer, Ralf Lehmann, Matthias Larisch, Bekim Qela
 */

package stilanalyse;

//import stilanalyse.Woerterbuch;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

public class WoerterbuchVerwaltungImpl implements WoerterbuchVerwaltung {
    private Woerterbuch wb;
    private String woerterbuchName = "woerterbuch.obj";
    public WoerterbuchVerwaltungImpl(){
        try{
            FileInputStream file=new FileInputStream(woerterbuchName);
            ObjectInputStream is = new ObjectInputStream(file);
            wb=(Woerterbuch) is.readObject();
            is.close();
        }catch(FileNotFoundException ex){
            wb=new WoerterbuchImpl();
        }catch(IOException ex){
        }catch(ClassNotFoundException ex){}
    }
        
    /**
     * Gibt die Instanz des Woerterbuch-Objekts zurück.
     * 
     * @return	Woerterbuch
     */
    public Woerterbuch getWoerterbuch()
    {
        return wb;
    }
    
    /**
     * Holt das <code>wortartPool</code>-Attribut.
     * 
     * @return	HashMap
     */
    public HashMap getWortarten(){
        return wb.getWortarten();
    }
    
    /**
     * Schreibt die Datenstruktur des Wörterbuchs in die Datei zurück.
     * 
     * <ul>
     * 	<li>Falls die Datei (d) nicht vorhanden ist, wird eine Exception
     * 		"FileNotFoundException" geworfen (bereits in Java vorhanden).</li>
     *  <li>Falls die Datei (d) nicht schreibbar ist, wird eine Exception
     * 		"IOException" geworfen (bereits in Java vorhanden).</li>
     * </ul>
     * 
     * @param	d	der absolute Dateipfad zur Wörterbuchdatei
     * @throws	FileNotFoundException
     * @throws	IOException
     */
    public void deinitialisiere(String d) throws FileNotFoundException, IOException {
        try{
            wb.deinitialisiere(woerterbuchName);
        }catch(FileNotFoundException ex){
        }catch(IOException ex){}
    }
    
    /**
     * Nimmt ein <em>neues</em> Wort in eine Wortart auf.
     * 
     * <ul>
     *   <li>Falls das Wort (w) in der Wortart (wa) schon vorhanden ist,
     *       darf es nicht erneut eingefügt werden, es wird eine Exception
     *       WortBereitsZugeordnetException geworfen.</li> 
     *   <li>Falls die Wortart noch nicht existiert, muss sie
     *       zuerst angelegt werden, es wird eine Exception 
     *       WortartNichtVorhandenException geworfen.</li>
     * </ul>
     * 
     * @param	w	das einzufügende Wort
     * @param	wa	der Name der zum Wort gehörenden Wortart
     * @throws	WortBereitsZugeordnetException
     * @throws	WortartNichtVorhandenException
     * @throws  LeeresWortException
     */	
    public void nimmWortAuf(String w, String wa) throws
     LeeresWortException, WortartNichtVorhandenException,
     WortBereitsZugeordnetException {
        wb.fuegeEin(w, wa);
    }
    
    /**
     * Fügt eine neue Wortart ein.
     * 
     * Falls die Worart bereits existiert, wird eine Exception 
     * WortartBereitsVorhandenException geworfen.
     * 
     * @param	wa	der Name der neuen Wortart
     * @throws	WortartBereitsVorhandenException
     */
    public void fuegeWortartEin(String wa) throws
     WortartBereitsVorhandenException {
        wb.fuegeWortartEin(wa);
    }
    
    /**
     * Löscht ein Wort aus <em>einer</em> Wortart.
     * 
     * <ul>
     *   <li>Falls das Wort (w) in der Wortart (wa) nicht existiert, wird eine
     *       Exception "WortNichtVorhandenException" geworfen.</li>
     *   <li>Falls die Wortart nicht existiert, wird eine
     *       Exception "WortartNichtVorhandenException" geworfen.</li>
     *   <li>Falls das Wort nach dem Löschen keiner weiteren Wortart zugeordnet
     *       ist, wird es automatisch in die Wortart "Sonstige" eingefügt.</li>
     * </ul>
     * 
     * @param	w	das zu löschende Wort
     * @param	wa	der Name der zum Wort gehörenden Wortart
     * @throws	WortNichtVorhandenException
     * @throws	WortartNichtVorhandenException 
     */
    public void loescheWort(String w, String wa) throws
     WortartNichtVorhandenException, WortNichtVorhandenException {
        wb.loesche(w, wa);
    }
    
    /**
     * Löscht ein Wort aus <em>allen</em> Wortarten.
     * 
     * Falls das Wort (w) nicht existiert, wird eine 
     * Exception "WortNichtVorhandenException" geworfen.
     * 
     * @param	w	das zu löschende Wort
     * @throws	WortNichtVorhandenException
     */	
    public void loescheWort(String w) throws
     WortNichtVorhandenException {
        wb.loesche(w);
    }
    
    /**
     * Gibt die HashSet mit den zur Wortart (wa) gehörenden Wörtern zurück.
     *
     * Falls die Wortart nicht existiert, wird eine
     * Exception "WortartNichtVorhandenException" geworfen.
     * 
     * @param	wa	der Name der Wortart
     * @throws	WortartNichtVorhandenException
     * @return	HashSet
     */
    public HashSet listeWoerterAuf(String wa) throws
        WortartNichtVorhandenException {
        return wb.listeWoerterAuf(wa);
    }
    
    /**
     * Weist einer <em>existierenden</em> Wortart ein 
     * <em>existierendes</em> Wort zu.
     * 
     * <ul>
     *   <li>Falls das Wort (w) in der Wortart (wa) schon vorhanden ist,
     *       darf es nicht erneut eingefügt werden.</li>
     *   <li>Falls die Wortart nicht existiert, wird eine
     *       Exception "WortartNichtVorhandenException" geworfen.</li>
     *   <li>Falls das Wort (w) nicht existiert, wird eine Exception 
     *       "WortNichtVorhandenException" geworfen.</li>
     * </ul>
     * 
     * @param	w	das zuzuweisende Wort
     * @param	wa	der Name der zum Wort gehörenden Wortart
     * @throws	WortNichtVorhandenException
     * @throws	WortartNichtVorhandenException
     */	
   public void weiseWortZu(String w, String wa) throws
           WortNichtVorhandenException, WortartNichtVorhandenException {

//    public void weiseWortZu(String w, String wa) throws
//            WortNichtVorhandenException, WortartNichtVorhandenException {
        try{
            wb.fuegeEin(w, wa);
        }catch (LeeresWortException ex){
        }catch (WortBereitsZugeordnetException ex){
        }
    }
    
    /**
     * Weist einer <em>existierenden</em> Wortart 
     * alle in der Datei enthaltenen Wörter zu.
     * 
     * <ul>
     *   <li>Falls die Wortart nicht existiert, wird eine
     *       Exception "WortartNichtVorhandenException" geworfen.</li>
     *   <li>Falls die Datei (d) nicht existiert ist, wird eine
     *       Expcetion "FileNotFoundException" geworfen (bereits in Java vorhanden)</li>
     *   <li>Falls die Datei (d) nicht lesbar ist, wird eine
     *       Expcetion "IOException" geworfen (bereits in Java vorhanden)</li>
     *   <li>Falls ein Wort in der Wortart (wa) schon vorhanden ist,
     *       darf es nicht erneut eingefügt werden.</li>
     * </ul>
     * 
     * @param	d	der absolute Dateipfad
     * @param	wa	der Name der Wortart
     * @throws	WortartNichtVorhandenException
     * @throws	FileNotFoundException
     * @throws	IOException
     */	
    public void weiseTextdateiZu(String d, String wa) throws
     WortartNichtVorhandenException, FileNotFoundException, IOException {  
        FileReader fr = new FileReader(d);
        BufferedReader file = new BufferedReader(fr);
        
        int valid = 0;
        while(file.ready())
        {
            String wort = new String();
            String w = new String();
            w = file.readLine();

            StringTokenizer tokenizer = new StringTokenizer( w );

            while ( tokenizer.hasMoreTokens() )
            {
                valid = 0;
                wort = tokenizer.nextToken();
                if(wort.indexOf('(') != -1) valid++;
                if(wort.indexOf(')') != -1) valid++;
                if(wort.indexOf('!') != -1) valid++;
                if(wort.indexOf('"') != -1) valid++;
                if(wort.indexOf('%') != -1) valid++;
                if(wort.indexOf('$') != -1) valid++;
                if(wort.indexOf('§') != -1) valid++;
                if(wort.indexOf('/') != -1) valid++;
                if(wort.indexOf('*') != -1) valid++;
                if(wort.indexOf('=') != -1) valid++;
                if(wort.indexOf('#') != -1) valid++;
                if(wort.indexOf('+') != -1) valid++;
                if(wort.indexOf(';') != -1) valid++;
                if(wort.indexOf('.') != -1) valid++;
                if(wort.indexOf(',') != -1) valid++;
                if(wort.indexOf(':') != -1) valid++;
                if(wort.indexOf('µ') != -1) valid++;
                if(wort.indexOf('~') != -1) valid++;
                if(wort.indexOf('?') != -1) valid++;
                if(wort.indexOf('{') != -1) valid++;
                if(wort.indexOf('}') != -1) valid++;
                if(wort.indexOf('[') != -1) valid++;
                if(wort.indexOf(']') != -1) valid++;
                if(wort.indexOf('\\') != -1) valid++;
                if(wort.indexOf('@') != -1) valid++;
                if(wort.indexOf('_') != -1) valid++;
                if(wort.indexOf('<') != -1) valid++;
                if(wort.indexOf('>') != -1) valid++;
                if(wort.indexOf('`') != -1) valid++;
                if(wort.indexOf('´') != -1) valid++;
                
                if(valid == 0)
                {
                    try{
                        wb.fuegeEin(wort, wa);
                    }catch(LeeresWortException ex){
                    }catch(WortBereitsZugeordnetException ex){}
                }
            }
        }
    }
}