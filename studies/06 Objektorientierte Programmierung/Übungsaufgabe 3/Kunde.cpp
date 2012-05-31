/** 
 * @file        Kunde.cpp
 * @synopsis    Definition Klasse Kunde.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Kunde.h"

Kunde::Kunde () : mName("Name"), mAdresse("Str. Hsnr") {}

Kunde::~Kunde () {}

/// Daten einlesen
void Kunde::init ()
{
    //Daten& src = Daten::getInstance("beispiel.txt");
    Daten& src = Daten::getInstance();

    string strasse, hsnr;
    src.mSrcFilestream >> mName; 
    src.mSrcFilestream >> strasse; 
    src.mSrcFilestream >> hsnr;
    mAdresse = strasse + " " + hsnr;

    if (mName.empty() || mAdresse.empty()) 
        throw InitException("Konnte Daten fuer das Kunden-Objekt nicht einlesen");
}
