/** 
 * @file        Huffman.h
 * @synopsis    Deklaration Klasse Huffman. 
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-06-09
 */

#ifndef HUFFMAN_H
#define HUFFMAN_H
#include "Exception.h"
#include "Knoten.h"
#include "BitFileIO.h"

#include <string>
#include <iostream>
#include <fstream>
#include <vector>
#include <map>
#include <queue>
using namespace std;

class Huffman
{
    public:
        Huffman ();
        ~Huffman ();
        
        void codiereDatei   (const string& src, const string& dest);
        void decodiereDatei (const string& src, const string& dest);

    private:
        void zaehleZeichen (ifstream& filestream);
        void erstelleBaum ();
        void erstelleCodeTabelle ();
        void schreibeHaeufigkeiten (BitFileOut* out);
        void liesHaeufigkeiten (BitFileIn* srcFile); 
        void traversiereBaum (const Knoten* p, vector< int >& ts);
        void druckeStatistik () const;
        void loescheBaum (const Knoten* p);

        vector< int > zeichenHaeufigkeiten;
        map< char, string > codeTabelle;
        const Knoten* wurzelKnoten;
        long int cntBits;
        long int cntChars;
        int cntUniqueChars;
};
#endif
