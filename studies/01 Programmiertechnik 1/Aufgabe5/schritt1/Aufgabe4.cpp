//
// Aufgabe4.cpp
//
// Programmiertechnik Uebungsaufgabe 5: Notenspiegel mit verketteter Liste
//
// Liest die Namen von Faechern mit den zugehoerigen Noten
// in eine verkettete Liste ein und
// gibt dann einen Notenspiegel im HTML-Format aus.
// Aufruf mit dem Namen des Studenten als Argument(e).
//
// Autor: Jan Tammen <jan.tammen@fh-konstanz.de>
// Erstellt am: 29.05.2004
//

#include <iostream>
#include <cstring>
#include "FachNote.h"
#include "FachNotenListe.h"

//============================================================= Hauptprogramm
int main (int argc, char *argv[])
{
    if (argc < 2) {
        std::cerr << "Name des Studenten als Argument angeben!\n";
        return 1;
    }

    //--------------------------------------------------- Notenspiegel einlesen
    std::cerr << "Fach und Note eingeben (Ende mit Strg-d):\n";

    for (;;) {
        // Speicherplatz fuer eine weitere Note besorgen:
        FachNote *fachNotePtr = new FachNote;
        char komma;

        // Fach und Note einlesen und pruefen:
        std::cin >> fachNotePtr->fach;
        std::cin >> fachNotePtr->ganze >> komma >> fachNotePtr->zehntel;

        if (std::cin.eof() || std::cin.bad()) {
            std::cerr << "Eingabeende\n";
            delete fachNotePtr;
            break;
        }

        // Eingabe ist fehlgeschlagen
        if (std::cin.fail()) {
            std::cerr << "Falsche Eingabe: ";
            delete fachNotePtr;
            
            std::cin.clear();
            char c;
            
            while (std::cin.get(c) && c != '\n' && c != ' ' && c != '\t') {
                std::cerr << c;
            }

            std::cerr << '\n';
            continue;
        }

        if (komma != ',' && komma != '.' || ! istRichtig(fachNotePtr)) {
            std::cerr << "Falsche Eingabe: "
                      << fachNotePtr->fach << ' '
                      << fachNotePtr->ganze << komma << fachNotePtr->zehntel
                      << '\n';
            delete fachNotePtr;
            continue;
        }
 
        // in Notenliste eintragen:
        einfuegen(fachNotePtr);
    }

    //------------------------------------ Notenspiegel im HTML-Format ausgeben
    std::cout <<
    "<HTML>\n"
    "<HEAD>\n"
    "<TITLE>Notenspiegel</TITLE>\n"
    "</HEAD>\n"
    "<BODY>\n"
    "<H1>Notenspiegel</H1><BR><HR>\n"
    "<P>\n"
    "<B>Student:</B><BR>\n";

    for (int i = 1; i < argc; ++i) {
        std::cout << argv[i] << '\n';
    }

    std::cout <<
    "</P>\n"
    "<P>\n"
    "<TABLE WIDTH=\"100%\" BORDER=\"1\">\n"
    "  <TR>\n"
    "    <TH ALIGN=\"LEFT\">Fach:</TH>\n"
    "    <TH ALIGN=\"LEFT\" COLSPAN=\"2\">Note:</TH>\n"
    "  </TR>\n";

    for (FachNote *p = ersteFachNotePtr;
         p != 0;
         p = p->naechsteFachNotePtr) {
        
        std::cout <<
      "  <TR>"
      "    <TD>" << p->fach << "</TD>"
      "    <TD>" << inWorten(p) << "</TD>"
      "    <TD>" << p->ganze << ',' << p->zehntel << "</TD>"
      "  </TR>\n";
    }

    std::cout <<
    "</TABLE>\n"
    "</P>\n"
    "<HR NOSHADE>\n"
    "</BODY>\n"
    "</HTML>\n";

    //---------------------------------------------------- Notenliste freigeben
    FachNote *fachNotePtr;

    while ((fachNotePtr = entfernen()) != 0) {
        delete fachNotePtr;
    }

    return 0;
} // main
