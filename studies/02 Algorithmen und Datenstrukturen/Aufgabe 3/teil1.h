/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  teil1.h
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

// prüft, ob x in a vorkommt. n ist die Anzahl der Elemente in a.
bool istElement(int x, int a[], int n)
{
    // Nicht gefunden
    if (n == 0)
    { 
        return false;
    }
    
    if (a[n-1] == x)
    {
        return true;
    } else {
        // Rekursiver Aufruf, "naechstes Element" pruefen
        return istElement(x, a, n-1);
    }
}

// liefert das größte Elemente aus a zurück. n ist die Anzahl der Elemente in a
double max(double a[], int n)
{
    // Beim letzten Element angekommen    
    if (n == 0)
    {
        return 0.0;
    }
    
    // Rek. Aufruf; Maximum des vorherigen Elements
    double previousMaximum = max(a, n-1);

    if (a[n-1] > previousMaximum)
    {
        return a[n-1];
    } else {
        return previousMaximum;
    }
}

// liefert den Mittelwert aller Elemente aus a zurück. n ist die Anzahl der Elemente in a.
double mittelwert(double a[], int n)
{
    // Beim letzten Element angekommen
    if (n == 0)
    {
        return 0.0;
    }

    double previousMittelwert = mittelwert(a, n-1);
    
    return (previousMittelwert*(n-1)+a[n])/(n);
            
}
