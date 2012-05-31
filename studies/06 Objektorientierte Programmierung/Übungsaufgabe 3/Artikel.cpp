/** 
 * @file        Artikel.cpp
 * @synopsis    Definition Klasse Artikel.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Artikel.h"
#include "Pizza.h"
#include "Eis.h"
#include "Nudeln.h"
#include "Limo.h"
#include "Bier.h"

Artikel::Artikel () : 
    mEinzelpreis(0.0),
    mTyp(Artikelliste::NONE) {}

Artikel::~Artikel () {}

void Artikel::init ()
{
    Daten& src = Daten::getInstance();

    /// Typ des Artikels einlesen
    string sTyp;
    src.mSrcFilestream >> sTyp;
    for (string::iterator it = sTyp.begin(); it != sTyp.end(); it++)
        *it = tolower(*it);
    
    /// Artikeltyp des eingelesenen Strings bestimmen
    mTyp = Artikelliste::getInstance().getTypForName(sTyp);

    if (mTyp == Artikelliste::NONE)
        throw InitException("Konnte Artikeltyp nicht bestimmen");
}

/// Spezielle Artikel-Klasse erstellen
Artikel* Artikel::factory () const
{
    switch (mTyp)
    {
        case Artikelliste::PIZZA:
            return new Pizza();
        case Artikelliste::EIS:
            return new Eis();
        case Artikelliste::NUDELN:
            return new Nudeln();
        case Artikelliste::LIMO:
            return new Limo();
        case Artikelliste::BIER:
            return new Bier();
        case Artikelliste::NONE:
        default:
            throw FactoryException("Artikel-Objekt kann nicht erstellt werden");
    }
}
