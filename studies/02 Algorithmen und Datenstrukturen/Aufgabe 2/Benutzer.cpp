/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  Benutzer.cpp
 * 
 *     Description:  Benutzer-Klasse Implementierung
 * 
 *         Version:  1.0
 *         Created:  01.11.2004 13:34:29 Westeuropäische Normalzeit
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 * 
 * =====================================================================================
 */

#include "Benutzer.h"
#include <iostream>

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Benutzer
 *      Method:  Benutzer
 * Description:  Default-Konstruktor, initialisiert die Member-Variablen
 *--------------------------------------------------------------------------------------
 */
Benutzer::Benutzer(std::string name)
{
    this->name           = name;
    this->countAusleihen = 0;

    // Feld mit Ausleihen initialisieren
    for (int i = 0; i < maxAusleihen; ++i)
    {
        aAusleihen[i] = NULL;
    }
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Benutzer
 *      Method:  ausleihen
 * Description:  Buch zur Ausleihliste hinzufuegen, falls Ausleihlimit nicht erreicht
 *--------------------------------------------------------------------------------------
 */
bool Benutzer::ausleihen(Buch* oBuch)
{
    // Hat der Benutzer die max. Anzahl Ausleihen erreicht?
    if (this->countAusleihen == maxAusleihen)
    {
        std::cerr << "Ausleihe fehlgeschlagen, Benutzer hat Ausleihlimit (" << maxAusleihen << ") erreicht: " 
                  << this->name << std::endl;
        return false;
    }
    
    // Neues Buch in Feld einfuegen
    for (int i = 0; i < maxAusleihen; ++i)
    {
        // Leere Position gefunden
        if (this->aAusleihen[i] == NULL)
        {
            this->aAusleihen[i] = oBuch;
            oBuch->setBenutzer(this);
            this->countAusleihen++;
            break;
        }    
    }
    
    return true;    
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Benutzer
 *      Method:  rueckgabe
 * Description:  Buch aus Ausleihliste entfernen
 *--------------------------------------------------------------------------------------
 */
bool Benutzer::rueckgabe(Buch* oBuch)
{    
    // Ausleihliste durchlaufen
    for (int i = 0; i < maxAusleihen; ++i)
	{
	    // Zeigen beide auf das selbe Objekt?
		if (this->aAusleihen[i] == oBuch)
		{
		    // Buch aus Feld entfernen
			this->aAusleihen[i] = NULL;
			oBuch->setBenutzer(NULL);
			this->countAusleihen--;
			
			return true;
		}
	}

	std::cerr << "Rueckgabe fehlgeschlagen, Benutzer hat Buch nicht entliehen." << std::endl;
    return false;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Benutzer
 *      Method:  getName
 * Description:  Name des Benutezr zurueckgeben
 *--------------------------------------------------------------------------------------
 */
std::string Benutzer::getName()
{
    return this->name;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Benutzer
 *      Method:  ausleihListe
 * Description:  Ausleihliste des Benutzers ausgeben
 *--------------------------------------------------------------------------------------
 */
void Benutzer::ausleihListe() const
{
    if (this->countAusleihen == 0)
    {
        std::cout << "Dieser Benutzer hat keine Buecher entliehen." << std::endl;
        return;
    }
    
    // Buecher ausgeben
    for (int i = 0; i < maxAusleihen; ++i)
    {
        if (this->aAusleihen[i] != NULL)
        {
                std::cout << "#" << i+1 << ": " 
                          << this->aAusleihen[i]->getAutor() << ", " 
                          << this->aAusleihen[i]->getTitel() << std::endl;
        }    
    }
    
    return;
}
