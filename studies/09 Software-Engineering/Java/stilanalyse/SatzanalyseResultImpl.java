package stilanalyse;
import java.util.HashMap;


/**
 * Implementierung SatzanalyseResult (SatzanalyseResultImpl.java)
 * 
 * @author  Matthias Knochen (mknochen@fh-konstanz.de)
 * @author  Christian Held (cheld@fh-konstanz.de)
 * @version 1.01 (09.01.2006)
 */
public class SatzanalyseResultImpl implements SatzanalyseResult
{
	private int anzahlNebensaetze = 0;
	private int anzahlSaetze = 0;
	private double durchschnittlicheSatzlaenge = 0;
	private HashMap satzlaengen = null; 

	public SatzanalyseResultImpl()
	{
		this.satzlaengen = new HashMap();
	}
	public double getDurchschnittlicheSatzlaenge()
	{ return this.durchschnittlicheSatzlaenge; 	}

	public void setDurchschnittlicheSatzlaenge(double schnitt) throws NegativValueException
	{
		if( schnitt < 0 )
			throw new NegativValueException();
		this.durchschnittlicheSatzlaenge = schnitt;
	}

	public int getAnzahlNebensaetze() 
	{ return this.anzahlNebensaetze; }

	public int getAnzahlSaetze()
	{ return this.anzahlSaetze; }

	public HashMap getAnzahlSatzlaenge()
	{ return this.satzlaengen; }

	public void erhoeheAnzahlNebensaetze() throws IntegerOverflowException
	{ this.anzahlNebensaetze++; }

	public void erhoeheAnzahlSaetze() throws IntegerOverflowException
	{ this.anzahlSaetze++; }

	public void erhoeheAnzahlSatzlaenge(int satzlaenge) throws IntegerOverflowException
	{
		// HashKey ist satzlaenge
		Integer key = new Integer(satzlaenge);
		
		if (this.satzlaengen.containsKey(key))
		{
			int anz = ((Integer)this.satzlaengen.get(key)).intValue();
			this.satzlaengen.put(key,new Integer(++anz));
		}
		else
			this.satzlaengen.put(key, new Integer(1));
	}
}
