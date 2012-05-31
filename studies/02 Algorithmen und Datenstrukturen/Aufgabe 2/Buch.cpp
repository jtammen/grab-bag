/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  Buch.cpp
 * 
 *     Description:  Buch-Klasse Implementierung
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

#include "Buch.h"
#include <iostream>

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Buch
 *      Method:  Buch
 * Description:  Default-Konstruktor, initialisiert die Member-Variablen
 *--------------------------------------------------------------------------------------
 */
Buch::Buch(std::string autor, std::string titel)
{
    this->autor     = autor;
    this->titel     = titel;
    this->oBenutzer = NULL;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Buch
 *      Method:  getTitel
 * Description:  Titel des Buches zurueckgeben
 *--------------------------------------------------------------------------------------
 */
std::string Buch::getTitel() const
{
    return this->titel;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Buch
 *      Method:  getAutor
 * Description:  Autor des Buches zurueckgeben
 *--------------------------------------------------------------------------------------
 */
std::string Buch::getAutor() const
{
    return this->autor;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Buch
 *      Method:  getBenutzer
 * Description:  Benutzer/Entleiher des Buches zurueckgeben
 *--------------------------------------------------------------------------------------
 */
Benutzer* Buch::getBenutzer() const
{
    return this->oBenutzer;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Buch
 *      Method:  setBenutzer
 * Description:  Benutzer/Entleiher des Buches setzen
 *--------------------------------------------------------------------------------------
 */
void Buch::setBenutzer(Benutzer* oNewBenutzer)
{
    this->oBenutzer = oNewBenutzer;
}

