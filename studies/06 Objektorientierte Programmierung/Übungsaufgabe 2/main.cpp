/** 
 * @file        main.cpp
 * @synopsis    Hauptprogramm Uebungsaufgabe 2, OOP
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @date        2005-04-14
 */

#include <iostream>
#include <string>
#include "Exception.h"
#include "SourceCompressor.h"

using namespace std;

int main ()
{
    while (true)
    {
        string sSourcefile;
        cout << "Bitte Namen der Quelldatei eingeben: [q = Beenden] ";
        cin >> sSourcefile;

        if (sSourcefile == "q") break;

        try
        {
            double time1=0.0, tstart;
            tstart = clock();
            
            SourceCompressor* srcCmp = new SourceCompressor(sSourcefile);
        
            char cShowOnScreen;
            cout << "Soll das Ergebnis zusaetzlich auf dem Schirm ausgegeben werden? [j | n] ";
            cin >> cShowOnScreen;
            bool bShowOnScreen = (cShowOnScreen == 'j' || cShowOnScreen == 'J');

            if (bShowOnScreen) cout << endl << string(72, '-') << endl;
            
            srcCmp->setShowOnScreen(bShowOnScreen);
            srcCmp->compress();

            if (bShowOnScreen) cout << string(72, '-') << endl;

            time1 += clock() - tstart;
            time1 = time1/CLOCKS_PER_SEC;
            cout << "    (Zeit = " << time1 << " sec.)" << endl;
        }
        catch (const FileNotReadableException& e)
        {
            cerr << "[EXCEPTION] " << e.getMessage() << endl;
        }
        catch (const FileNotWriteableException& e)
        {
            cerr << "[EXCEPTION] " << e.getMessage() << endl;            
        }        
        catch (const Exception& e)
        {
            cerr << "[EXCEPTION] " << e.getMessage() << endl;            
        }

        cout << endl;
    }
    
    return 0;
}
