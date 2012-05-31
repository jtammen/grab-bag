//
// Aufgabe4.cpp
//
// Programmiertechnik Uebungsaufgabe 4: Notenspiegel mit verketteter Liste
//
// Liest die Namen von Faechern mit den zugehoerigen Noten
// in eine verkettete Liste ein und
// gibt dann einen Notenspiegel im HTML-Format aus.
// Aufruf mit dem Namen des Studenten als Argument(e).
//
// Autor: Jan Tammen <jan.tammen@fh-konstanz.de>
// Erstellt am: 16.05.2004
//

#include <iostream>
#include <cstring>
#include <ctime>

//=========================================================== Typ-Deklaration
struct FachNote
{
    FachNote *naechsteFachNotePtr; // Verkettung

    char fach[40];
    int ganze;
    int zehntel;
};

//====================================================== Funktions-Prototypen
bool istRichtig (const FachNote *fachNotePtr);
const char *inWorten (const FachNote *fachNotePtr);
std::tm *getCurrentDate ();
void einfuegen (FachNote *fachNotePtr);

FachNote *entfernen ();

//========================================================== globale Variable
FachNote *ersteFachNotePtr;

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

    tm *currentDate = getCurrentDate();
    
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
    "  <TR>\n"
    "    <TD COLSPAN=\"3\" STYLE=\"text-align: right; font-size: 76%; font-style: italic;\">Datum: " << std::asctime(currentDate) << "</TD>\n"
    "  </TR>\n"
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


//==================================================== Funktions-Definitionen
bool istRichtig (const FachNote *fachNotePtr)
{
    bool fachValid = false;
    bool noteValid = false;
    
    const char *faecherListe[7] = {"Analysis1", "Diskr.Math", "Fach.Engl.", "Dig.Techn.1", "Grundl.BWL", "Rech.Wes.1", "PROG"};

    for (int i = 0; i < 7; ++i) {
        if (std::strcmp(fachNotePtr->fach, faecherListe[i]) == 0) {
            fachValid = true;
        }
    }

    switch (fachNotePtr->ganze) {
        case 1:
        case 2:
        case 3:
            if (fachNotePtr->zehntel == 0 || fachNotePtr->zehntel == 3 ||fachNotePtr->zehntel == 7) {
                noteValid = true;
            }
            break;
        case 4:
        case 5:
            if (fachNotePtr->zehntel == 0) {
                noteValid = true;
            }
            break;
        default:
            noteValid = false;
    }
    
    if (fachValid && noteValid) {
        return true;
    }

    return false;
}


const char *inWorten (const FachNote *fachNotePtr)
{
    switch (fachNotePtr->ganze) {
        case 1:
            if (fachNotePtr->zehntel <= 5) {
                return "sehr gut";
            } else {
                return "gut";
            }
            break;
        case 2:
            if (fachNotePtr->zehntel <= 5) {
                return "gut";
            } else {
                return "befriedigend";
            }
            break;
        case 3:
            if (fachNotePtr->zehntel <= 5) {
                return "befriedigend";
            } else {
                return "ausreichend";
            }
            break;
        case 4:
            if (fachNotePtr->zehntel <= 0) {
                return "ausreichend";
            } else {
                return "nicht ausreichend";
            }
            break;
        default:
            return "nicht ausreichend";
    }

    return "Ungueltige Note";
}

std::tm *getCurrentDate()
{
    std::time_t rawtime;
    std::tm * timeinfo;

    std::time ( &rawtime );
    timeinfo = std::localtime ( &rawtime );

    return timeinfo;
}

// Fuegt die Fachnote in die verkettete Liste ein
void einfuegen (FachNote *fachNotePtr)
{
    // Element verketten
    fachNotePtr->naechsteFachNotePtr = ersteFachNotePtr;
    
    // Anfang der Liste auf neues Element setzen
    ersteFachNotePtr = fachNotePtr;
    
    return;
}

// Entfernt das erste Element der verketteten Liste
FachNote *entfernen ()
{
    // Zu loeschendes Element merken
    FachNote *deletedFachNotePtr = ersteFachNotePtr;
    
    // Listenanfang auf naechstes Element legen
    if (ersteFachNotePtr != 0) {
        ersteFachNotePtr = ersteFachNotePtr->naechsteFachNotePtr;
    }

    return deletedFachNotePtr;
}
