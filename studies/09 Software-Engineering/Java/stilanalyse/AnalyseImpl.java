package stilanalyse;

/**
 * Implementierung Analyse (AnalyseImpl.java)
 * 
 * @author  Matthias Knochen (mknochen@fh-konstanz.de)
 * @author  Christian Held (cheld@fh-konstanz.de)
 * @version 1.01 (09.01.2006)
 */
public class AnalyseImpl implements Analyse
{
	private WortanalyseImpl wa = null;
	private SatzanalyseImpl sa = null;
	
	public void starteAnalyse(Woerterbuch wb, Roman r)
	{
		this.wa = new WortanalyseImpl(wb);
		try
		{ 
			this.wa.fuehreAnalyseDurch(r);
			this.sa = new SatzanalyseImpl();
			try
			{ this.sa.fuehreAnalyseDurch(r); }
			catch( SatzanalyseIncompleteException ex)
			{ ex.printStackTrace(); }
		}
		catch( WortanalyseIncompleteException ex)
		{ ex.printStackTrace(); }
	}

	// Diese Methode kann erst ausgeführt werden, 
	// nachdem starteAnalyse(..) ausgeführt wurde
	// (Ansonsten ist wa=null)
	public void updateWoerterbuch(Roman r) throws ZuordnungIncompleteException
	{
		if(wa==null)
			return;
		
		wa.updateWoerterbuch(r);
	}
}
