/** 
 * @file        Artikel.h
 * @synopsis    Deklaration Klasse Artikel. 
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#ifndef ARTIKEL_H
#define ARTIKEL_H

#include "Exception.h"
#include "Daten.h"
#include "Artikelliste.h"

#include <sstream>
#include <map>
#include <iostream>
#include <iomanip>
#include <ios>
using namespace std;

class Artikel
{
    public:
        Artikel ();
        virtual ~Artikel ();

		double getEinzelpreis () const      { return mEinzelpreis; }
        Artikelliste::Artikeltyp getArtikeltyp ()	const   { return mTyp; }

        Artikel* factory () const;
        virtual void init ();       /// virtual, fuer dyn. Bindung
        friend ostream& operator<< (ostream& out, Artikel& x); 

    protected:
        double mEinzelpreis;
        Artikelliste::Artikeltyp mTyp;
        virtual ostream& display (ostream& out) { return out; }
};

inline ostream& operator<< (ostream& out, Artikel& x)
{
    return x.display(out);
}
#endif
