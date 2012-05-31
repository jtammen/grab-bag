package stilanalyse;

import java.text.BreakIterator;
import java.util.Vector;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

/**
 * Implementierung Satzanalyse (SatzanalyseImpl.java)
 * 
 * @author  Matthias Knochen (mknochen@fh-konstanz.de)
 * @author  Christian Held (cheld@fh-konstanz.de)
 * @version 1.01 (09.01.2006)
 */
public class SatzanalyseImpl implements Satzanalyse
{
	private SatzanalyseResultImpl res 		= null;
	private String	romanText 				= null;
	private Vector sentenceList			= null;
	private Vector signalWords				= null;
	
	public SatzanalyseImpl()
	{
		this.sentenceList		= new Vector();
		this.initSignalWordList();
	}
	
	private String getSentence(int index)
	{
		return this.sentenceList.elementAt(index).toString();
	}
	
	private void initSignalWordList()
	{
		this.signalWords 		= new Vector();
		
		// Falls ein Satz mit Nebensatz eingeleitet wird,
		// können diese Wörter am Satzanfang stehen
		String[] wordsUpper = new String[] 
		{"Als","Wenn","Obwohl","Aber","Weil", "Anstatt",
		 "Indem","Während","Damit","Wo","Dass","Daß" }; 
		
		// Diese Wörter sind nach einem Komma grundsätzlich
		// ein Signalwort für einen Nebensatz
		String[] wordsLower = new String[]
		{"der","die","das","den","deren","welche",
		 "welcher","welches","wessen","dessen","jene",
		 "wem", "wo", "wann","da","so", "und","in","im",
		 "unter","über","mitten", "hinter","vor", "an",
		 "wie","was","mit","ohne"};
		
		for(int i=0; i<wordsUpper.length;i++)
			this.signalWords.add(wordsUpper[i]);
		
		for(int i=0; i<wordsUpper.length;i++)
			this.signalWords.add(wordsUpper[i].toLowerCase());
		
		for(int i=0; i<wordsLower.length;i++)
			this.signalWords.add(wordsLower[i]);
	}
	
	public void fuehreAnalyseDurch(Roman r) throws SatzanalyseIncompleteException 
	{
		this.res = (SatzanalyseResultImpl) r.getSatzanalyseResult();
		
		// Störende Strings entfernen
		this.romanText = r.getText().trim().
				replaceAll(System.getProperty("line.separator")," ").
				replaceAll("\"", "").
				replaceAll("“", "").
				replaceAll("„", "");

		this.getSentenceCount();
		this.getSubSentenceCount();
		this.setDurchschnittlicheSatzlaenge();

	}
	
	// Bestimmt die Anzahl der Sätze im Romantext
	// und aktualisiert die Anzahl der verschiedenen Satzlängen in der HashMap
	private void getSentenceCount()
	{
		BreakIterator sentenceIterator = BreakIterator.getSentenceInstance();
		sentenceIterator.setText(this.romanText);
		
		int start 	= sentenceIterator.first(); 
		int end 	= sentenceIterator.next();
		int length;
		 
		while(end != BreakIterator.DONE)
		{
			length = end - start;
			try	{ res.erhoeheAnzahlSaetze(); }
			catch( Exception ex ) {	ex.getStackTrace(); }
			try	{ res.erhoeheAnzahlSatzlaenge(length); }
			catch( Exception ex ) {	ex.getStackTrace();	}
			
			if(length>1)
				this.sentenceList.add( this.romanText.substring(start,end) );

			start = end;
			end = sentenceIterator.next();
		}
	}
	
	// Bestimmt die Anzahl der Nebensätze
	private void getSubSentenceCount()
	{
		// Die Anzahl der Nebensätze kann nur näherungsweise bestimmt werden, 
		// da es keinen Univeralalgorithmus zur Bestimmung aller Nebensätze
		// einschließlich Sonderfälle gibt.
		// Ein Problem stellen z.B. Aufzählungen dar, in denen einzelne Elemente
		// mit einem Signalwort eingeleitet werden.
		// Bsp: "Heute gehen ich, der Peter aus Berlin, die Susi aus Konstanz und Klaus schwimmen."
		
		// Die private Methode getSubSentenceCount darf grundsätzlich nur
		// dann ausgeführt werden, wenn zuvor getSubSentenceCount aufgerufen
		// wurde, d.h. schon die Anzahl der Sätze bestimmt worden ist.
		if( this.sentenceList.size() == 0)
			return;
				
		for(int i=0; i<this.sentenceList.size(); i++)
		{
			String sentence = this.getSentence(i);
			String first = this.getFirstWord( sentence );
			Vector k = this.getKommata( sentence );
			if(k!=null)
			{
				for(int x=0;x<k.size(); x++)
				{
					// Erstes Wort im Satz
					if(x==0 && this.signalWords.contains(first))
						this.erhoeheAnzahlNebensaetze();
					else
					{
						// Wort nach dem Komma
						first = this.getFirstWord( sentence.substring(
								((Integer) k.elementAt(x) ).intValue()));
						if(this.signalWords.contains(first))
							this.erhoeheAnzahlNebensaetze();
					}
				}
			}
		}
	}
	
	// Gibt das erste Wort aus dem String s zurück.
	// Bsp: getWord(0, "Der Ball ist rund.") gibt "Der" zurück.
	private String getFirstWord(String s)
	{
		BreakIterator wordIterator = BreakIterator.getWordInstance();
		wordIterator.setText(s);
		
		int start = wordIterator.first();
		int end = wordIterator.next();
		while(end != BreakIterator.DONE)
		{
			if((end-start)>1)
				break;
			start=end;
			end = wordIterator.next();
		}
		return s.substring(start,end);
	}
	
	// Gibt einen Vektor mit allen Kommatapositionen eines Strings zurück.
	// Die Anzahl der Kommata in dem String ist dann hier die Vektor-Größe.
	// Bsp: getKommata("Erkläre es nochmal, damit ich es besser verstehe.")
	// gibt einen Vektor mit einem Element (19) zurück.
	private Vector getKommata(String s)
	{
		int last = 0;
		Vector k = new Vector();
		
		while(true)
		{
			int pos = s.indexOf(",",last);
			if(pos==-1)
				break;
			else
			{
				k.add(new Integer(pos));
				last=pos+1;
			}
		}
		if( k.size() == 0)
			return null;
		else
			return k;
	}
	
	private void setDurchschnittlicheSatzlaenge()
	{
		double d = 0;	
		 
		if( this.sentenceList.size() > 0)
		{
			double length = 0;
			for(int i = 0; i < this.sentenceList.size();i++) {
				length += this.sentenceList.elementAt(i).toString().length();
			}

			d = length / (double) this.sentenceList.size();
		}
			
		try	
		{ this.res.setDurchschnittlicheSatzlaenge(d); }
		catch( Exception ex ) 
		{ ex.getStackTrace(); }
	}

	// Weiterleitung mit try-catch-Behandlung
	private void erhoeheAnzahlNebensaetze()
	{
		try	{ res.erhoeheAnzahlNebensaetze(); }
		catch( Exception ex ) {	ex.getStackTrace();	}
	}
}
