//
// FachNotenListe.h
//
// Programmiertechnik Uebungsaufgabe 5: Notenspiegel mit verketteter Liste
//
// Autor: Jan Tammen <jan.tammen@fh-konstanz.de>
// Erstellt am: 29.05.2004
//

#ifndef  FACHNOTENLISTE_H
#define  FACHNOTENLISTE_H

#include "FachNote.h"

//========================================================== globale Variable
extern FachNote *ersteFachNotePtr;

//====================================================== Funktions-Prototypen
void einfuegen (FachNote *fachNotePtr);
FachNote *entfernen ();

#endif
