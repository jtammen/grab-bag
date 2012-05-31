/** 
 * @file        Pizza.h
 * @synopsis    Deklaration Klasse Pizza
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#ifndef PIZZA_H
#define PIZZA_H

#include "Artikel.h"
#include <string>
using namespace std;

class Pizza : public Artikel
{
    public:
        Pizza () : mGroesse('S'), mBelag1("undef"), mBelag2("undef") {}
        virtual ~Pizza () {}

        virtual void init ();

    private:
		char mGroesse;
        string mBelag1;
        string mBelag2;

    protected:
        virtual ostream& display (ostream& out);
};
#endif
