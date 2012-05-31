/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =============================================================================
 * 
 *        Filename:  textcopy.cpp
 * 
 *     Description:  "textcopy"-Programm
 *                   Kopiert Textdatei zeilenweise in neue Textdatei
 *                   Parameter: [-q Quell-Datei] optional, sonst STDIN
 *                              [-z Ziel-Datei] optional, sonst STDOUT
 * 
 * 		     z.B.:
 *		     textcopy -q quelle.txt -z ziel.txt
 *		     cat quelle.txt < textcopy 1> ziel.txt		
 *
 *         Version:  1.0
 *         Created:  29.10.2004
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen (Matrikelnummer: 277143)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 * 
 *
 * Zeigerarithmetik:
 *   *(argv+i) == argv[i]
 *   (*(argv+i)+0) == argv[i][0]
 *   usw.
 * =============================================================================
 */

#include <iostream>
#include <string>
#include "textcopy.h"

const string MSG_SOURCE_NOT_READABLE = "Fehler: Quelle konnte nicht zum Lesen geoeffnet werden";
const string MSG_DESTINATION_NOT_READABLE = "Fehler: Ziel konnte nicht zum Schreiben geoeffnet werden";
const int readBufferSize = 512;

main (int argc, char** argv)
{
    bool argumentSourceGiven      = false;
    bool argumentDestinationGiven = false;
    string sourceFileName, destinationFileName;
    FILE* sourceStream            = stdin;
    FILE* destinationStream       = stdout;

    // Quell- und Zieldatei ermitteln
    for (int i = 1; i < argc; ++i)
    {
        // Quelldatei-Option gefunden
        if (strcmp(*(argv+i), "-q") == 0)
        {
            // Ist Argument auch gesetzt?
            if (*(argv+i+1) != NULL && *(*(argv+1+i)+0) != '-')
            {
                sourceFileName = *(argv+i+1);
            }
        }
        
        // Zieldatei-Option
        if (strcmp(*(argv+i), "-z") == 0)
        {
            // Ist Argument auch gesetzt?
            if (*(argv+i+1) != NULL && *(*(argv+1+i)+0) != '-')
            {
                destinationFileName = *(argv+i+1);
            }
        }    
    }

    // Falls Quell-Datei uebergeben, oeffnen
    if (!sourceFileName.empty()) {
        sourceStream = fopen(sourceFileName.c_str(), "r");
    }    
        
    // Konnte Quell-Stream nicht oeffnen
    if (sourceStream == NULL || sourceStream == false)
    {
        cerr << MSG_SOURCE_NOT_READABLE << endl;
        exit(1);
    }

    // Falls Ziel-Datei uebergeben, oeffnen
    if (!destinationFileName.empty()) {  
        destinationStream = fopen(destinationFileName.c_str(), "w");
    }    
    
    // Konnte Ziel-Stream nicht oeffnen
    if (destinationStream == NULL || destinationStream == false) {
        cerr << MSG_DESTINATION_NOT_READABLE << endl;
        exit(1);
    }

    // Quelle einlesen, in Ziel kopieren und dann schliessen
    char lineBuffer[readBufferSize];
    
    // Zeile in Puffer lesen
    while (fgets(lineBuffer, readBufferSize, sourceStream))
    {
        // Nun ermitteln wieviele Woerter diese Zeile hat
        int numWords = AnzWoerter(lineBuffer);
        
        // Speicher fuer Zeile besorgen und leeren
        char** zeilePtr = new char* [numWords];
        memset(zeilePtr, 0, numWords);        
        
        // Nun die Woerter speichern
        for (int i = 0; i < numWords; ++i)
        {
            int wordLength = LenWort(lineBuffer, i);
            
            // Speicher fuer Wort besorgen und leeren, +1 fuer Termination
            // *(zeilePtr+i) entspricht zeilePtr[i]
            *(zeilePtr+i) = new char[wordLength+1];
            memset(*(zeilePtr+i), 0, wordLength+1);
            
            // Wort in Speicher kopieren
            // char * strncpy (char * dest, const char * src, sizet_t num); 
            strncpy(*(zeilePtr+i), AdrWort(lineBuffer, i), wordLength);
        }
        
        // Zeile in Zieldatei schreiben
        Ausgabe(numWords, zeilePtr, destinationStream);
                                                   
        // Speicher loeschen
        for (int i = 0; i < numWords; ++i)
        {
            delete *(zeilePtr+i);
        }
        
        delete zeilePtr;
    }  

    fclose (sourceStream);
    fclose (destinationStream);

    return 0;
}
