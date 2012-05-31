//
// Notenspiegel.h
//
// Programmiertechnik Uebungsaufgabe 5: Klasse Notenspiegel
//
// Autor: H.Drachenfels
// Erstellt am: 24.05.2004
//

#ifndef NOTENSPIEGEL_H
#define NOTENSPIEGEL_H

#include "FachNotenListe.h"

class Notenspiegel
{
public:
    Notenspiegel (char **namePtr);
   ~Notenspiegel ();

    void einlesen ();
    void htmlAusgeben () const;

private:
    char **namePtr_;
    FachNotenListe dieFachNoten_;
};

#endif
