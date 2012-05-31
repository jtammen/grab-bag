/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  teil2.h
 * 
 *     Description:  Funktionsdefinitionen Aufgabenblatt 3
 * 
 *         Version:  1.0
 *         Created:  25.11.2004 10:00:24 CET
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 * 
 * =====================================================================================
 */

using namespace std;

/* a) Ueberprueft, ob uebergebenes Feld aufsteigend sortiert ist
 * b) Ausgabe der Aufrufstruktur (Tiefe und Parameter)
 * c) Ausgabe der Anzahl der Funktionsaufrufe
 *
 * Es finden maximal (n*2) - 1 Aufrufe statt. -> Wenn Feld sortiert ist
 * Iterativ: (n-1) Vergleiche
 *
 * Somit bringt die Funktion insgesamt keinen Geschwindigkeitsvorteil.
 *
 */
   
bool f(int a[], int l, int r) 
{
    static int rekTiefe = 0, funktionsAufrufe = 0;
    funktionsAufrufe++;

    // Einrueckung und Rekurstionstiefe + Parameter ausgeben
    cout << string(2*rekTiefe, ' ')
         << "Rekursionstiefe: " << rekTiefe++ << ", Aufruf Nr. " << funktionsAufrufe 
         << " | linker Index = " << l << ", rechter Index = " << r << endl;
    
    if (l >= r) 
        return true; 
    else 
    { 
        int m = (l+r)/2;
        return f(a,l,m) && f(a,m+1,r) && a[m] <= a[m+1];
    }
}
