//
// Notenspiegel.cpp
//
// Programmiertechnik Uebungsaufgabe 5: Klasse Notenspiegel
//
// Autor: H.Drachenfels
// Erstellt am: 25.05.2004
//

#include "Notenspiegel.h"
#include <iostream>

Notenspiegel::Notenspiegel (char **namePtr)
{
    namePtr_ = namePtr;
}

Notenspiegel::~Notenspiegel ()
{
  FachNote *fachNotePtr;
  while ((fachNotePtr = dieFachNoten_.entfernen()) != 0)
  {
    delete fachNotePtr;
  }
}

void Notenspiegel::einlesen ()
{
  std::cerr << "Fach und Note eingeben (Ende mit Strg-d):\n";

  for (;;)
  {
    //------------------------------------ Fach und Note einlesen und pruefen
    char fach[40];
    int ganze;
    int zehntel;
    char komma;

    std::cin >> fach;
    std::cin >> ganze >> komma >> zehntel;

    if (std::cin.eof() || std::cin.bad())
    {
      std::cerr << "Eingabeende\n";
      return;
    }

    if (std::cin.fail())
    {
      std::cerr << "Keine Zahl: ";
      std::cin.clear();

      char c;
      while (std::cin.get(c) && c != '\n' && c != ' ' && c != '\t')
      {
        std::cerr << c;
      }

      std::cerr << '\n';
      continue;
    }

    try 
    {
        FachNote *fachNotePtr = new FachNote(fach, ganze, zehntel);
        if (komma != ',' && komma != '.')
        {
          std::cerr << "Falsche Eingabe: "
                    << fach << ' ' << ganze << komma << zehntel
                    << '\n';
          delete fachNotePtr;
          continue;
        }

        //----------------------------------------------- in Notenliste eintragen
        dieFachNoten_.einfuegen(fachNotePtr);
    } 
    catch (FalscheNote) 
    {
        std::cout << "Falsche Eingabe, gefangen durch FalscheNote!\n";
    }
  }
}

void Notenspiegel::htmlAusgeben () const
{
  std::cout <<
    "<HTML>\n"
    "<HEAD>\n"
    "<TITLE>Notenspiegel</TITLE>\n"
    "</HEAD>\n"
    "<BODY>\n"
    "<H2>Notenspiegel<BR><HR></H2>\n"
    "<P>\n"
    "<B>Student:</B><BR>\n";

  for (char **p = namePtr_; *p != 0; p++)
  {
    std::cout << *p << '\n';
  }

  std::cout <<
    "</P>\n"
    "<P>\n"
    "<TABLE WIDTH=\"100%\">\n"
    "<TR><TH ALIGN=\"LEFT\">Fach:</TH><TH ALIGN=\"LEFT\">Note:</TH></TR>\n";

  for (FachNote *p = dieFachNoten_.getErsteFachNote();
       p != 0;
       p = p->getNaechsteFachNote())
  {
    std::cout <<
      "<TR>"
      "<TD>" << p->getFach() << "</TD>"
      "<TD>" << p->inWorten() << "</TD>"
      "<TD>" << p->getGanze() << ',' << p->getZehntel() << "</TD>"
      "</TR>\n";
  }

  std::cout <<
    "</TABLE>\n"
    "</P>\n"
    "<HR NOSHADE>\n"
    "</BODY>\n"
    "</HTML>\n";
}
