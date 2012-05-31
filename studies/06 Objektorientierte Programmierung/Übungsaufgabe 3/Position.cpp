/** 
 * @file        Position.cpp
 * @synopsis    Definition Klasse Position.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Position.h"

Position::Position () : mAnzahl(0) {}

Position::~Position () {}

/// Daten einlesen
void Position::init ()
{
    Daten& src = Daten::getInstance();

    /// Anzahl einlesen
    src.mSrcFilestream >> mAnzahl;

    if (mAnzahl < 0)
        throw InitException("Konnte Anzahl nicht einlesen");
    if (mAnzahl == 0)
        throw 0;

    /// Artikel erstellen und Daten einlesen
    Artikel* a = new Artikel();
    a->init();                      /// Artikel initialisieren...
    mArtikel = a->factory();        /// und konkrete Klasse erzeugen
    mArtikel->init();
}

/// Positionszeile ausgeben
void Position::display () const
{
    cout << left << setw(5) << mAnzahl
         << setw(8) << Artikelliste::getInstance().getNameForTyp(mArtikel->getArtikeltyp()) 
         << (*mArtikel)
         << setw(10) << fixed << right << setprecision(2) << getZeilenpreis() << endl;
}

/// Zeilenpreis errechnen
double Position::getZeilenpreis () const
{
    return (mAnzahl * (mArtikel->getEinzelpreis()) );
}
