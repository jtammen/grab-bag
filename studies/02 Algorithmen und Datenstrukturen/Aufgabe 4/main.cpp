/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 *
 *        Filename:  main.cpp
 *
 *     Description:  Hauptprogramm Aufgabe 4 (Klasse Dictionary)
 *
 *         Version:  1.0
 *         Created:  01.12.2004
 *        Revision:  none
 *        Compiler:  gcc
 *
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 *
 * =====================================================================================
 */

#include <iostream>
#include <fstream>
using namespace std;
#include "Dictionary.h"

int main()
{
    Dictionary* dict = new Dictionary(32173);
    string buffer, de, engl;

    // Wortpaarliste oeffnen und in Dictionary speichern
    ifstream fin("dtengl.txt");

    while (!fin.eof())
    {
        if (!de.empty() && !engl.empty())
        {
            dict->insert(de, engl);
            //cout << de << " / " << engl << endl;
        }

        fin >> de >> engl;
    }
    fin.close();

    string zu_loeschen = "buchstabieren";
    if (dict->del(zu_loeschen))
    {
        cout << zu_loeschen << " wurde erfolgreich geloescht." << endl;
    }

    string suchwort, ergebnis;

    cout << "Nach welchem deutschen Wort soll gesucht werden? ";
    while (cin >> suchwort && suchwort != "q")
    {
        if (dict->lookup(suchwort, ergebnis))
        {
            cout << "'" << suchwort << "' auf Englisch: '" << ergebnis << "'" << endl;
        }
        else
        {
            cout << "'" << suchwort << "' konnte im Woerterbuch nicht gefunden werden!" << endl;
        }

        cout << endl << "Nach welchem deutschen Wort soll gesucht werden? ";
    }

    cout << "Durchschnittliche Sondierungsfolge bei nicht-erfolgreicher Suche: " << dict->probingLength(0) << endl;
    cout << "Durchschnittliche Sondierungsfolge bei nicht-erfolgreicher Suche: " << dict->probingLength(1) << endl;
    //dict->printList();
    return 0;
}
