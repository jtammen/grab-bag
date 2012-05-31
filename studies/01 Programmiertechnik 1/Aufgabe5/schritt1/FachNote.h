//
// FachNote.h
//
// Programmiertechnik Uebungsaufgabe 5: Notenspiegel mit verketteter Liste
//
// Autor: Jan Tammen <jan.tammen@fh-konstanz.de>
// Erstellt am: 29.05.2004
//

#ifndef  FACHNOTE_H
#define  FACHNOTE_H

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

#endif
