/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 *
 *        Filename:  Dictionary.cpp
 *
 *     Description:  Deklaration der Dictionary-Klasse
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

#include "Dictionary.h"
#include <iostream>
#include <iomanip>

// Default-Konstruktor, leere Hash-Tabelle anlegen
Dictionary::Dictionary(unsigned int n)
{
    this->tableSize = n;
    this->hashTable = new Wortpaar[n];
    this->numWortpaare = 0;

    for (unsigned int i = 0; i < this->tableSize; ++i)
    {
        this->hashTable[i].leer      = true;
        this->hashTable[i].geloescht = false;
    }
}

// Destruktor, Hash-Tabelle loeschen
Dictionary::~Dictionary(void)
{
    delete [] this->hashTable;
    this->tableSize = 0;
}

// Wortpaar in Tabelle einfuegen. Gibt false zurueck, falls bereits vorhanden.
bool Dictionary::insert(const string& dt, const string& engl)
{
    if (dt.empty() || engl.empty())
    {
        return false;
    }

    // Dt. Wort ist bereits vorhanden
    if (this->findByKey(dt) != -1)
    {
        std::cout << "FEHLER. Wort '" << dt << "' ist bereits vorhanden!" << endl;
        return false;
    }

    int j = 0, newIndex;
    do
    {
        newIndex = (this->calculateHash(dt.c_str()) + this->probe(j)) % this->tableSize;
        j++;
    } while (this->hashTable[newIndex].leer      == false &&
             this->hashTable[newIndex].geloescht == false);

    Wortpaar wp;
    wp.dt        = dt;
    wp.engl      = engl;
    wp.leer      = false;
    wp.geloescht = false;
    this->hashTable[newIndex] = wp;
    //cout << "Fuege Wortpaar ein: " << dt << " / " << engl << endl;
    this->numWortpaare++;

    return true;
}

// Englisches Wort zu dt. Wort suchen
bool Dictionary::lookup(const string& dt, string& engl)
{
    int index = this->findByKey(dt);

    if (index == -1)
    {
        engl = "-";
        return false;
    }

    engl = this->hashTable[index].engl;
    return true;
}

// Wortpaar aus Tabelle entfernen
bool Dictionary::del(const string& dt)
{
    int index = this->findByKey(dt);

    if (index == -1)
    {
        return false;
    }

    this->hashTable[index].geloescht = true;
    this->hashTable[index].dt        = "";
    this->hashTable[index].engl      = "";
    this->numWortpaare--;

    return true;
}

// Durchschnittliche Länge der Sondierungsfolgen messen
double Dictionary::probingLength(int s)
{
    if (this->numWortpaare == 0)
    {
        return 0.0;
    }

    double result = 0.0;
    double sum = 0.0;

    switch (s)
    {
        //  Bei _nicht_ erfolgreicher Suche
        case 0:
            for (unsigned int i = 0; i < this->tableSize; ++i)
            {
                if (this->hashTable[i].geloescht == false &&
                    this->hashTable[i].leer      == false)
                {
                    int j = 0, index;
                    do
                    {
                        index = (this->calculateHash(this->hashTable[i].engl.c_str()) + this->probe(j)) % this->tableSize;
                        j++;
                    } while (this->hashTable[index].leer == false &&
                             this->hashTable[index].engl != this->hashTable[i].engl);

                    sum += j;
                }
            }

            result = double(sum/this->numWortpaare);

        break;

        // Bei erfolgreicher Suche
        case 1:
        default:
            for (unsigned int i = 0; i < this->tableSize; ++i)
            {

                if (this->hashTable[i].geloescht == false &&
                    this->hashTable[i].leer      == false)
                {
                    int j = 0, index;
                    do
                    {
                        index = (this->calculateHash(this->hashTable[i].dt.c_str()) + this->probe(j)) % this->tableSize;
                        j++;
                    } while (this->hashTable[index].leer == false &&
                             this->hashTable[index].dt != this->hashTable[i].dt);

                    sum += j;
                }
            }

            result = double(sum/this->numWortpaare);
    }

    return result;
}

// Ausgabe einer Liste mit allen Wortpaaren
void Dictionary::printList(void)
{
    for (unsigned int i = 0; i < this->tableSize; ++i)
    {
       if (this->hashTable[i].leer == false &&
            this->hashTable[i].geloescht == false) {
          std::cout << "[# " << i << "] Deutsch: " << this->hashTable[i].dt
                    << " / English: " << this->hashTable[i].engl << std::endl;
        }
    }

    cout << endl << "Anzahl der Eintraege: " << this->numWortpaare << endl;

    return;
}

/*************  Private Methoden *************/

// Index anhand des dt. Wortes ermitteln
int Dictionary::findByKey(const string& dt) const
{
    int j = 0, index;
    do
    {
        index = (this->calculateHash(dt.c_str()) + this->probe(j)) % this->tableSize;
        j++;
    } while (this->hashTable[index].leer == false &&
             this->hashTable[index].dt != dt);

    if (this->hashTable[index].leer)
    {
        return -1;
    }
    else
    {
        cout << "wort vorhanden: " << this->hashTable[index].dt << " / bei index: " << index << endl;
        return index;
    }
}

// Berechnet Hash-Wert des uebergebenen C-Strings
int Dictionary::calculateHash(const char* k) const
{
    int hash;

    for (hash = 0; *k != '\0'; k++)
    {
        hash = (hash*128 + *k) % this->tableSize;
    }

    return hash;
}

// Quadratisches Sondieren
int Dictionary::probe(const int j) const
{
    return (j*j);
}
