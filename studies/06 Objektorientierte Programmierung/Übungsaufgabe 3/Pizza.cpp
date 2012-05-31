/** 
 * @file        Pizza.cpp
 * @synopsis    Definition Klasse Pizza.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Pizza.h"

/// Daten einlesen
void Pizza::init ()
{
    mTyp = Artikelliste::PIZZA;
    Daten& src = Daten::getInstance();

    /// Daten einlesen
    src.mSrcFilestream >> mGroesse;
    src.mSrcFilestream >> mBelag1;
    src.mSrcFilestream >> mBelag2;

    if (mGroesse == ' ' || mBelag1.empty() || mBelag2.empty())
        throw InitException("Konnte Daten fuer Pizza-Objekt nicht einlesen");

    /// Einzelpreis einlesen
    string tmp; src.mSrcFilestream >> tmp; 
    istringstream sstemp(tmp); sstemp >> mEinzelpreis;

    if (mEinzelpreis < 0)
        throw InitException("Konnte Einzelpreis nicht einlesen");
}

/// Ausgabe
ostream& Pizza::display (ostream& out)
{
	out << setw(4)  << "Gr:" 
        << setw(2)  << mGroesse
        << setw(5)  << "mit" 
        << setw(10) << mBelag1 
        << setw(5)  << "und" 
        << setw(10) << mBelag2
        << setw(10) << right << mEinzelpreis;
    
	return out;
}
