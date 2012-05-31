/** 
 * @file        main.cpp
 * @synopsis    Hauptprogramm Uebungsaufgabe 4, OOP
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-06-09
 */

#include <iostream>
#include <string>
#include "Huffman.h"

using namespace std;

int main ()
{
    char auswahl = '0'; 
    Huffman huff;
    while (auswahl != 'q')
    {
        string destFilename;
        string srcFilename;
        
        switch (auswahl)
        {
            case '1':
                cout << "Bitte Dateinamen der zu codierenden Datei eingeben: ";
                cin >> srcFilename;
                cout << "Bitte Zieldatei eingeben: ";
                cin >> destFilename;  
                cout << endl;

                try {
                    huff.codiereDatei(srcFilename, destFilename);
                }
                catch (Exception& e)
                {
                    clog << e.getMessage() << endl;
                    break;
                }
                auswahl = 0;
                break;
            
            case '2':
                cout << "Bitte Dateinamen der codierten Datei eingeben: ";
                cin >> srcFilename;
                cout << "Bitte Zieldatei eingeben: ";
                cin >> destFilename;  
                cout << endl;

                try {
                    huff.decodiereDatei(srcFilename, destFilename);
                }
                catch (Exception& e)
                {
                    clog << e.getMessage() << endl;
                    break;
                }
                auswahl = 0;
                break;

            default:
                cout << string(76, '-') << endl
                     << "Bitte waehlen: "       << endl
                     << "[1]: Datei codieren"   << endl
                     << "[2]: Datei decodieren" << endl
                     << "[q]: Beenden"          << endl
                     << string(76, '-') << endl;
                cin >> auswahl;
        }
    }

    return 0;
}
