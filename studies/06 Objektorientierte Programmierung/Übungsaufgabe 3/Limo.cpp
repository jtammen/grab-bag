/** 
 * @file        Limo.cpp
 * @synopsis    Definition Klasse Limo.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Limo.h"

/// Daten einlesen
void Limo::init ()
{
    mTyp = Artikelliste::LIMO;
    
    //Daten& src = Daten::getInstance("beispiel.txt");
    Daten& src = Daten::getInstance();

    /// Daten einlesen
    string tmpInhalt; src.mSrcFilestream >> tmpInhalt;
    istringstream sstmpInhalt(tmpInhalt); sstmpInhalt >> mInhalt;
    src.mSrcFilestream >> mArt;

    if (mArt.empty() || mArt.empty()) 
        throw InitException("Konnte Daten fuer Limo-Objekt nicht einlesen");

    /// Einzelpreis einlesen
    string tmp; src.mSrcFilestream >> tmp; 
    istringstream sstemp(tmp); sstemp >> mEinzelpreis;

    if (mEinzelpreis < 0)
        throw InitException("Konnte Einzelpreis nicht einlesen");
}

/// Ausgabe
ostream& Limo::display (ostream& out)
{
	out << setw(4)  << "Gr: " 
        << setw(6)  << mInhalt
        << setw(5)  << "Art:" 
        << setw(21) << mArt
        << setw(10) << right << mEinzelpreis;
    
	return out;
}
