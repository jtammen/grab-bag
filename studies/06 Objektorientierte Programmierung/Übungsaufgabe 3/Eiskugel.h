/** 
 * @file        Eiskugel.h
 * @synopsis    Deklaration Klasse Eiskugel
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#ifndef EISKUGEL_H
#define EISKUGEL_H

#include "Exception.h"
#include "Daten.h"

#include <string>
using namespace std;

class Eiskugel
{
    public:
        Eiskugel () : mSorte("undef") {}
        ~Eiskugel () {}

        void init ();
        friend ostream& operator<< (ostream& out, const Eiskugel& x); 

    private:
        string mSorte;
};

inline ostream& operator<< (ostream& out, const Eiskugel& x)
{
	out << x.mSorte;
	return out;
}
#endif
