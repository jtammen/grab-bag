/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  teil1.cpp
 * 
 *     Description:  Aufgabenblatt 3, Aufgabe 1
 * 
 *         Version:  1.0
 *         Created:  25.11.2004 09:59:02 CET
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen (jt), jan.tammen@fh-konstanz.de
 *         Company:  FH Konstanz
 * 
 * =====================================================================================
 */

#include <iostream>
#include "teil1.h"

using namespace std;

int main()
{
    int    n = 20;
    int    a[20];
    double b[20];
    int    x;

    // Test-Felder aufbauen
    for (int i = 0; i < n; ++i)
    {
        a[i] = (i*2);
        b[i] = double(i*2);

        cout << "Fuege Element ein: " << double(i*2) << endl;
    }

    cout << "Welche ganze Zahl soll ueberprueft werden: ";

    while (cin >> x)
    {
        
        if (istElement(x, a, n) == true)
        {
            cout << x << " ist Element des Feldes a." << endl;
        } else {
            cout << x << " ist NICHT Element des Feldes a." << endl;
        }
    }


    cout << "Suche grösstes Element in Feld b: ";
    cout << max(b, n) << endl;;
   
    cout << "Berechne Mittelwert des Feldes b: ";
    cout << mittelwert(b, n) << endl;
    
    return 0;
}
