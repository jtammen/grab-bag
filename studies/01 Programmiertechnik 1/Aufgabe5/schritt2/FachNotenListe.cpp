//
// FachNotenListe.cpp
//
// Programmiertechnik Uebungsaufgabe 5: Klasse FachNotenListe
//
// Autor: Jan Tammen
// Erstellt am: 29.05.2004
//

#include "FachNotenListe.h"

// Default-Konstruktor, Datenkomponenten initialisieren
FachNotenListe::FachNotenListe ()
{
    this->ersteFachNotePtr_ = 0;
}

// Destruktor
FachNotenListe::~FachNotenListe () { }

// Fuegt die Fachnote in die verkettete Liste ein
void FachNotenListe::einfuegen (FachNote *fachNotePtr)
{
    // Element verketten
    fachNotePtr->setNaechsteFachNote(this->ersteFachNotePtr_);
     
    // Anfang der Liste auf neues Element setzen
    this->ersteFachNotePtr_ = fachNotePtr; 
    return;
}

// Entfernt das erste Element der verketteten Liste
FachNote *FachNotenListe::entfernen ()
{
    // Zu loeschendes Element merken
    FachNote *deletedFachNotePtr = this->ersteFachNotePtr_;
    
    // Listenanfang auf naechstes Element legen
    if (deletedFachNotePtr != 0) {
        this->ersteFachNotePtr_ = deletedFachNotePtr->getNaechsteFachNote();
    }

    return deletedFachNotePtr;
}

// Pointer auf erste Fachnote zurueckgeben
FachNote *FachNotenListe::getErsteFachNote () const
{
    return this->ersteFachNotePtr_;
}
