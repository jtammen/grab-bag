/** 
 * @file        Huffman.cpp
 * @synopsis    Deklaration Klassen BitFileOut und BitFileIn.
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-06-20
 */

#ifndef BITFILEIO_H
#define BITFILEIO_H

#include <iostream>
#include <fstream>
#include <sstream>
#include <iomanip>
#include "Exception.h"
using namespace std;

class BitFileOut
{
    public:
        BitFileOut (const string& fn);
        ~BitFileOut ();
        
        void writeBits (const string & str);
        int getLength ()        { return fp.tellp (); }
        const bool eof() const  { return fp.eof(); }

        static string decToBin (unsigned int n);
    
    private:
        ofstream fp;

        int obc;
        char och;
};

class BitFileIn
{
    public:
        BitFileIn (const string& fn);
        ~BitFileIn () { fp.close(); }
        
        int getBit ();
        int readBits (int n);
        int getLength ()        { return length; }
        const bool eof() const  { return fp.eof(); }
    
    private:
        ifstream fp;
        int length;

        int obc;
        unsigned char och;
        int numIgnore;
};
#endif
