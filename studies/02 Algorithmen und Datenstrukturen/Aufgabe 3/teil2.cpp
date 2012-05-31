/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  teil2.cpp
 * 
 *     Description:  Aufgabenblatt 3, Aufgabe 2
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

#include <iostream>
#include <string>
#include "teil2.h"

int main()
{
    int l = 0;
    int r = 19;

    // unsortiertes Feld
    int a[20] = {1, 3, 5, 6, 7, 59, 45, 34, 23, 4, 99, 3, 11, 34, 77, 3, 11, 45, 22, 9};

    // sortiertes Feld
    int b[20] = {1, 3, 5, 7, 12, 22, 23, 34, 41, 44, 50, 53, 61, 64, 77, 79, 85, 90, 95, 99};

//    cout << f(a, l, r) << endl;
    cout << f(b, l, r) << endl;
}
