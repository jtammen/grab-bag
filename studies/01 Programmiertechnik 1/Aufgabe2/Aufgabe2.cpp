/***************************************************************************
 *            Aufgabe2.cpp
 *
 *  Sat Apr 10 13:49:42 2004
 *  Copyright  2004  Jan Tammen
 *  jan.tammen@fh-konstanz.de
 ****************************************************************************/

#include <iostream>

int main () 
{
    using namespace std;

    int number;
    int * cntNumber = new int[6];

    for (int i = 0; i < 6; i++) {
        cntNumber[i] = 0;
    }

    // Zahlen einlesen
    cout << "Ganze Zahlen zwischen 1 und 6 eingeben (Ende mit Strg-D):\n";

    while (cin >> number)
    {
        // Pruefung der Eingabe
        if (number <= 6 && number >= 1) {
            cntNumber[number-1]++;
//            cout << "Zähle Eingabe der Zahl " << number << " (Aktueller Stand: " << cntNumber[number] << ")\n";
        } else {
            cout << "Falsche Eingabe wird ignoriert: " << number << '\n';
        }
    }

    // Hoechsten/Niedrigsten Zaehlerstand ermitteln
    int cntNumberMax = 0;
    for (int i = 0; i < 6; i++) {
        if (cntNumber[i] > cntNumberMax) {
            cntNumberMax = cntNumber[i];
        }
    }
    // cout << "Hoechster Zaehlerstand: " << cntNumberMax << '\n';

    int cntNumberMin = cntNumberMax;
    for (int i = 0; i < 6; i++) {
        if (cntNumber[i] < cntNumberMin) {
            cntNumberMin = cntNumber[i];
        }
    }
    // cout << "Niedrigster Zaehlerstand: " << cntNumberMin << '\n';

    // Skalierungsfaktor errechnen
    int scaleFactor = (cntNumberMax/50)+1;

    // Histogramm ausgeben
    cout << "Histogramm: (Skalierungsfaktor: " << scaleFactor << ")\n";
 
    // Horizontale Ausgabe
    for (int i = 0; i < 6; i++) {
        int startValue = 0;             // Startwert fuer Zuschneidung auf cntNumberMin setzen
        int stepValue  = scaleFactor;   // Schrittweite fuer Skalierung auf scaleFactor setzen

        for (int j = startValue; j < cntNumber[i]; (j += stepValue)) {
            cout << i+1;
        }
        cout << " (" << cntNumber[i] << ")\n";
    }

    cout << '\n';

    delete [] cntNumber;
}
