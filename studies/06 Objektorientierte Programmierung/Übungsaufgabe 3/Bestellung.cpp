/** 
 * @file        Bestellung.cpp
 * @synopsis    Definition Klasse Bestellung.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Bestellung.h"

Bestellung::Bestellung () : 
    mDateTime(), 
    mAnzahlArtikel(0),
    mGesamtpreis(0.0),
    mPositionen(0) {}

Bestellung::~Bestellung (void)
{
    vector< Position >::iterator it;

    /// Speicher freigeben
    for (it = mPositionen.begin(); it != mPositionen.end(); ++it)
        delete it;
}

/// Daten der Bestellung einlesen
void Bestellung::init ()
{
    Daten& src = Daten::getInstance();

    /// Datum & Uhrzeit einlesen
    src.mSrcFilestream >> mDateTime.datum;
    src.mSrcFilestream >> mDateTime.uhrzeit;

    if (mDateTime.datum.empty() || mDateTime.uhrzeit.empty()) 
        throw InitException("Konnte Datum und/oder Uhrzeit nicht einlesen");

    /// Position-Objekte erstellen und initialieren: -> loop im Sequenzdiagramm
    while (!src.mSrcFilestream.eof())
    {
        Position* pos = new Position();

        try 
        {
            pos->init();
        }
        catch (const InitException& e)
        {
            clog << "[EXCEPTION] " << e.getMessage() << endl;
            break;
        }
        catch (const FactoryException& e)
        {
            clog << "[EXCEPTION] " << e.getMessage() << endl;
            break;
        }
        catch (const int)
        {
            break;
        }

        addPosition(*pos);
    }
}

/// Position-Objekt in Array einfuegen
void Bestellung::addPosition (const Position& pos)
{
    mPositionen.push_back((pos));
    mGesamtpreis += pos.getZeilenpreis();
    mAnzahlArtikel++;
}

/// Alle Positionen anzeigen
void Bestellung::displayAllPos () const
{
    vector< Position >::const_iterator it;
    int i = 1;
    for (it = mPositionen.begin(); it != mPositionen.end(); ++it)
    {
        cout << setw(5) << left << i;   /// "Pos. i"
        (*it).display();
        ++i;
    }
}

/// Nur bestimmte Positionen anzeigen
void Bestellung::displayPosWithTyp (const int typ) const
{
    /// Bei unbekanntem Typ abbrechen
    if (Artikelliste::getInstance().getNameForTyp(typ) == "")
        throw UnknownArticleException("Unbekannter Artikeltyp");
    
    /// Ergebnis in neuem vector speichern
    vector< Position > tmpPosition;

    /// Alle Positionen, bei denen der Artikel nicht vom
    /// Typ 'typ' ist, _nicht_ in den neuen vector kopieren
    remove_copy_if (mPositionen.begin(), 
                    mPositionen.end(), 
                    back_inserter(tmpPosition), 
                    bind2nd(DifferentTyp(), typ));    
 
    /// Keine Positionen mit diesem Typen
    if (tmpPosition.size() == 0)
        throw 0;
    
    /// Ergebnis mit Iterator durchlaufen und ausgeben
    int i = 1;
    vector< Position >::const_iterator it;
    for (it = tmpPosition.begin(); it != tmpPosition.end(); ++it)
    {
        cout << setw(5) << left << i;   /// "Pos. i"
        (*it).display();
        ++i;
    }
}
