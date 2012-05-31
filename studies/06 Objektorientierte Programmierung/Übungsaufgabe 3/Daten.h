/** 
 * @file        Daten.h
 * @synopsis    Deklaration Klasse Daten. Implementiert das 
 *              (MEYERS-)Singleton-Pattern
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#ifndef DATEN_H
#define DATEN_H

#include "Exception.h"

#include <iostream>
#include <fstream>
using namespace std;

class Daten
{
    public:
        static Daten& getInstance (const char* src = "");
        ~Daten ();

        ifstream mSrcFilestream;

    private:
        Daten (const char* src = "");
        Daten (const Daten&);
        Daten& operator= (Daten const &);

        //ifstream mSrcFilestream;
};
#endif
