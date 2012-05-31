/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  teil3.cpp
 * 
 *     Description:  Aufgabenblatt 3, Aufgabe 3
 * 
 *         Version:  1.0
 *         Created:  25.11.2004 09:59:02 CET
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen (jt), jan.tammen@fh-konstanz.de
 *         Company:  FH Konstanz
 * 
 * =====================================================================================
 */

static const int anzStaedte = 9;

#include <list>
#include <iostream>
#include <string>
#include "teil3.h"

int main()
{
    // Test-Matrix, gespiegelt
    //                                          0   1   2   3   4   5   6   7   8
    int benachbart[anzStaedte][anzStaedte] = {{-1,  1,  6,  2,  2, -1, -1, -1, -1}, // 0
                                              { 1, -1,  4, -1, -1,  1, -1, -1, -1}, // 1
                                              { 6,  4, -1,  3, -1, -1, -1,  8, -1}, // 2
                                              { 2, -1,  3, -1, -1, -1, -1, -1, -1}, // 3
                                              { 2, -1, -1, -1, -1,  4,  1, -1, -1}, // 4
                                              {-1,  1, -1, -1,  4, -1, -1, -1,  7}, // 5
                                              {-1, -1, -1, -1,  1, -1, -1,  1, -1}, // 6
                                              {-1, -1,  8, -1, -1, -1,  1, -1,  1}, // 7
                                              {-1, -1, -1, -1, -1,  7, -1,  1, -1}  // 8
                                             };

    int faden[anzStaedte];
    list<int> aktuellerWeg;
    for (int i = 0; i < anzStaedte; ++i)
    {
        faden[i] = 0;
    }
    
    // Wege suchen
    cout << "Anzahl Wege gesamt: " << sucheWege(0, 8, benachbart, 0, faden, aktuellerWeg);
}
