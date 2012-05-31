/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 *
 *        Filename:  Complex.h
 *
 *     Description:  Deklaration & Definition der Complex-Klasse
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

#ifndef  COMPLEX_H
#define  COMPLEX_H

#include <math.h>

class Complex
{
    public:
        friend Complex plus(Complex x, Complex y); // befreundete Funktion
        Complex plus_m(Complex x); // Methode
        friend Complex operator+(Complex x, Complex y);
        friend Complex operator-(Complex x, Complex y);
        friend ostream& operator<<(ostream& s, const Complex& x);
        friend istream& operator>>(istream& s, Complex& x);
        bool operator<(const Complex& x) const; // Kleiner-Op.
        bool operator>(const Complex& x) const; // Groesser-Op.
        double getBetrag(void) const;

    private:
        double real;
        double im;
};

Complex plus(Complex x, Complex y)
{
    Complex z;
    z.real = x.real + y.real;
    z.im = x.im + y.im;
    return z;
}

Complex Complex::plus_m(Complex y)
{
    Complex z;
    z.real = real + y.real;
    z.im = im + y.im;
    return z;
}

double Complex::getBetrag(void) const
{
    return sqrt(real*real+im*im);
}

// Ueberladen des Kleiner-Operators
bool Complex::operator<(const Complex& y) const
{
    return this->getBetrag() < y.getBetrag();
}

// Ueberladen des Groesser-Operators
bool Complex::operator>(const Complex& y) const
{
    return this->getBetrag() > y.getBetrag();
}

// Ueberladen des Ausgabeoperators:
ostream& operator<<(ostream& s, const Complex& x)
{
    s << x.real << "+" << x.im << "*i (Betrag: " << x.getBetrag() << ")";
    return s;
}

// Ueberladen des Eingabeoperators:
istream& operator>>(istream& s, Complex& x)
{
    s >> x.real;
    s.ignore(1); // Ignoriere "+"
    s >> x.im;
    s.ignore(2); // Ignoriere "*i"
    return s;
}

#endif   /* ----- #ifndef COMPLEX_H  ----- */
