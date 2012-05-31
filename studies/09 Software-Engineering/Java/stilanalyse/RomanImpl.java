package stilanalyse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implementierung Roman (RomanImpl.java)
 * 
 * @author  Matthias Knochen (mknochen@fh-konstanz.de)
 * @author  Christian Held (cheld@fh-konstanz.de)
 * @version 1.01 (09.01.2006)
 */
public final class RomanImpl implements Roman
{
	// Attribute
	private String autor = "";
	private String titel = "";
	private String text = ""; 
	private String dateipfad = "";
	private String datum = "";
	private String anzahlVerkauft = "";
	private WortanalyseResult wortanalyseResult;
	private SatzanalyseResult satzanalyseResult;
	
	
	public RomanImpl()
	{
		this.wortanalyseResult = new WortanalyseResultImpl();
		this.satzanalyseResult = new SatzanalyseResultImpl();
		
	}
	// Autor
	public String getAutor()
	{ return this.autor; }
	public void setAutor(String autor)
	{ this.autor = autor; }
	
	// Titel
	public String getTitel()
	{ return titel; }
	public void setTitel(String titel)
	{ this.titel = titel; }
	
	// Text
	public String getText()
	{ return this.text; }
	public void setText(String text)
	{ this.text = text; }
	
	// Dateipfad
	public String getDateipfad()
	{ return this.dateipfad; }
	public void setDateipfad(String dateiPfad)
	{ this.dateipfad = dateiPfad; }
	
	// Datum
	public String getDatum()
	{ return this.datum; }
	public void setDatum(String datum)
	{ this.datum = datum; }
	
	// AnzahlVerkauft
	public String getAnzahlVerkauft()
	{ return this.anzahlVerkauft; }
	public void setAnzahlVerkauft(String anzahlVerkauft)
	{ this.anzahlVerkauft = anzahlVerkauft; }
	
	// WortanalyseResult
	public WortanalyseResult getWortanalyseResult()
	{ return this.wortanalyseResult; }
	public void setwortanalyseResult(WortanalyseResult wortanalyseResult)
	{ this.wortanalyseResult = wortanalyseResult; }
	
	// SatzanalyseResult
	public SatzanalyseResult getSatzanalyseResult()
	{ return this.satzanalyseResult; }
	public void setSatzanalyseResult(SatzanalyseResult satzanalyseResult)
	{ this.satzanalyseResult = satzanalyseResult; }
	
	
	public void leseTextEin()
	{
		// Pro Objekt nur einmal Text einlesen
		if( this.text != "" )
			return;
		
	    StringBuffer buffer = new StringBuffer();
	    BufferedReader input = null;
	    
	    try 
	    {
	    	input = new BufferedReader( new FileReader(this.dateipfad) );
	    	String line = null; 
	    	while ((line=input.readLine()) != null)
	    	{
	    		buffer.append(line); 
	    		buffer.append(System.getProperty("line.separator"));
	    	}
	    }
	    catch (IOException ex)
	    { ex.printStackTrace(); }
	    finally 
	    {
	    	try
	    	{
	    		if (input!= null) 
	    			input.close();
	    	}
	    	catch (IOException ex)
	    	{ ex.printStackTrace(); }
	    }
	    this.text = buffer.toString();
	}	
}
