//
// FachNote.cpp
//
// Programmiertechnik Uebungsaufgabe 5: Klasse FachNote
//
// Autor: Jan Tammen
// Erstellt am: 29.05.2004
//

#include <cstring>
#include <iostream>
#include "FachNote.h"

// Default-Konstruktor, Datenkomponenten initialisieren
FachNote::FachNote (const char *fachPtr, int ganze, int zehntel) 
{
    bool fachValid = false;
    bool noteValid = false;

    const char *faecherListe[] = {"Analysis1", "Diskr.Math", "Fach.Engl.", "Dig.Techn.1", "Grundl.BWL", "Rech.Wes.1", "PROG"};
    for (int unsigned i = 0; i < (sizeof faecherListe / sizeof(char*)); ++i) {
        if (std::strcmp(fachPtr, faecherListe[i]) == 0) {
            fachValid = true;
            break;
        }
    }

    switch (ganze) {
        case 1:
        case 2:
        case 3:
            if (zehntel == 0 || zehntel == 3 || zehntel == 7) {
                noteValid = true;
            }
            break;
        case 4:
        case 5:
            if (zehntel == 0) {
                noteValid = true;
            }
            break;
        default:
            noteValid = false;
    }

    if (fachValid && noteValid) {
        std::strcpy(this->fach_, fachPtr);
        this->ganze_   = ganze;
        this->zehntel_ = zehntel;
        this->naechsteFachNotePtr_ = 0;
    } else {
        throw FalscheNote();
    }
}

// Destruktor
FachNote::~FachNote () { }

// Fachnote auf Korrektheit ueberpruefen
/*
bool FachNote::istRichtig() const
{
    bool fachValid = false;
    bool noteValid = false;
    
    const char *faecherListe[] = {"Analysis1", "Diskr.Math", "Fach.Engl.", "Dig.Techn.1", "Grundl.BWL", "Rech.Wes.1", "PROG"};
    for (int unsigned i = 0; i < (sizeof faecherListe / sizeof(char*)); ++i) {
        if (std::strcmp(this->fach_, faecherListe[i]) == 0) {
            fachValid = true;
        }
    }

    switch (this->ganze_) {
        case 1:
        case 2:
        case 3:
            if (this->zehntel_ == 0 || this->zehntel_ == 3 || this->zehntel_ == 7) {
                noteValid = true;
            }
            break;
        case 4:
        case 5:
            if (this->zehntel_ == 0) {
                noteValid = true;
            }
            break;
        default:
            noteValid = false;
    }
    
    return (fachValid && noteValid);
}
*/

// Fachnote "in Worten" zurueckgeben
const char *FachNote::inWorten () const
{
    switch (this->ganze_) {
        case 1:
            if (this->zehntel_ <= 5) {
                return "sehr gut";
            } else {
                return "gut";
            }
            break;
        case 2:
            if (this->zehntel_ <= 5) {
                return "gut";
            } else {
                return "befriedigend";
            }
            break;
        case 3:
            if (this->zehntel_ <= 5) {
                return "befriedigend";
            } else {
                return "ausreichend";
            }
            break;
        case 4:
            if (this->zehntel_ <= 0) {
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

// Naechste Fachnote zurueckgeben
FachNote *FachNote::getNaechsteFachNote() const
{
    return this->naechsteFachNotePtr_;
}

// Naechste Fachnote setzen
void FachNote::setNaechsteFachNote (FachNote *fachNotePtr) 
{
    if (fachNotePtr != 0) {
        this->naechsteFachNotePtr_ = fachNotePtr;
    }

    return;
}

// "Fach"-Datenkomponente zurueckgeben
const char *FachNote::getFach () const
{
    return this->fach_;
}

// "Ganze"-Datenkomponenten zurueckgeben
int FachNote::getGanze () const
{
    return this->ganze_;
}

// "Zehntel"-Datenkomponenten zurueckgegen
int FachNote::getZehntel () const
{
    return this->zehntel_;
}
