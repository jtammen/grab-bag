/** 
 * @file        Limo.h
 * @synopsis    Deklaration Klasse Limo
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#ifndef LIMO_H
#define LIMO_H

#include "Artikel.h"

#include <string>
using namespace std;

class Limo : public Artikel
{
    public:
        Limo () : mInhalt(0.0), mArt("undef") {}
        virtual ~Limo () {}

        virtual void init ();

	private:
		float mInhalt;
        string mArt;

    protected:
        virtual ostream& display (ostream& out);
};
#endif
