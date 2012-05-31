/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =============================================================================
 * 
 *        Filename:  textcopy.h
 * 
 *     Description:  Funktionen fuer das "textcopy"-Programm
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
 * =============================================================================
 */

using namespace std;

// Anzahl der Woerter einer Zeile ermitteln
int AnzWoerter (char* zeile)
{
    int countWoerter = 0;
    
    //cout << "Bestimme Wortanzahl der Zeile: " << zeile;
    
    while (zeile != NULL)
    {        
        // Zeilen-Pointer auf naechstes Wort setzen 
        // (Woerter sind durch Leerzeichen getrennt)
        zeile = strstr(zeile, " ");
        countWoerter++;
        if (zeile != NULL)
        {
            zeile++;
        }
    }
    
    //cout << " ... Ergebnis: " << countWoerter << endl;
    return countWoerter;
}

// Adresse des n-ten Wortes einer Zeile ermitteln
char* AdrWort (char* zeile, int n)
{    
    for (int i = 0; i < n; ++i)
    {
        // Zum naechsten Wort springen
        zeile = strstr(zeile, " ");
        
        if (zeile != NULL)
        {
            zeile++;
        }
    }
    
    return zeile;
}

// Laenge des n-ten Wortes einer Zeile ermitteln
int LenWort (char* zeile, int n)
{
    int wordLength = 0;
    char * wordPtr = AdrWort(zeile, n);
    
    // Alle Zeichen bis zum Wortende bzw. Zeilenende durchlaufen
    while (wordPtr[wordLength] != ' ' &&
           wordPtr[wordLength] != '\n')
    {
        wordLength++;
    }    
    
    return wordLength;
}

// Zeile in Zieldatei schreiben
void Ausgabe (int argc, char** argv, FILE* ziel)
{   
    for (int i = 0; i < argc; ++i)
    {
        fputs(*(argv+i), ziel);
        
        // Am Ende der Zeile kein Leerzeichen schreiben
        if (argc > i+1)
        {
            fputs(" ", ziel);
        }    
    }
    
    // Kleiner Bug: Zeilenumbruch wird immer geschrieben, 
    // auch falls in der Quelldatei kein Zeilenumbruch am Ende der Datei war.
    fputs("\n", ziel);
}

