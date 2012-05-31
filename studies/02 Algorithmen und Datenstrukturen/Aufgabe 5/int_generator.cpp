/*
 * =====================================================================================
 * 
 *        Filename:  int_generator.cpp
 * 
 *     Description:  
 * 
 *         Version:  1.0
 *         Created:  17.01.2005 19:29:47 Westeuropäische Normalzeit
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen (), jan.tammen@fh-konstanz.de
 *         Company:  FH Konstanz
 * 
 * =====================================================================================
 */

#include <stdlib.h>
#include <iostream>
#include <fstream>

using namespace std;

int main(int argc, char* argv[])
{
    if (argc < 3)
        return 1;
    
    
    srand(time(0));      // damit startet der Zufallsz.-Gen.
                         // jeweils mit einer neuen Zahl
    
    ofstream fout(argv[1], ios::out);

    if (!fout.good())
        return 1;
   
    char* num_ints = argv[2];
    int num = atoi(num_ints);

    fout << num << endl;
    
    for (int i = 0; i < num; ++i)
    {
        fout << (rand() % 32767) << endl;
    }
   
    fout.close(); 

    return 0;
}
