/** 
 * @file        Knoten.h
 * @synopsis    Deklaration Klasse Knoten. 
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-06-16
 */

#ifndef KNOTEN_H
#define KNOTEN_H
#include "Exception.h"
#include <iostream>
using namespace std;

class Knoten
{
    public:
        Knoten ();
        Knoten (int h);
        virtual ~Knoten() = 0;
        
        int   getHaeufigkeit ()        const { return haeufigkeit;  }
        bool  istAussenKnoten ()       const;

        virtual const Knoten* getKindLinks () const = 0;
        virtual const Knoten* getKindRechts () const = 0;
        virtual char  getSymbol () const = 0;

    protected:
        int haeufigkeit;
};

class InnenKnoten : public Knoten
{
    public:
        InnenKnoten (const Knoten* cl, const Knoten* cr);
        
        virtual const Knoten* getKindLinks ()  const { return kindLinks;  }
        virtual const Knoten* getKindRechts () const { return kindRechts; }
        virtual char  getSymbol ()             const { return 0; }
    
    private:
        const Knoten* kindLinks;
        const Knoten* kindRechts;
};

class AussenKnoten : public Knoten
{
    public:
        AussenKnoten (char c, int h);

        virtual const Knoten* getKindLinks ()  const { return 0;  }
        virtual const Knoten* getKindRechts () const { return 0; }
        virtual char  getSymbol ()             const { return symbol; }
    
    private:
        char symbol;
};

/// Funktionsobjekt zum Vergleichen der Haeufigkeiten
struct VergleicheKnoten
{
    bool operator() (Knoten* x,
                     Knoten* y) const
    {
        return (x->getHaeufigkeit() > y->getHaeufigkeit());
    }
};
#endif
