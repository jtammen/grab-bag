//
// FachNotenListe.cpp
//
// Programmiertechnik Uebungsaufgabe 5: Notenspiegel mit verketteter Liste
//
// Autor: Jan Tammen <jan.tammen@fh-konstanz.de>
// Erstellt am: 29.05.2004
//

#include "FachNote.h"
#include "FachNotenListe.h"

// Globale Variable
FachNote *ersteFachNotePtr;

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
