/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  teil3.h
 * 
 *     Description:  Funktionsdefinitionen Aufgabenblatt 3, Aufgabe 3
 * 
 *         Version:  1.0
 *         Created:  25.11.2004 10:00:24 CET
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 * 
 * =====================================================================================
 */

using namespace std;
//struct Weg{};

int sucheWege(int aktStadt, int zielStadt, int benachbart[anzStaedte][anzStaedte], int gesamtStrecke, int faden[anzStaedte], list<int> aktuellerWeg)
{
    if (aktStadt != zielStadt)
    {
        int anzahlWege = 0;

        // Alle erreichbaren Staedte ueberpruefen 
		for (int i = 0; i < anzStaedte; i++)
		{
            // Direktverbindung gefunden
			if (benachbart[aktStadt][i] >= 0 && faden[i] != 1)
            {
                int entfernung = benachbart[aktStadt][i];
                aktuellerWeg.push_front(i);
                
                // Verbindung entfernen
                benachbart[aktStadt][i] = -1;
                benachbart[i][aktStadt] = -1;
                faden[aktStadt] = 1;
              
				anzahlWege += sucheWege(i, zielStadt, benachbart, gesamtStrecke+entfernung, faden, aktuellerWeg);

                // Schritt zuruecknehmen
                benachbart[aktStadt][i] = entfernung;
                benachbart[i][aktStadt] = entfernung;
                faden[aktStadt] = 0;
                aktuellerWeg.remove(i);
			}
        }

        return anzahlWege;
    } 
    else
    {
        cout << "** Angekommen in Stadt Nr. " << zielStadt << "! Zurueckgelegte Strecke: " << gesamtStrecke << " **" << endl << endl;
        cout.flush();

        while (aktuellerWeg.size() > 0)
        {
            cout << aktuellerWeg.back();
            if (aktuellerWeg.size() > 1)
            {
                cout << " -> ";
            }
            aktuellerWeg.pop_back();
        }
        cout << endl;
        return 1;
    }
}
