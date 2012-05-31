/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  Bibliothek.h
 * 
 *     Description:  Definition der Bibliothek-Klasse
 * 
 *         Version:  1.0
 *         Created:  14.10.2004
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 * 
 * =====================================================================================
 */


#ifndef  BIBLIOTHEK_H
#define  BIBLIOTHEK_H

#include <string>
#include "Benutzer.h"
#include "Buch.h"

class Bibliothek
{
    public:
        Bibliothek(void);                                   // Default-Konstruktor
        ~Bibliothek(void);                                  // Destruktor

        bool insert(std::string name);                      // Benutzer einfuegen
        bool del(std::string name);                         // bzw. loeschen

        bool insert(std::string autor, std::string titel);  // Buch einfuegen
        bool del(std::string autor, std::string titel);     // bzw. loeschen
        
        void benutzer() const;                              // Ausgabe aller Benutzer
        void buecher() const;                               // Ausgabe aller Buecher

        void ausleihListe(std::string name) const;          // Ausleihen eines Benutzers ausgeben

        // Buch zu Ausleihen eines Benutzers hinzufuegen
        bool ausleihen(std::string name, std::string autor, std::string titel);
        
        // Buch aus Ausleihen eines Benutzers entfernen
        bool rueckgabe(std::string autor, std::string titel);

    private:
        BenutzerNode* benutzerListeHead;                    // Liste der Benutzer
        BuchNode*     buecherListeHead;                     // Liste der Buecher
        
        // Benutzer nach Name suchen
        Benutzer* sucheBenutzer(std::string name) const;
        
        // Benutzer nach Autor/Titel suchen
        Buch* sucheBuch(std::string autor, std::string titel) const;  
};

#endif   /* ----- #ifndef BIBLIOTHEK_H  ----- */
