/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  Buch.h
 * 
 *     Description:  Buch-Klasse
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

#ifndef  BUCH_H
#define  BUCH_H

#include <string>
/*#include "Benutzer.h"*/

class Benutzer; // Geht nicht anders?!

class Buch
{
    public:
        Buch(void);                                 // Default-Konstruktor
        Buch(std::string autor, std::string titel); // spez. Konstruktor

        std::string getTitel() const;               // Titel holen
        std::string getAutor() const;               // Autor holen
        Benutzer* getBenutzer() const;              // Entleiher holen
        void setBenutzer(Benutzer* oNewBenutzer);   // Entleiher setzen


    private:
        std::string titel;
        std::string autor;
        Benutzer* oBenutzer;
};

struct BuchNode
{
    BuchNode* next;
    Buch*     oBuch;
};

#endif   /* ----- #ifndef BUCH_H  ----- */
