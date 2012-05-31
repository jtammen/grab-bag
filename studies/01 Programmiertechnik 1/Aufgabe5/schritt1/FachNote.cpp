//
// FachNote.cpp
//
// Programmiertechnik Uebungsaufgabe 5: Notenspiegel mit verketteter Liste
//
// Autor: Jan Tammen <jan.tammen@fh-konstanz.de>
// Erstellt am: 29.05.2004
//

#include <cstring>
#include "FachNote.h"

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
    
    return (fachValid && noteValid);
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
