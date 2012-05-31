/** 
 * @file        main.cpp
 * @synopsis    Hauptprogramm Uebungsaufgabe 1, OOP
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @date        2005-03-21
 */

#include <iostream>
#include <stdlib.h>

#include "Langzahl.h"
#include "MyDouble.h"
#include "MyNumber.h"

double const pi = 3.1415926535897932384626433832795028841972; 

using namespace std;

int main ()
{
    int numIterations = 0;
    
    cout << "Anzahl der Iterationen eingeben: ";
    cin  >> numIterations;
    cout << endl << "Starte Berechnung, bitte warten ... " << endl;
    cout << endl << string(80, '-') << endl;
    
    // {{{ Test mit MyDouble-Klasse
    /// Reihenentwicklung: 1 / n(n+1) = 1
    MyDouble sum;
    for (int n = 1; n <= numIterations; ++n)
    {
        MyDouble nenner( n*(n+1.0) );
        sum += nenner.getKehrwert();
    }
    
    /// Reihenentwicklung: 1 / 2*n - 1 = pi/4
    MyDouble sum2;
    for (int n = 1; n <= numIterations; ++n)
    {
        MyDouble nenner( 2.0*n - 1.0 );

        if (n%2 != 0) sum2 += nenner.getKehrwert();
        else          sum2 -= nenner.getKehrwert();
    }
    // }}}
    
    // ------------------------------------------------------------- //
    
    // {{{ Test mit MyNumber-Klasse
    /// Reihenentwicklung: 1 / n(n+1) = 1
    MyNumber* sum3 = new MyNumber(string("0.0"));
    for (int n = 1; n <= numIterations; ++n)
    {
        MyNumber* nenner = new MyNumber( n*(n+1) );

        *sum3 += nenner->getKehrwert();
        delete nenner;
    }
    
    /// Reihenentwicklung: 1 / 2*n - 1 = pi/4
    MyNumber* sum4 = new MyNumber(string("0.0"));
    for (int n = 1; n <= numIterations; ++n)
    {
        MyNumber* nenner = new MyNumber( 2*n - 1 );
        
        if (n%2 != 0) *sum4 += nenner->getKehrwert();
        else          *sum4 -= nenner->getKehrwert();

        delete nenner;
    }
    // }}}

    cout << "Ergebnisse der Reihenentwicklung (Iterationen: " << numIterations << ")" << endl
         << "  - Reihe (1) [\\sum_{k=1}^{\\infty}{\\frac{1}{k(k+1)}} = 1]" << endl
         << "    - unter Verwendung der MyDouble-Klasse: " << sum << endl
         << "    - unter Verwendung der MyNumber-Klasse: " << endl 
         << "      " << *sum3 << endl
         << endl
         << "  - Reihe (2) [\\sum_{k=1}^{\\infty}{(-1)^{k+1} \\frac{1}{2k - 1}} = \\frac{\\pi}{4}]" << endl
         << "    - unter Verwendung der MyDouble-Klasse: " << sum2 << endl
         << "    - unter Verwendung der MyNumber-Klasse: " << endl 
         << "      " << *sum4 << endl
         << string(80, '-') << endl;

    system ("pause");
    return 0;
}
