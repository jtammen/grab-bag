/** 
 * @file        Bier.h
 * @synopsis    Deklaration Klasse Bier
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#ifndef BIER_H
#define BIER_H

#include "Artikel.h"

#include <string>
using namespace std;

class Bier : public Artikel
{
    public:
        Bier () : mMarke("undef") {}
        virtual ~Bier () {}

        virtual void init ();

	private:
        string mMarke;

    protected:
        virtual ostream& display (ostream& out);
};
#endif
