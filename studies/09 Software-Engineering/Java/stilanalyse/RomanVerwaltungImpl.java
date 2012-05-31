package stilanalyse;
import java.io.Serializable;
import java.util.Vector;

/**
 * Implementierung Romanverwaltung (RomanverwaltungImpl.java)
 * 
 * @author  Matthias Knochen (mknochen@fh-konstanz.de)
 * @author  Christian Held (cheld@fh-konstanz.de)
 * @version 1.01 (09.01.2006)
 */
public class RomanVerwaltungImpl implements Romanverwaltung, Serializable
{
	private static final long serialVersionUID = 1L;
	private Vector romanListe;
	
	public RomanVerwaltungImpl()
	{
		this.romanListe = new Vector();
	}
	
	public Vector getRomanListe()
	{ return romanListe; }
	public void setRomanListe(Vector romanListe)
	{ this.romanListe = romanListe; }

	public Roman getRoman(String titel, String autor) throws RomanNichtVorhandenException
	{	
		for(int i=0; i<this.romanListe.size(); i++)
		{
			Roman roman = this.getRomanByIndex(i);
			if(	roman.getTitel() == titel &&
				roman.getAutor() == autor)
				return roman;
		}
		throw new RomanNichtVorhandenException();
	}

	public void loescheRoman(String titel, String autor) throws RomanNichtVorhandenException
	{
		this.romanListe.remove(this.getRoman(titel,autor));
	}

	public void fuegeRomanEin(String titel, String autor, String datum, String anzahlVerkauft, String dateipfad) throws RomanBereitsVorhandenException
	{
		if( titel.trim() == "" || autor.trim() == "" )
			return;
		
		RomanImpl r = new RomanImpl();
		r.setTitel(titel);
		r.setAutor(autor);
		r.setDatum(datum);
		r.setAnzahlVerkauft(anzahlVerkauft);
		r.setDateipfad(dateipfad);
        // TODO: Beim Integrieren eingefügt, da Text des Romans sonst nicht eingelesen wird
        // 2006-01-19, 18:00
        r.leseTextEin();
		
		if(this.romanListe.contains(r))
			throw new RomanBereitsVorhandenException();
		
		this.romanListe.add(r);
	}
	
	private Roman getRomanByIndex(int index)
	{
		return (Roman)this.romanListe.elementAt(index);
	}
}
