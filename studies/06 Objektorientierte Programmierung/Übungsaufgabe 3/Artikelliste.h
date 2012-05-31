/** 
 * @file        Artikelliste.h
 * @synopsis    Deklaration Klasse Artikelliste.Implementiert das 
 *              (MEYERS-)Singleton-Pattern
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#ifndef ARTIKELLISTE_H
#define ARTIKELLISTE_H

#include <iostream>
#include <list>
using namespace std;

class Artikelliste
{
    public:
        static Artikelliste& getInstance ();
        ~Artikelliste ();

        enum Artikeltyp {NONE, PIZZA, EIS, NUDELN, LIMO, BIER};
        typedef pair< string, Artikeltyp > artikelEintrag;

        void init ();
        
        Artikeltyp getTypForName (string name) const;
        string getNameForTyp (const int typ) const;
        void displayList () const;

    private:
        Artikelliste ();
        Artikelliste (const Artikelliste&);
        Artikelliste& operator= (Artikelliste const &);
        
        list< artikelEintrag > mListe;
};
#endif
