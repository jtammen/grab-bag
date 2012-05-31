/** 
 * @file        Daten.cpp
 * @synopsis    Definition Klasse Daten.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Daten.h"

/// Konstruktor: Datendatei-Strom oeffnen
Daten::Daten (const char* src)
{
    //mSrcFilestream(mSrcFilename, ios::in);
    mSrcFilestream.open(src, ios::in);
    if (!mSrcFilestream.good()) 
        throw FileNotReadableException("Datei nicht lesbar");
}

/// Konstruktor, Filestream schliessen
Daten::~Daten ()
{
    mSrcFilestream.close();
}

/// Instanz zurueckgeben
Daten& Daten::getInstance (const char* src)
{
    if (src == "") throw Exception("Fehlender Dateiname");
    
    static Daten uniqueInstance(src);
    return uniqueInstance;
}
