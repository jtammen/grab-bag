//
// Aufgabe3.cpp
//
// Programmiertechnik Übungsaufgabe 3: Klausurnoten
//
// Liest Klausurnoten im Format ganze,zehntel oder ganze.zehntel ein und
// bestimmt die beste und die schlechteste Note sowie den Durchschnitt.
// Das Programm beruecksichtigt dabei nur die nach Pruefungsordnung
// zulaessigen Noten (1,0 1,3 1,7 2,0 2,3 2,7 3,0 3,3 3,7 4,0 5,0),
// andere Noten werden unter Ausgabe einer Warnung ignoriert.
//
// Das Programm gibt als Klausurergebnis folgende Werte aus:
// - die Anzahl der beruecksichtigten Noten
// - die beste Note
// - die schlechteste Note
// - den Durchschnitt
//
// Autor(en): Jan Tammen <jan.tammen@fh-konstanz.de>
// Erstellt am: 26.04.2004
//

#include <iostream>
#include <iomanip>

struct KlausurNote
{
    int ganze;    // [1..5]
    int zehntel;  // [0, 3, 7] fuer Noten 1 bis 3, nur 0 fuer Noten 4 und 5
};

int main () 
{
    //--------------------------------------------------------- Noten einlesen
    std::cout << "Noten im Format ganze,zehntel eingeben (Ende mit Strg-D):\n";

    KlausurNote note;
    char komma;
    KlausurNote besteNote        = {5, 0};
    KlausurNote schlechtesteNote = {1, 0};

    const int n = 6;    // Zeilenzahl:  6
    const int m = 8;    // Spaltenzahl: 8

    // Matrix fuer die Speicherung der Notenverteilung anlegen
    int **notenMatrix = new int*[n];
    for (int i = 0; i < n; ++i) {
        notenMatrix[i] = new int[m];
    }

    int summeNoten  = 0;
    int anzahlNoten = 0;

    while (std::cin >> note.ganze >> komma >> note.zehntel) {
        bool noteValid = false;
        //------------------------------------------------------ Eingabe pruefen
        /* 2: Direkt nach diesem Kommentar Anweisungen fuer das 
              Pruefen der eingegebenen Note schreiben */
        
        if (komma == ',' || komma == '.') {
            switch (note.ganze) {
                case 1:
                case 2:
                case 3:
                    if (note.zehntel == 0 || note.zehntel == 3 || note.zehntel == 7) {
                        noteValid = true;
                    }
                    break;
                case 4:
                case 5:
                    if (note.zehntel == 0) {
                        noteValid = true;
                    }
                    break;
                default: 
                        noteValid = false;
            }
        }

        if (noteValid == false) {
            std::cout << "Unzulaessige Note " << note.ganze << komma << note.zehntel << " wird ignoriert!\n";
        } else {
            summeNoten += 10*(note.ganze);
            summeNoten += note.zehntel;
            anzahlNoten++;

            // Aktuelle Note ist niedriger als die bisher beste Note
            if ((note.ganze < besteNote.ganze) ||
                (note.ganze == besteNote.ganze && note.zehntel < besteNote.zehntel)) {
                
                besteNote.ganze   = note.ganze;
                besteNote.zehntel = note.zehntel;
            }

            // Aktuelle Note ist hoeher als die bisher schlechteste Note
            if ((note.ganze > schlechtesteNote.ganze) ||
                (note.ganze == schlechtesteNote.ganze && note.zehntel > schlechtesteNote.zehntel)) {
                
                schlechtesteNote.ganze   = note.ganze;
                schlechtesteNote.zehntel = note.zehntel;
            }

            // "Zaehler" fuer aktuelle Note erhoehen
            notenMatrix[note.ganze][note.zehntel]++;
            std::cout << "Zaehle Note: " << note.ganze << "." << note.zehntel << " | Zaehlerstand: " << notenMatrix[note.ganze][note.zehntel] << "\n";
        }

//        std::cout << "anzahl noten: " << anzahlNoten << std::endl;
//        std::cout << "summe noten: " << summeNoten << std::endl;
    }

    if (!std::cin.eof()) {
        std::cout << "Ende wegen falscher Eingabe!\n";
    }

    //------------------------------------------------------ Ergebnis ausgeben
    std::cout << "\nAnzahl beruecksichtigter Noten: " << anzahlNoten << "\n";
    
    if (anzahlNoten > 0) {
        std::cout << "Beste Note: " << besteNote.ganze << "," << besteNote.zehntel << "\n";
        std::cout << "Schlechteste Note: " << schlechtesteNote.ganze << "," << schlechtesteNote.zehntel << "\n";
        std::cout << "Durchschnitt: " << (summeNoten/anzahlNoten)/10 << "," << (summeNoten/anzahlNoten)%10 << "\n";
    }

    // Histogramm ausgeben
    std::cout << "Histogramm: \n";

    // Horizontale Ausgabe
    for (int i = 1; i < 6; ++i) {
        for (int j = 0; j < 7; j++) {
            if (notenMatrix[i][j]) {
                std::cout << notenMatrix[i][j];
                std::cout << " (" << i << "," << j << ")\n";
            }
        }
    }

    std::cout << '\n';

    delete [] notenMatrix;
}
