/** 
 * @file        Eiskugel.cpp
 * @synopsis    Definition Klasse Eiskugel.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Eiskugel.h"

/// Daten einlesen
void Eiskugel::init ()
{
    //Daten& src = Daten::getInstance("beispiel.txt");
    Daten& src = Daten::getInstance();

    /// Sorte einlesen
    src.mSrcFilestream >> mSorte;

    if (mSorte.empty())
        throw InitException("Konnte Daten fuer Eiskugel-Objekt nicht einlesen");
}
