/** 
 * @file        Position.h
 * @synopsis    Deklaration Klasse Position
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#ifndef POSITION_H
#define POSITION_H

#include "Daten.h"
#include "Artikel.h"

#include <iostream>
using namespace std;

class Position
{
    public:
        Position ();
        ~Position ();

		int getAnzahl () const          { return mAnzahl;   }
		Artikel* getArtikel () const    { return mArtikel;  }

        double getZeilenpreis () const;
        Artikelliste::Artikeltyp getArtikeltyp () const { return mArtikel->getArtikeltyp(); }
        void init ();
        void display () const;
        
    private:
		int mAnzahl;
		Artikel* mArtikel;
};
#endif
