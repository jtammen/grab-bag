//
// Aufgabe1.cpp
//
// Programmiertechnik Uebungsaufgabe 1:
// Lernprogramm fuer Hexadezimalzahlen.
//
// Autor(en): Jan Tammen <jan.tammen@fh-konstanz.de>
// erstellt am: 29.03.2004
//

#include <iostream>
#include <cstdlib>
#include <ctime>

int main ()
{
  //----------------------------------------------- Aufgabenstellung ausgeben
  std::cout << "Dezimaldarstellung der Hexadezimalzahl eingeben "
               "(Strg-D fuer Ende):\n";

  //---------------------------------- Zufallszahlengenerator initialisieren
  std::srand(std::time(0));

  //------------------------------------------ Variablen fuer die Auswertung
  /* 1: Nach dieser Zeile Variablen definieren */

    int cnt_wrong    = 0;
    int cnt_correct  = 0;
    int cnt_try      = 1;

  while (true)
  {
    //------------------------------------------------------ Aufgabe stellen
    int ausgabe = std::rand() % 100;  // Zufallszahl zwischen 0 und 99

    std::cout << "hexadezimal ";
    std::cout << std::hex; // Ausgabeformat auf hexadezimal umstellen
    std::cout << ausgabe;
    std::cout << std::dec; // Ausgabeformat auf dezimal zurueckstellen
    std::cout << " = dezimal ";

    //-------------------------------------------------------- Eingabe lesen
    int eingabe;
    if (! (std::cin >> eingabe)) break; // break beendet die Schleife

    //------------------------------------------------------ Eingabe pruefen
    /* 2: Nach dieser Zeile Anweisungen zum Pruefen der Eingabe schreiben */

        std::cout << "=====================\n";
        std::cout << "Versuch Nr. " << cnt_try << '\n';
        if (ausgabe == eingabe) {
            cnt_correct++;
            std::cout << "Richtig!\n"; 
        } else {
            if (cnt_try < 2) {
                cnt_try++;
                std::cout << "Falsch! Bitte erneut versuchen: \n"; 
            } else {
                cnt_wrong++;
                std::cout << "Falsch! (Richtig: " << ausgabe << ")\n";
                cnt_try = 1;
            }
        }
        std::cout << "====================\n\n";
  }

  //------------------------------------------------------------- Auswertung
  /* 3: Nach dieser Zeile Anweisungen fuer die Auswertung schreiben */
    
    std::cout << std::dec;
    std::cout << '\n' << cnt_correct << " richtige Eingabe(n)\n";
    std::cout << cnt_wrong   << " falsche Eingabe(n)\n";

    if (cnt_correct > cnt_wrong) {
        std::cout << "Bestanden!\n";
    } else {
        std::cout << "Nicht bestanden!\n";
    }

}

