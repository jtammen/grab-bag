/*
 * =====================================================================================
 * 
 *        Filename:  main.cpp
 * 
 *     Description:  Hauptprogramm Aufgabe 2 (Klasse Bibliothek)
 * 
 *         Version:  1.0
 *         Created:  14.10.2004 10:43:31 CEST
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 * 
 * =====================================================================================
 */

#include "Bibliothek.h"
#include <iostream>

using namespace std;

void printLine(int length)
{
    cout << string(length, '-') << endl;
}

int main()
{
    /**************************************************************************/
    
    // Neues Bibliotheks-Objekt erstellen
    Bibliothek* myBibliothek = new Bibliothek();                

    // Benutzer einfuegen
    myBibliothek->insert(string("Jan Tammen"));            
    myBibliothek->insert(string("Hans Wurst"));
    myBibliothek->insert(string("Erwin Müller"));
    myBibliothek->insert(string("Uta Beispiel"));
    myBibliothek->insert(string("Siegfried Mayer"));
    myBibliothek->insert(string("Manfred Nachname"));
    
    printLine(80);
    
    // Alle Benutzer ausgeben
    myBibliothek->benutzer();                                   
    
    printLine(80);
    
    // Benutzer entfernen
    myBibliothek->del(string("Manfred Nachname"));         
    myBibliothek->del(string("Hermann Unbekannt"));
    myBibliothek->del(string("Erwin Müller"));
    
    printLine(80);
    
    // Alle Benutzer ausgeben
    myBibliothek->benutzer();
    
    printLine(80);
    
    // Ausleihliste ausgeben
    myBibliothek->ausleihListe(string("Siegfried Mayer")); 
    myBibliothek->ausleihListe(string("Hermann Unbekannt"));
    
    printLine(80);
    
    /**************************************************************************/
    
    // Buch einfuegen: Korrekter String    
    myBibliothek->insert(string("Goethe"), string("Faust"));
    myBibliothek->insert(string("Schiller"), string("Die Räuber"));
    myBibliothek->insert(string("Boyle"), string("Wassermusik"));
    myBibliothek->insert(string("Kafka"), string("Der Prozess"));
    myBibliothek->insert(string("Mann"), string("Die Buddenbrooks"));
    myBibliothek->insert(string("Brecht"), string("Der Clown"));
    myBibliothek->insert(string("Böll"), string("Gruppenbild mit Dame")); 
    
    printLine(80);
    
    // Alle Buecher ausgeben
    myBibliothek->buecher();
    
    printLine(80);
    
    /**************************************************************************/
    
    // Buch ausleihen und Ausleihliste ausgeben
    myBibliothek->ausleihen(string("Jan Tammen"), string("Goethe"), string("Faust"));
    myBibliothek->ausleihen(string("Jan Tammen"), string("Schiller"), string("Die Räuber"));
    myBibliothek->ausleihen(string("Jan Tammen"), string("Schiller"), string("Die Räuber"));
    myBibliothek->ausleihen(string("Jan Tammen"), string("Kafka"), string("Der Prozess"));
    myBibliothek->ausleihen(string("Jan Tammen"), string("Boyle"), string("Wassermusik"));
    myBibliothek->ausleihen(string("Jan Tammen"), string("Brecht"), string("Der Clown"));
    myBibliothek->ausleihen(string("Jan Tammen"), string("Böll"), string("Gruppenbild mit Dame"));
    
    printLine(80);
    
    myBibliothek->ausleihListe(string("Jan Tammen"));
    
    printLine(80);    
    
    // Buch zurueckgeben
    myBibliothek->rueckgabe(string("Kafka"), string("Der Prozess"));
    myBibliothek->rueckgabe(string("Schiller"), string("Die Räuber"));
    myBibliothek->rueckgabe(string("Boyle"), string("Wassermusik"));
    myBibliothek->rueckgabe(string("Schiller"), string("Die Räuber"));
    myBibliothek->rueckgabe(string("Brecht"), string("Der Clown"));
    myBibliothek->rueckgabe(string("Goethe"), string("Faust"));
    
    printLine(80);
    
    myBibliothek->ausleihListe(string("Jan Tammen"));
    
    printLine(80);            
    
    /**************************************************************************/
    
    // Buch entfernen    
    myBibliothek->del(string("Tammen"), string("Faust"));
    myBibliothek->del(string("Goethe"), string("Faust"));
    myBibliothek->del(string("Boyle"), string("Wassermusik"));
    myBibliothek->del(string("Kafka"), string("Der Prozess"));
    myBibliothek->del(string("Schiller"), string("Die Räuber"));
    
    printLine(80);
    
    // Alle Buecher ausgeben
    myBibliothek->buecher();   
    
    printLine(80);
}   
