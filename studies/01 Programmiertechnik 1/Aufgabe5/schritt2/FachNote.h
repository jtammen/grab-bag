//
// FachNote.h
//
// Programmiertechnik Uebungsaufgabe 5: Klasse FachNote
//
// Autor: H.Drachenfels
// Erstellt am: 24.05.2004
//

#ifndef FACHNOTE_H
#define FACHNOTE_H

#include "FalscheNote.h"

class FachNote
{
public:
    FachNote (const char *fachPtr, int ganze, int zehntel);
    ~FachNote ();

//  bool istRichtig () const;
    const char *inWorten () const;

    FachNote *getNaechsteFachNote () const;
    void setNaechsteFachNote (FachNote *fachNotePtr);
    const char *getFach () const;
    int getGanze () const;
    int getZehntel () const;

private:
    FachNote *naechsteFachNotePtr_;

    char fach_[40];
    int ganze_;
    int zehntel_;
};

#endif
