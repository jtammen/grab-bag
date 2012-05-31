// SiumulationMain.cpp
//
// Ereignisorientierte Simulation.
// Siehe ALDA-Projekt 1.
//
// O. Bittel; 9.10.2000


#include "Event.h"
#include "EventList.h"
#include "Queue.h"


#include <iostream>
#include <fstream>
#include <cstdlib>
#include <cstdio>

using namespace std;

int main()
{
	// Bedien-Schalter:
	const int nS = 3;				// Anzahl Bank-Schalter
	Queue<Event> bankSchalter[nS];	// Warteschlangen für Bank-Schalter

	// EreignisListe
	EventList eList;

	// kunden.dat
	ifstream fin("kunden.dat");

	Event e;

	// Hole aus Datei erstes Ankunftsereignis
	// und füge es in Ereignisliste ein:
	e.type = 'i';
	fin >> e.time;
	fin >> e.d;
	if (fin.eof())
		return 0;
	eList.insert(e);

    // Daten fuer Statistik
    int numKunden          = 0;
    float gesamtBedienzeit = 0.0;
    float minBedienzeit    = 10000.0;
    float maxBedienzeit    = 0.0;
    float gesamtWartezeit  = 0.0;
    float minWartezeit     = 10000.0;
    float maxWartezeit     = 0.0;

	while (eList.next(e))
	{
        // Verzoegern und Anzeige loeschen
        system("usleep 150000");
        system("clear");
        
		// Ausgabe des aktuellen Zeitpunktes
		cout << "Zeit:\t\t" << e.time << " Min" << endl;
        cout.flush();

		if (e.type == 'i')
		{
			//////
			// e ist Ankunftsereignis:
			//////

			// füge e in kürzeste Warteschlange ein:
			// Kürzeste Schlange min bestimmen
            int min = 0;
            for (int i = 0; i < nS; ++i)
            {
                if (bankSchalter[i].length() < bankSchalter[min].length())
                {
                    min = i;
                }
            }

			bankSchalter[min].add(e);

			// Falls e einziger Kunder (d.h. Warteschlangenlänge == 1),
			// dann wird er gleich bedient. Erzeuge daher neues
			// Abgangsereignis und füge es in Ereignisliste ein:
			if (bankSchalter[min].length() == 1)
			{
				Event eo;
				eo.type = 'o';
				eo.time = e.time + e.d;	// Zeitpunkt des Abgangs = Bedien-Ende
				eo.nrSchalter = min;
				eList.insert(eo);

                // Statistik aktualisieren
                minWartezeit = 0.0;     // minimal Wartezeit ist = 0, da Kunde allein am Schalter
			}

			// Hole aus Datei neues Ankunftsereignis
			// und füge es in Ereignisliste ein:
			Event ei;
			ei.type = 'i';
			fin >> ei.time;
			fin >> ei.d;
			if (!fin.eof())
			{
				eList.insert(ei);
            }

            cout << "Ereignis:\tKunde trifft ein und reiht sich an Schalter " << (min+1) << " ein." << endl;

            // Statistik aktualisieren
            if (maxBedienzeit < e.d)
            {
                maxBedienzeit = e.d;    // e.d = Bedienzeit des akt. Events
            }

            if (minBedienzeit > e.d)
            {
                minBedienzeit = e.d;
            }

            gesamtBedienzeit += e.d;
		}
		else // e.type == 'o'
		{
			//////
			// e ist Abgangsereignis:
			//////

			// Kunde von Bankschalter entfernen:
			bankSchalter[e.nrSchalter].remove();

			// erzeuge für nächsten Kunden am Bankschalter neues
			// Abgangsereignis und füge es in Ereignisliste ein:
			if (bankSchalter[e.nrSchalter].length() > 0)
			{
				Event eo;
				bankSchalter[e.nrSchalter].front(eo);  // nächster Kunde
                float aktuelleWartezeit = float(e.time-eo.time);    // Wartezeit

				// Abgangsereignis erzeugen:
				eo.type = 'o';
				eo.time = e.time + eo.d;	// Zeitpunkt des Abgangs = Bedien-Ende
				eo.nrSchalter = e.nrSchalter;
				eList.insert(eo);

                // Statistik aktualisieren
                if (maxWartezeit < aktuelleWartezeit)
                {
                    maxWartezeit = aktuelleWartezeit;
                }

                if (minWartezeit > aktuelleWartezeit)
                {
                    minWartezeit = aktuelleWartezeit;
                }

                gesamtWartezeit += aktuelleWartezeit;
            }

			cout << "Ereignis:\tKunde Nr. " << numKunden++ << " wurde bedient und verlaesst Schalter " << e.nrSchalter+1 << endl;
		}


		for (int i = 0; i < nS; ++i)
		{
		    cout << "Schalter " << i+1 << ":\t" << string(bankSchalter[i].length(), '*') << endl;
		}

	} // end while

    // Ausgabe der Statistiken
    system("clear");
    cout << "Ergebnisse der Simulation" << endl;
    cout << string(80, '=') << endl;  
    cout << "Anzahl der Kunden:\t\t" << numKunden << endl << endl;
    
    cout << "Min. Bedienzeit:\t\t" << minBedienzeit << endl;
    cout << "Max. Bedienzeit:\t\t" << maxBedienzeit << endl;
    cout << "Durchschnittl. Bedienzeit:\t" << "(" << gesamtBedienzeit << "/" << numKunden << ") == " << float(gesamtBedienzeit/numKunden) << endl;
    cout << string(80, '-') << endl;

    cout << "Min. Wartezeit:\t\t\t" << minWartezeit << endl;
    cout << "Max. Wartezeit:\t\t\t" << maxWartezeit << endl;
    cout << "Durchschnittl. Wartezeit:\t" << "(" << gesamtWartezeit << "/" << numKunden << ") == " << float(gesamtWartezeit/numKunden) << endl;
    cout << string(80, '-') << endl;


    // Statistik in Datei schreiben
    ofstream outfile ("statistik_ergebnis.txt");
    outfile << "Ergebnisse der Simulation" << endl;
    outfile << string(80, '=') << endl;
    outfile << "Anzahl der Kunden:\t\t" << numKunden << endl << endl;

    outfile << "Min. Bedienzeit:\t\t" << minBedienzeit << endl;
    outfile << "Max. Bedienzeit:\t\t" << maxBedienzeit << endl;
    outfile << "Durchschnittl. Bedienzeit:\t" << "(" << gesamtBedienzeit << "/" << numKunden << ") == " << float(gesamtBedienzeit/numKunden) << endl;
    outfile << string(80, '-') << endl;

    outfile << "Min. Wartezeit:\t\t\t" << minWartezeit << endl;
    outfile << "Max. Wartezeit:\t\t\t" << maxWartezeit << endl;
    outfile << "Durchschnittl. Wartezeit:\t" << "(" << gesamtWartezeit << "/" << numKunden << ") == " << float(gesamtWartezeit/numKunden) << endl;
    outfile << string(80, '-') << endl;
    outfile.close();

	return 0;
}
