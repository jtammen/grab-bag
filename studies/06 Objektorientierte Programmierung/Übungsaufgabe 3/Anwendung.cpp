/** 
 * @file        Anwendung.cpp
 * @synopsis    Definition Klasse Anwendung.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-14
 */

#include "Anwendung.h"

Anwendung::Anwendung () : mKunde(), mBestellung()
{
    initDaten();
}

Anwendung::~Anwendung () {}

/// Name der Datendatei einlesen und an Singleton uebergeben
void Anwendung::initDaten() const
{
    string srcFile;
    cout << "Bitte Dateinamen der Datenquelle eingeben: ";
    cin >> srcFile;
    
    Daten::getInstance(srcFile.c_str());
}

/// Initialisieren - Daten der Bestellung einlesen
void Anwendung::init ()
{
    mKunde.init();
    mBestellung.init();
}

/// Eingelesene Best. ausgeben und Dialog starten
void Anwendung::run () const
{
    cout << endl << "Komplette Bestellung: " << endl;
    
    cout << "Kunde: " << mKunde
         << '\t' 
         << "Bestellung: " << mBestellung 
         << endl;
    cout << setw(5)  << left << "Pos."
         << setw(5)  << "Anz."
         << setw(8)  << "Name"
         << setw(36) << "Details"
         << setw(10) << right << "Einzelpr."
         << setw(10) << "Preis"
         << endl
         << string(74, '-')
         << endl;

    mBestellung.displayAllPos();        /// Alle Positionen ausgeben

    cout << string(74, '-') << endl;
    cout << setw(64) << "Gesamtpreis:"
         << setw(10) << right << mBestellung.getGesamtpreis()
         << endl << endl;


    /// Liste mit Filter ausgeben
    int typ = 0;
    cout << "Bitte gewuenschten Artikel-Typ auswaehlen [q = Beenden]" << endl;
    Artikelliste::getInstance().displayList();
    cout << "Auswahl: ";
    
    while (cin >> typ)
    {
        cout << endl;
        cout << "Kunde: " << mKunde
             << '\t' 
             << "Bestellung: " << mBestellung 
             << endl;
        cout << setw(5)  << left << "Pos."
             << setw(5)  << "Anz."
             << setw(8)  << "Name"
             << setw(36) << "Details"
             << setw(10) << right << "Einzelpr."
             << setw(10) << "Preis"
             << endl
             << string(74, '-')
             << endl;

        try
        {
            mBestellung.displayPosWithTyp(typ);
        }
        catch (const UnknownArticleException& e)
        {
            clog << e.getMessage() << endl; 
        }
        catch (const int)
        {
            cout << "Keine Artikel fuer diesen Typ!" << endl;
        }

        cout << string(74, '-') << endl;
        cout << endl << "Neue Auswahl: ";        
    }
}
