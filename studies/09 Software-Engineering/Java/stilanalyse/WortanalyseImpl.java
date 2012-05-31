/*
 * Created on 12.12.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
// TODO: Beim Integrieren nach "stilanalyse" geändert
// 2006-01-19, 22:30
package stilanalyse;

import java.util.HashMap;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;
import stilanalyse.Roman;
import stilanalyse.Woerterbuch;
import stilanalyse.Wortanalyse;
import stilanalyse.WortanalyseResult;
import stilanalyse.Wortzuordnung;

/**
 * @author marco
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WortanalyseImpl implements Wortanalyse {

	/* (non-Javadoc)
	 * @see stilanalyse.Wortanalyse#fuehreAnalyseDurch(stilanalyse.Roman)
	 */
	private final int abkuerzungslaenge = 4;
	private final String sonstige = "Sonstige";
	private Woerterbuch wb;
	private Vector sonstigeWorte;

	public WortanalyseImpl(Woerterbuch wb_in) {
		wb = wb_in;
		sonstigeWorte = new Vector();
	}

	private void fuegeWortInResultEin(Roman r, Wortzuordnung wz)
		throws WortHashMapOverflowException {
		/*
		 * so die Funktion wurstelt mit den aufrufen von dem result die man ALLE
		 * braucht um nur ein Wort einzufuegen ^^
		 */
		if (!r.getWortanalyseResult().fuegeWortEin(wz.getWort())) {
			// wenn das wort nicht in der Hashmap vorhanden ist
			try {
				r.getWortanalyseResult().fuegeNeuesWortEin(wz);
			} catch (WortHashMapOverflowException e) {
				throw e; // leitet die Exception weiter
			}
catch (UngueltigeWortzuordnungException e) {}
		}

	}

	public void fuehreAnalyseDurch(Roman r)
		throws WortanalyseIncompleteException {
		//	###1. Prüfung auf Abkürzung
		//	###2. Keine Abkürzung
		//	###3. Kurzes Wort behandeln
		//	###4. Sonstige Worte
		
		// Get Text von Roman und entferne nichtbenötigte Zeichen
        String newString = r.getText().replaceAll("[\\W_&&[^\\x2E^ö^ü^ä^ß^-]]", " ");

        //nun noch alle Zahlen entfernen
        newString = newString.replaceAll("( \\d+)", " ");
        
		StringTokenizer tokenizer = new StringTokenizer(newString);
		// Get Wortanalyseresult (WAR) von Roman
		WortanalyseResult result = r.getWortanalyseResult();
		// Füge jedes Wort enthalten in Text in WAR ein
		try {
			while (true) {
				Wortzuordnung zuordnung = new WortzuordnungImpl();
				String currentWord = tokenizer.nextToken();
				//###1. Prüfung auf Abkürzung
				if (currentWord.endsWith(".")) {
					if (currentWord.length() <= abkuerzungslaenge + 1) {
						// special handling here, because of the blöde
						// Abkürzungen

						// Zuordnungstabelle zum currentWord erzeugen
						try {
							zuordnung = wb.gibWort(currentWord);
							try {
								fuegeWortInResultEin(r, zuordnung);
							} catch (WortHashMapOverflowException e) {
								e.printStackTrace();
								throw new WortanalyseIncompleteException();
							}
						} catch (WortNichtVorhandenException e1) {
							//### 2. Keine Abkürzung
							// Das Wort mit dem Punkt ist nicht vorhanden, 
							// somit keine Abkürzung

							//Punkt weg
							currentWord =
								currentWord.substring(
									0,
									currentWord.length() - 1);

							//###3. Kurzes Wort behandeln							
							try {
								zuordnung = wb.gibWort(currentWord);
								try {
									fuegeWortInResultEin(r, zuordnung);
								} catch (WortHashMapOverflowException e) {
									e.printStackTrace();
									throw new WortanalyseIncompleteException();
								}

							} catch (WortNichtVorhandenException e2) {
								zuordnung.setWort(currentWord);
								Vector wa = new Vector();
								wa.add(sonstige);
								// extra für Pete
								sonstigeWorte.add(currentWord);
								zuordnung.setWortarten(wa);
								try {
									fuegeWortInResultEin(r, zuordnung);
								} catch (WortHashMapOverflowException e) {
									e.printStackTrace();
									throw new WortanalyseIncompleteException();
								}
							}

						}

					}
					/* EDIT:	22.12.05 21:10
					 * By:		Alex Belizer
					 * Added:	Following "else" statement
					 */
					else { 
						// wenn mit punkt und groesser als 4 Zeichen
						//Punkt weg
						currentWord =
							currentWord.substring(
								0,
								currentWord.length() - 1);

						//###3. Kurzes Wort behandeln							
						try {
							zuordnung = wb.gibWort(currentWord);
							try {
								fuegeWortInResultEin(r, zuordnung);
							} catch (WortHashMapOverflowException e) {
								e.printStackTrace();
								throw new WortanalyseIncompleteException();
							}

						} catch (WortNichtVorhandenException e2) {
							zuordnung.setWort(currentWord);
							Vector wa = new Vector();
							wa.add(sonstige);
							// extra für Pete
							sonstigeWorte.add(currentWord);
							zuordnung.setWortarten(wa);
							//##CHANGE## by mwh - 12.01.06
							try {
								wb.fuegeEin(currentWord, sonstige);
							} catch (WortartNichtVorhandenException e1) {
								e1.printStackTrace();
							} catch (WortBereitsZugeordnetException e1) {
								//e1.printStackTrace();
							}
catch (LeeresWortException e) {}
							//##ENDCHANGE## by mwh - 12.01.06
							try {
								fuegeWortInResultEin(r, zuordnung);
							} catch (WortHashMapOverflowException e) {
								e.printStackTrace();
								throw new WortanalyseIncompleteException();
							}
						}
					}
					/* END OF EDIT:	22.12.05 21:10 */
				} //end if endsWith 
				else {
					// ###4. Sonstige Worte
					try {
						zuordnung = wb.gibWort(currentWord);
						try {
							fuegeWortInResultEin(r, zuordnung);
						} catch (WortHashMapOverflowException e) {
							e.printStackTrace();
							throw new WortanalyseIncompleteException();
						}

					} catch (WortNichtVorhandenException e2) {
						zuordnung.setWort(currentWord);
						Vector wa = new Vector();
						wa.add(sonstige);
						// extra für Pete
						sonstigeWorte.add(currentWord);
						zuordnung.setWortarten(wa);
						try {
							wb.fuegeEin(currentWord, sonstige);
						} catch (WortartNichtVorhandenException e1) {
							e1.printStackTrace();
						} catch (WortBereitsZugeordnetException e1) {
							//e1.printStackTrace();
						}
catch (LeeresWortException e) {}
						try {
							fuegeWortInResultEin(r, zuordnung);
						} catch (WortHashMapOverflowException e) {
							e.printStackTrace();
							throw new WortanalyseIncompleteException();
						}
					}
				}
			}

		} catch (NoSuchElementException e) {
			//tralala nix mehr da
		}
	}

	/* (non-Javadoc)
	 * @see stilanalyse.Wortanalyse#updateWoerterbuch()
	 */
	public void updateWoerterbuch(Roman r)
		throws ZuordnungIncompleteException {
			// Get Wortzuordnungen from Roman r
			WortanalyseResult wa = r.getWortanalyseResult();
			HashMap wortzuordnungen = (HashMap) wa.getWortZuordnungen();
			boolean wortZugeordnet = false;
			//insert all the new Wortzuordnungen, 
			//ergo all the words in the sonstigeWorte-Vector,
			//into the Wörterbuch

			for (int i = 0; i < (int) sonstigeWorte.size(); i++) {
				
				//##CHANGE## - by mwh - 12.01.06
				wortZugeordnet = false;
				Vector currentWa = new Vector();
					//(Vector) wortzuordnungen.get(sonstigeWorte.get(i));
					
				//durch alle Wortarten iterieren
				Iterator currentIt = wortzuordnungen.keySet().iterator();
				
				while(currentIt.hasNext()){
					String key = new String((String)currentIt.next().toString());
					if ( ((Vector)wortzuordnungen.get(key)).contains(sonstigeWorte.get(i)))
						currentWa.add(key);
				}
					
				if (currentWa.size() != 0) {
				//##ENDCHANGE## - by mwh - 12.01.06
					for (int b = 0; b < currentWa.size(); b++) {
						try {
							if (currentWa.get(b).toString() != "Sonstige"){
							
							wb.fuegeEin(
								sonstigeWorte.get(i).toString(),
								currentWa.get(b).toString());
							wortZugeordnet = true;
							}

						} catch (WortartNichtVorhandenException e) {
							//e.printStackTrace();
							// neue Wortart ins Wörterbuch einfügen
							// und dann Wort nochmals zuordnen
							try {
								wb.fuegeWortartEin(currentWa.get(b).toString());
								try {
									wb.fuegeEin(
										sonstigeWorte.get(i).toString(),
										currentWa.get(b).toString());
									wortZugeordnet = true;
								} catch (WortartNichtVorhandenException e2) {
									// ups, fehler, haben die Art ja gerade angelegt
									e2.printStackTrace();
								} catch (WortBereitsZugeordnetException e2) {
									//do nothing
								}
catch (LeeresWortException e2) {}

							} catch (WortartBereitsVorhandenException e1) {

								//e1.printStackTrace();
							}
						} catch (WortBereitsZugeordnetException e) {
							//e.printStackTrace();
						}
catch (LeeresWortException e) {}
					}
				} //end for b
				if (wortZugeordnet) {
					try {
						wb.loesche(sonstigeWorte.get(i).toString(), "Sonstige");
					} catch (WortNichtVorhandenException e) {
						//e.printStackTrace();
					} catch (WortartNichtVorhandenException e) {
						//e.printStackTrace();
					}
					sonstigeWorte.remove(i);
				}
			}
	}

}
