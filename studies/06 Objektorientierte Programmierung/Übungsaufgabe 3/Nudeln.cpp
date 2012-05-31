/** 
 * @file        Nudeln.cpp
 * @synopsis    Definition Klasse Nudeln.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Nudeln.h"

/// Daten einlesen
void Nudeln::init ()
{
    mTyp = Artikelliste::NUDELN;
    Daten& src = Daten::getInstance();

    /// Daten einlesen
    src.mSrcFilestream >> mArt;
    src.mSrcFilestream >> mZubereitung;

    if (mArt.empty() || mZubereitung.empty()) 
        throw InitException("Konnte Daten fuer Nudeln-Objekt nicht einlesen");

    /// Einzelpreis einlesen
    string tmp; src.mSrcFilestream >> tmp; 
    istringstream sstemp(tmp); sstemp >> mEinzelpreis;

    if (mEinzelpreis < 0)
        throw InitException("Konnte Einzelpreis nicht einlesen");
}

/// Ausgabe
ostream& Nudeln::display (ostream& out)
{
	out << setw(5)  << "Typ:" 
        << setw(13) << mArt 
        << setw(5)  << "Var:"
        << setw(13) << mZubereitung
        << setw(10) << right << mEinzelpreis;
    
	return out;
}
