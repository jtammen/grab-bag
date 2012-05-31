//
// Aufgabe5.cpp
//
// Programmiertechnik Uebungsaufgabe 5: Notenspiegel objektorientiert
//
// Liest die Namen von Faechern mit den zugehoerigen Noten ein und
// gibt dann einen Notenspiegel im HTML-Format aus.
// Aufruf mit dem Namen des Studenten als Argument(e).
//
// Autor: H.Drachenfels
// Erstellt am: 24.05.2004
//

#include "Notenspiegel.h"
#include <iostream>

int main (int argc, char *argv[])
{
  if (argc < 2)
  {
    std::cerr << "Name des Studenten als Argument(e) angeben!\n";
    return 1;
  }

  Notenspiegel derNotenspiegel(&argv[1]);
  derNotenspiegel.einlesen();
  derNotenspiegel.htmlAusgeben();

  return 0;
}
