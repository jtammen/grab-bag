/** 
 * @file        Eis.cpp
 * @synopsis    Definition Klasse Eis.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Eis.h"

/// Daten einlesen
void Eis::init ()
{
    mTyp = Artikelliste::EIS;
    
    //Daten& src = Daten::getInstance("beispiel.txt");
    Daten& src = Daten::getInstance();

    /// Anzahl Kugeln einlesen
    src.mSrcFilestream >> mAnzahlKugeln;

    if ((int)mAnzahlKugeln == 0) 
        throw InitException("Konnte Daten fuer Eis-Objekt nicht einlesen");

    /// Eiskugel-Objekte erstellen und initialisieren
    for (int i = 0; i < mAnzahlKugeln; ++i)
    {
        Eiskugel* eiskugel = new Eiskugel();
        eiskugel->init();
        mKugeln.push_back((*eiskugel));
    }

    /// Einzelpreis einlesen
    string tmp; src.mSrcFilestream >> tmp; 
    istringstream sstemp(tmp); sstemp >> mEinzelpreis;

    if (mEinzelpreis < 0)
        throw InitException("Konnte Einzelpreis nicht einlesen");
}

/// Ausgabe
ostream& Eis::display (ostream& out)
{
	out << setw(4) << mAnzahlKugeln 
        << setw(8) << "Kugeln:";
    
    vector< Eiskugel >::const_iterator it;
    for (it = mKugeln.begin(); 
         it != mKugeln.end(); 
         ++it)
    {
        out << setw(int(24/mAnzahlKugeln)) << (*it);
    }
    
    out << setw(10) << right << mEinzelpreis;
    
	return out;
}
