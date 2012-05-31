/** 
 * @file        Knoten.cpp
 * @synopsis    Definition Klasse Knoten.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-06-16
 */

#include "Knoten.h"

Knoten::Knoten () : 
    haeufigkeit(0) {}

Knoten::Knoten (int h) :
    haeufigkeit(h) {}

Knoten::~Knoten () {}

bool Knoten::istAussenKnoten () const 
{
    return (getKindLinks() == 0 && 
            getKindRechts() == 0);
}

AussenKnoten::AussenKnoten (char c, int h) : 
    Knoten(h),
    symbol(c) {}

InnenKnoten::InnenKnoten (const Knoten* cl, const Knoten* cr) :
    Knoten(cl->getHaeufigkeit() + cr->getHaeufigkeit()),
    kindLinks(cl),
    kindRechts(cr) {}    
