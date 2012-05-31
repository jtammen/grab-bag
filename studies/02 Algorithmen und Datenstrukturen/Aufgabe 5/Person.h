/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 *
 *        Filename:  Person.h
 *
 *     Description:  Deklaration & Definition der Person-Klasse
 *
 *         Version:  1.0
 *         Created:  14.01.2005
 *        Revision:  none
 *        Compiler:  g++
 *
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 *
 * =====================================================================================
 */

#ifndef  PERSON_H
#define  PERSON_H

#include <iostream>

class Person
{
    public:
        friend ostream& operator<<(ostream& s, const Person& x);
        friend istream& operator>>(istream& s, Person& x);
        bool operator<(const Person& x) const; // Kleiner-Op.
        bool operator>(const Person& x) const; // Groesser-Op.
        string getFamilienname(void) const;

    private:
        string vorname;
        string familienname;
        string geburtsdatum;
};

string Person::getFamilienname(void) const
{
    return this->familienname;
}

// Ueberladen des Kleiner-Operators
bool Person::operator<(const Person& y) const
{
    return this->getFamilienname() < y.getFamilienname();
}

// Ueberladen des Groesser-Operators
bool Person::operator>(const Person& y) const
{
    return this->getFamilienname() > y.getFamilienname();
}

// Ueberladen des Ausgabeoperators:
ostream& operator<<(ostream& s, const Person& x)
{
    s << x.vorname << " " << x.familienname << " " << x.geburtsdatum;
    return s;
}

// Ueberladen des Eingabeoperators:
istream& operator>>(istream& s, Person& x)
{
    s >> x.vorname;
    s.ignore(1); // Ignoriere " "
    s >> x.familienname;
    s.ignore(1); // Ignoriere ", "
    s >> x.geburtsdatum;
    return s;
}

#endif   /* ----- #ifndef PERSON_H  ----- */
