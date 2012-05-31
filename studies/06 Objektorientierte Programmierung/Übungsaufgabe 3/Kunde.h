/** 
 * @file        Kunde.h
 * @synopsis    Deklaration Klasse Kunde
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#ifndef KUNDE_H
#define KUNDE_H

#include "Exception.h"
#include "Daten.h"

#include <iostream>
#include <string>
#include <sstream>
using namespace std;

class Kunde
{
    public:
        Kunde ();
        ~Kunde ();

		string getName () const     { return mName; }
		string getAdresse () const  { return mAdresse; }

        void init ();
        friend ostream& operator<< (ostream&, const Kunde&);
    
    private:
		string mName;
		string mAdresse;
};

inline ostream& operator<< (ostream& out, const Kunde& x)
{
	out << x.mName << " " << x.mAdresse;
	return out;
}
#endif
