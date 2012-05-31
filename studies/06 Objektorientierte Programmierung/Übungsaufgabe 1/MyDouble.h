/** 
 * @file        MyDouble.h
 * @synopsis    MyDobule-Klasse Deklaration
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @date        2005-03-21
 */

#ifndef MYDOUBLE_H
#define MYDOUBLE_H

#include <iostream>
#include "Langzahl.h"

class MyDouble : public virtual Langzahl
{
    public:
        /// Konstruktoren + Destruktor
        MyDouble (void);
        MyDouble (double number);                   
        ~MyDouble ();

        /// Ueberladene Operatoren
        MyDouble& operator=  (const MyDouble& z);
        MyDouble  operator+  (const MyDouble& z) const;
        MyDouble  operator-  (const MyDouble& z) const;
        MyDouble  operator*  (const MyDouble& z) const;        
        MyDouble& operator+= (const MyDouble& z);
        MyDouble& operator-= (const MyDouble& z);
        MyDouble& operator*= (const MyDouble& z);
        MyDouble  operator-  (void) const;
       
        /// Ausgabeoperator 
        friend std::ostream& operator<< (std::ostream& s, const MyDouble& z);
   
        /// Zugriffsmethoden  
        long double getKehrwert (void) const    { return (1.0/mNumber); }
        long double getNumber(void)    const    { return mNumber;       }
        void        setNumber(double n)         { mNumber = n;          }
    
    private:     
        long double mNumber;
};

#endif

/* vim: set expandtab tabstop=4 shiftwidth=4 softtabstop=4 foldmethod=marker: */
