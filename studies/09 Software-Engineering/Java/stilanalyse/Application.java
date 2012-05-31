/*
 * 	Autor : Marco Fahr
 *  Date  : 12.12.2005
 *  Klasse MainFrameController -> Controllerklasse des Anwendungsfensters(MainFrame)
 * 
 */

package stilanalyse;


public class Application {

	public static int index = -1;					// Bugfix Marco wegen keinem Roman in der Verwaltung
	// TODO: Beim Integrieren nach oben korrigiert, da zeichenweise gezählt wird...
	public static final int UNTERESATZGRENZE = 20;
	public static final int OBERESATZGRENZE = 50;
	public static Romanverwaltung romanVerwaltung = new RomanVerwaltungImpl(); 
	public static WoerterbuchVerwaltung woerterVerwaltung = new WoerterbuchVerwaltungImpl();
	public static MainFrameView frame;
	// TODO: Beim Integrieren eingefügt, damit nach dem Anlegen eines Romans Analyse gestartet werden kann
    // 2006-01-19, 18:00
    public static Analyse analyse = new AnalyseImpl();
	
	public static void main(String[] args) {
		frame = new MainFrameView();
		frame.setVisible(true);
		SplashWindow3 sw = new SplashWindow3("bild2.png",null, 5000);  
	}

//	public static Roman getAktuellerRoman(){
		
//		return (Roman)  romanVerwaltung.getRomanListe().elementAt(getRomanIndex());
		
//	}

	public static int getRomanIndex() {
		return index;
	}
}
