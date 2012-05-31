/*
 * Created on 12.12.2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
// TODO: Beim Integrieren nach "stilanalyse" ge�ndert
// 2006-01-19, 22:30
package stilanalyse;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

import stilanalyse.WortanalyseResult;
import stilanalyse.Wortzuordnung;

/**
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WortanalyseResultImpl implements WortanalyseResult, Serializable {

	WortanalyseResultImpl() {
		wortHaeufigkeiten = new HashMap();
		wortartenHaeufigkeiten = new HashMap();
		wortzuordnungen = new HashMap();
	}

	/* (non-Javadoc)
	 * @see stilanalyse.WortanalyseResult#getAnzahlWoerter()
	 */
	public long getAnzahlWoerter() {
		return anzahlWoerter;
	}

	/* (non-Javadoc)
	 * @see stilanalyse.WortanalyseResult#getWortZuordnungen()
	 */
	public HashMap getWortZuordnungen() {
		return wortzuordnungen;
	}

	/* (non-Javadoc)
	 * @see stilanalyse.WortanalyseResult#getWortHaeufigkeiten()
	 */
	public HashMap getWortHaeufigkeiten() {
		return wortHaeufigkeiten;
	}

	/* (non-Javadoc)
	 * @see stilanalyse.WortanalyseResult#getWortartenHaeufigkeiten()
	 */
	public HashMap getWortartenHaeufigkeiten() {
		return wortartenHaeufigkeiten;
	}

	/* (non-Javadoc)
	 * @see stilanalyse.WortanalyseResult#updateHaeufigkeiten()
	 */
	public void updateHaeufigkeiten() {

		//Was soll das Ding machen? Wortartenhäufigkeit updaten, 
		//nachdem die neuen Wortzuordnungen gemacht worden sind

		Iterator schluessel = wortzuordnungen.keySet().iterator();

		while (schluessel.hasNext()) {
			String temp = (String) schluessel.next();

			Vector tempVec = (Vector) wortzuordnungen.get(temp);
			//jetzt haben wir die Worte zu jeder Wortart
			//und müssen noch nach der Häufigkeit des Wortes schauen
			//und entsprechend aufsummieren
			int neueHaufigkeit = 0;
			Iterator durchDenVector = tempVec.iterator();
			while (durchDenVector.hasNext()) {

				Integer wortHaufigkeit =
					(Integer) wortHaeufigkeiten.get(durchDenVector.next());
				if (wortHaufigkeit != null)
					neueHaufigkeit += wortHaufigkeit.intValue();
				//else eigentlich nen fehler hier
				//aber man könnte auch nen Wort mit Häufigkeit 1 einfügen und dann eben eins dazuzählen
			}
			//ok, nun schreiben wir die neue Wortartenhäufigkeiten in die Hashmap
			wortartenHaeufigkeiten.put(temp, new Integer(neueHaufigkeit));
		};

	}
	
	/* (non-Javadoc)
	 * @see stilanalyse.WortanalyseResult#fuegeWortEin(java.lang.String)
	 */
	public boolean fuegeWortEin(String w) throws WortHashMapOverflowException {
		
		// Vorraussetzung WOrt schon in den WOrtzuordnungen vorhanden
		//sollte das nicht so sein, dann wird false zur�ckgegeben und 
		//WortanalyseImpl.fuegeWortinResultEin ruft fuegeNeuesWortEin auf
		if (wortHaeufigkeiten.containsKey(w)) {
			//erhoehe Worthaeufigkeit
			try {
				erhoeheAnzahlWort(w);
				//	nun wird noch die Gesamtanzahl der Woerter hochgezaehlt
				erhoeheAnzahlWoerter();
			} catch (WortNichtVorhandenException e) {
				throw new WortHashMapOverflowException();
			}
			//erhoehe Wortartenhaeufigkeit fuer jede Wortart

			Iterator myIt = wortzuordnungen.keySet().iterator();

			try {
				while (true) {

					String aktuelleWa = (String) myIt.next();
					Vector inhalt = (Vector) wortzuordnungen.get(aktuelleWa);

					if (inhalt.contains(w)) {
						//Wort in Wortart enthalten
						//d.h. Wortartenhaefigkeiten fuer Iteratorstand erhoehen
						try {
							erhoeheAnzahlWortart(aktuelleWa);
						} catch (WortartNichtVorhandenException e1) {
							//Das muesste eine Inkonsistenz sein
							//						neueWortart((String)wortZuordnung.get(i));
							e1.printStackTrace();
							throw new WortHashMapOverflowException();
						}
					}
				}
			} catch (NoSuchElementException e) {
				// nix vom Iteratior
			}

			return true;
		} else
			return false;
	}

	/* (non-Javadoc)
	 * @see stilanalyse.WortanalyseResult#fuegeNeuesWortEin(stilanalyse.Wortzuordnung)
	 */
	public void fuegeNeuesWortEin(Wortzuordnung wz)
		throws WortHashMapOverflowException {

		//1. Fuege Wort in Worthaeufigkeiten mit Haeufigkeit 1 ein
		wortHaeufigkeiten.put((String) wz.getWort(), new Integer(1));

		//2. Erhoehe mitgelieferte Wortarten bzw. erstelle diese.
		int bla = wz.getWortarten().size();
		for (int i = 0; i < wz.getWortarten().size(); i++) {
			try {
				erhoeheAnzahlWortart((String) wz.getWortarten().get(i));
			} catch (WortartNichtVorhandenException e1) {
				neueWortart((String) wz.getWortarten().get(i));
				//throw new WortHashMapOverflowException();
			}

			//4. handle wortZuordnungenReverse Map
			//mal gucken, ob die Wortart schon in der Map drin ist
			if (wortzuordnungen.containsKey(wz.getWortarten().get(i))) {
				//zugeordnete Woerter holen
				Vector dieWoerter =
					(Vector) wortzuordnungen.get(wz.getWortarten().get(i));

				if (!dieWoerter.contains(wz.getWort())) {
					//Ok, nu tun wir mal das Wort noch zuordnen
					dieWoerter.add(wz.getWort());
				}
			} else {
				//uih, jetzt ist noch nicht mal die Wortart vorhanden,
				//was werden wir wohl tun? Einfuegen werden wir tun....
				Vector dasWort = new Vector();
				dasWort.add(wz.getWort());
				wortzuordnungen.put(wz.getWortarten().get(i), dasWort);
			}
		//nun wird noch die Gesamtanzahl der Woerter hochgezaehlt
			erhoeheAnzahlWoerter();
		}
	}

	/* (non-Javadoc)
	 * @see stilanalyse.WortanalyseResult#erhoeheAnzahlWoerter()
	 */
	public void erhoeheAnzahlWoerter() {
		anzahlWoerter++;
	}

	/* (non-Javadoc)
	 * @see stilanalyse.WortanalyseResult#erhoeheAnzahlWortart(java.lang.String)
	 */
	public void erhoeheAnzahlWortart(String wa)
		throws WortartNichtVorhandenException {

		if (wortartenHaeufigkeiten.containsKey(wa)) {
			Integer newCount;
			newCount = (Integer) wortartenHaeufigkeiten.get(wa);
			newCount = new Integer(newCount.intValue() + 1);
			wortartenHaeufigkeiten.put(wa, newCount);
		} else
			throw new WortartNichtVorhandenException();
	}

	/* (non-Javadoc)
	 * @see stilanalyse.WortanalyseResult#erhoeheAnzahlWort(java.lang.String)
	 */
	public void erhoeheAnzahlWort(String w)
		throws WortNichtVorhandenException {

		if (wortHaeufigkeiten.containsKey(w)) {
			Integer newCount;
			newCount = (Integer) wortHaeufigkeiten.get(w);
			newCount = new Integer(newCount.intValue() + 1);
			wortHaeufigkeiten.put(w, newCount);
		} else
			throw new WortNichtVorhandenException();
	}

	private long anzahlWoerter;
	//Key = Wortart, Value = Vector mit Worten
	HashMap wortzuordnungen;
	HashMap wortHaeufigkeiten;
	HashMap wortartenHaeufigkeiten;

	private void neueWortart(String wa) {
		wortartenHaeufigkeiten.put(wa, new Integer(1));
	}

}
