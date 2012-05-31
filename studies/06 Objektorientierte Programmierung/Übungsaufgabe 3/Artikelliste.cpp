/** 
 * @file        Artikelliste.cpp
 * @synopsis    Definition Klasse Artikelliste.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Artikelliste.h"

Artikelliste::Artikelliste ()
{
    init();
}

Artikelliste::~Artikelliste () {}

/// Artikel in die Liste eintragen, evtl. extern auslesen
void Artikelliste::init ()
{
    mListe.push_back(artikelEintrag("pizza", PIZZA));
    mListe.push_back(artikelEintrag("eis", EIS));
    mListe.push_back(artikelEintrag("nudeln", NUDELN));
    mListe.push_back(artikelEintrag("limo", LIMO));
    mListe.push_back(artikelEintrag("bier", BIER));
}

/// Typ des Artikels zu einem Namen holen
Artikelliste::Artikeltyp Artikelliste::getTypForName (string name) const
{
    /// Mit Iterator ueber Liste laufen und Uebereinstimmung suchen
    list< artikelEintrag >::const_iterator it;
    for (it = mListe.begin(); it != mListe.end(); ++it)
    {
        if (name == it->first) return it->second;
    }
    
    return NONE;
}

/// Name des Artikels zu einem Typ holen
string Artikelliste::getNameForTyp (const int typ) const
{
    /// Mit Iterator ueber Liste laufen und Uebereinstimmung suchen
    list< artikelEintrag >::const_iterator it;
    for (it = mListe.begin(); it != mListe.end(); ++it)
    {
        if (typ == it->second)
        {
            string s(it->first); s[0] = toupper(s[0]);  /// Wortanfang gross
            return s;
        }
    }
    
    return "";
}

/// Instanz zurueckgeben
Artikelliste& Artikelliste::getInstance ()
{
    static Artikelliste uniqueInstance;
    return uniqueInstance;
}

/// Ausgabe
void Artikelliste::displayList () const
{
    /// Mit Iterator ueber Liste laufen und ausgeben
    list< artikelEintrag >::const_iterator it;
    for (it = mListe.begin(); it != mListe.end(); ++it)
    {
        string s(it->first); s[0] = toupper(s[0]);
        cout << it->second << ": " << s << endl;
    }    
}
