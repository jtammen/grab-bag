/** 
 * @file        Anwendung.h
 * @synopsis	Deklaration Klasse Anwendung
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#ifndef ANWENDUNG_H
#define ANWENDUNG_H

#include "Kunde.h"
#include "Bestellung.h"
#include "Exception.h"
#include <iostream>
using namespace std;

class Anwendung
{
    public:
        Anwendung ();
        ~Anwendung ();
		
		void init ();
        void initDaten() const;
        void run () const;

    private:
		Kunde mKunde;
		Bestellung mBestellung;
};
#endif
