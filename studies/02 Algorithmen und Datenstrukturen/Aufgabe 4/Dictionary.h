/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 *
 *        Filename:  Dictionary.h
 *
 *     Description:  Definition der Dictionary-Klasse
 *
 *         Version:  1.0
 *         Created:  01.12.2004
 *        Revision:  none
 *        Compiler:  gcc
 *
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 *
 * =====================================================================================
 */

#ifndef  DICTIONARY_H
#define  DICTIONARY_H
#include <string>
#include <vector>
using namespace std;

class Dictionary
{
    public:
        Dictionary(unsigned int n);                         // Konstruktor
        ~Dictionary(void);                                  // Destruktor

        bool insert(const string& dt, const string& engl);  // Wortpaar einfuegen
        bool lookup(const string& dt, string& engl);        // Deutsch -> Englisch nachschlagen
        bool del(const string& dt);                         // Wortpaar entfernen

        double probingLength(int s);                        // Laufzeitmessungen, s = 0 | 1
        void printList(void);                               // Ausgabe der Wortpaare

    private:
        unsigned int tableSize;
        unsigned int numWortpaare;

        struct Wortpaar
        {
            string dt;
            string engl;

            bool leer, geloescht;
        };

        // Berechnet Hash-Wert des Strings
        int calculateHash(const char* k) const;
        int probe(const int j) const;
        int findByKey(const string&) const;

        Wortpaar* hashTable;
};

#endif   /* ----- #ifndef DICTIONARY_H  ----- */
