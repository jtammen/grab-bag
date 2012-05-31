/** 
 * @file        Eis.h
 * @synopsis    Deklaration Klasse Eis
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#ifndef EIS_H
#define EIS_H

#include "Artikel.h"
#include "Eiskugel.h"
#include <vector>

class Eis : public Artikel
{
    public:
        Eis () : mAnzahlKugeln(0) {}
        virtual ~Eis () {}

        virtual void init ();

    private:
		int mAnzahlKugeln;
        std::vector< Eiskugel > mKugeln;

    protected:
        virtual ostream& display (ostream& out);
};
#endif
