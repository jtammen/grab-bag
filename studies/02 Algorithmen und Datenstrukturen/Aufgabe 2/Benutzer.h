/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  Benutzer.h
 * 
 *     Description:  Benutzer-Klasse
 * 
 *         Version:  1.0
 *         Created:  28.10.2004 10:37:36 CEST
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 * 
 * =====================================================================================
 */

#ifndef  BENUTZER_H
#define  BENUTZER_H

#include <string>
#include "Buch.h"
static const int maxAusleihen = 10;

class Benutzer
{
    public:
        Benutzer(std::string name);             // spez. Konstruktor

        bool ausleihen(Buch* oBuch);          // Ein Buch zur Ausleih-Liste hinzufuegen
        bool rueckgabe(Buch* oBuch);          // Ein Buch aus der Ausleih-Liste entfernen

        std::string getName();                  // Name holen
        void ausleihListe() const;              // Ausgabe der Ausleihliste
        
    private:
        std::string name;
        Buch*       aAusleihen[maxAusleihen];   // stat. Feld mit ausgeliehenen Buechern 
        int         countAusleihen;
};

struct BenutzerNode
{
    BenutzerNode* next;
    Benutzer*     oBenutzer;
};

#endif   /* ----- #ifndef BENUTZER_H  ----- */
