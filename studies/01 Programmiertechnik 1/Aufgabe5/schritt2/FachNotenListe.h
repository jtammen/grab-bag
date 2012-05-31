//
// FachNotenListe.h
//
// Programmiertechnik Uebungsaufgabe 5: Klasse FachNotenListe
//
// Autor: H.Drachenfels
// Erstellt am: 24.05.2004
//

#ifndef FACHNOTENLISTE_H
#define FACHNOTENLISTE_H

#include "FachNote.h"

class FachNotenListe
{
public:
  FachNotenListe ();
  ~FachNotenListe ();

  void einfuegen (FachNote *fachNotePtr);
  FachNote *entfernen ();
  FachNote *getErsteFachNote () const;

private:
  FachNote *ersteFachNotePtr_;
};

#endif
