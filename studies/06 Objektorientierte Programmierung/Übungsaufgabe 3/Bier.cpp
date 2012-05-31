/** 
 * @file        Bier.cpp
 * @synopsis    Definition Klasse Bier.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Bier.h"

/// Daten einlesen
void Bier::init ()
{
    mTyp = Artikelliste::BIER;
    Daten& src = Daten::getInstance();

    /// Daten einlesen
    src.mSrcFilestream >> mMarke;

    if (mMarke.empty()) 
        throw InitException("Konnte Daten fuer Bier-Objekt nicht einlesen");

    /// Einzelpreis einlesen
    string tmp; src.mSrcFilestream >> tmp; 
    istringstream sstemp(tmp); sstemp >> mEinzelpreis;

    if (mEinzelpreis < 0)
        throw InitException("Konnte Einzelpreis nicht einlesen");
}

/// Ausgabe
ostream& Bier::display (ostream& out)
{
	out << setw(7)  << "Marke:" 
        << setw(29) << mMarke 
        << setw(10) << right << mEinzelpreis;
	return out;
}
