/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 *
 *        Filename:  main.cpp
 *
 *     Description:  Hauptprogramm Aufgabe 5
 *
 *         Version:  1.0
 *         Created:  01.12.2004
 *        Revision:  none
 *        Compiler:  gcc
 *
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 *
 * =====================================================================================
 */

#include <iostream>
#include <fstream>
#include "Data.h"
#include "Complex.h"
#include "Person.h"

int main()
{
    Data<double>  doubleDat("test_double.txt");
    Data<int>     intDat("test_int.txt");
    Data<Complex> complexDat("test_complex.txt");
    Data<Person>  personDat("test_person.txt");

    cout << "Ausgabe doubleDat (unsortiert):" << endl;
    doubleDat.display();
    cout << string(40, '-') << endl;
    cout << "Ausgabe doubleDat (sortiert):" << endl;
    doubleDat.sort();
    doubleDat.display();
    cout << string(40, '-') << endl << endl;

    cout << "Ausgabe intDat (unsortiert):" << endl;
    intDat.display();
    cout << string(40, '-') << endl;
    cout << "Ausgabe intDat (sortiert):" << endl;
    intDat.sort();
    intDat.display();
    cout << string(40, '-') << endl << endl;

    cout << "Ausgabe complexDat (unsortiert):" << endl;
    complexDat.display();
    cout << string(40, '-') << endl;
    cout << "Ausgabe complexDat (sortiert):" << endl;
    complexDat.sort();
    complexDat.display();
    cout << string(40, '-') << endl << endl;

    cout << "Ausgabe personDat (unsortiert):" << endl;
    personDat.display();
    cout << string(40, '-') << endl;
    cout << "Ausgabe personDat (sortiert):" << endl;
    personDat.sort();
    personDat.display();
    cout << string(40, '-') << endl << endl;

    return 0;
  
    // {{{ 
    // M = 0, N = 10000
    cout << "Laufzeitmessung (M = 0, N = 10000):" << endl;
    int runs = 10;
    float gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_10000.txt");
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;

    // M = 0, N = 20000
    cout << "Laufzeitmessung (M = 0, N = 20000):" << endl;
    gesamtZeit = 0.0;
    
    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_20000.txt");
        gesamtZeit += datInt.sort();
    }
    
    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;
    
    // M = 0, N = 30000
    cout << "Laufzeitmessung (M = 0, N = 30000):" << endl;
    gesamtZeit = 0.0;
    
    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_30000.txt");
        gesamtZeit += datInt.sort();
    }
    
    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;

    // M = 0, N = 40000
    cout << "Laufzeitmessung (M = 0, N = 40000):" << endl;
    gesamtZeit = 0.0;
    
    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_40000.txt");
        gesamtZeit += datInt.sort();
    }
    
    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;
    // }}}

    // {{{
    // M = 20, N = 10000
    cout << "Laufzeitmessung (M = 20, N = 10000):" << endl;
     gesamtZeit = 0.0;
    
    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_10000.txt");
        datInt.setM(20);
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;
    
    // M = 20, N = 20000
    cout << "Laufzeitmessung (M = 20, N = 20000):" << endl;
    gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_20000.txt");
        datInt.setM(20);
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;

    // M = 20, N = 30000
    cout << "Laufzeitmessung (M = 20, N = 30000):" << endl;
    gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_30000.txt");
        datInt.setM(20);
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;

    // M = 20, N = 40000
    cout << "Laufzeitmessung (M = 20, N = 40000):" << endl;
    gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_40000.txt");
        datInt.setM(20);
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;
    // }}}
   
    // {{{ 
    // M = N, N = 10000
    cout << "Laufzeitmessung (M = N, N = 10000):" << endl;
    gesamtZeit = 0.0;
    
    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_10000.txt");
        datInt.setM(10000);
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;
    
    // M = N, N = 20000
    cout << "Laufzeitmessung (M = N, N = 20000):" << endl;
    gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_20000.txt");
        datInt.setM(20000);
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;

    // M = N, N = 30000
    cout << "Laufzeitmessung (M = N, N = 30000):" << endl;
    gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_30000.txt");
        datInt.setM(30000);
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;

    // M = N, N = 40000
    cout << "Laufzeitmessung (M = 20, N = 40000):" << endl;
    gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_40000.txt");
        datInt.setM(40000);
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;
    // }}}

    // {{{ 
    // M = 20, N = 10000
    cout << "Laufzeitmessung (M = 20, N = 10000, vorsortiert):" << endl;
    gesamtZeit = 0.0;
    
    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_10000_sortiert.txt");
        datInt.setM(20);
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;
    
    // M = 20, N = 20000
    cout << "Laufzeitmessung (M = 20, N = 20000, vorsortiert):" << endl;
    gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_20000_sortiert.txt");
        datInt.setM(20);
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;

    // M = 20, N = 30000
    cout << "Laufzeitmessung (M = 20, N = 30000, sortiert):" << endl;
    gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_30000_sortiert.txt");
        datInt.setM(20);
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;

    // M = 20, N = 40000
    cout << "Laufzeitmessung (M = 20, N = 40000, sortiert):" << endl;
    gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_40000.txt");
        datInt.setM(20);
        gesamtZeit += datInt.sort();
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;
    // }}}

    // {{{ 
    // M = 20, N = 10000
    cout << "Laufzeitmessung (M = 20, N = 10000, vorsortiert, 3-Median-Strategie):" << endl;
    gesamtZeit = 0.0;
    
    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_10000_sortiert.txt");
        datInt.setM(20);
        gesamtZeit += datInt.sort(true);
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;
    
    // M = 20, N = 20000
    cout << "Laufzeitmessung (M = 20, N = 20000, vorsortiert, 3-Median-Strategie):" << endl;
    gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_20000_sortiert.txt");
        datInt.setM(20);
        gesamtZeit += datInt.sort(true);
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;

    // M = 20, N = 30000
    cout << "Laufzeitmessung (M = 20, N = 30000, sortiert, 3-Median-Strategie):" << endl;
    gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    {
        Data<int> datInt("test_int_30000_sortiert.txt");
        datInt.setM(20);
        gesamtZeit += datInt.sort(true);
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;

    // M = 20, N = 40000
    cout << "Laufzeitmessung (M = 20, N = 40000, sortiert, 3-Median-Strategie):" << endl;
    gesamtZeit = 0.0;

    for (int i = 0; i < runs; ++i)
    { 
        Data<int> datInt("test_int_40000.txt");
        datInt.setM(20);
        gesamtZeit += datInt.sort(true);
    }

    cout << "Durchschnittliche Laufzeit der Sortierung: " << ((gesamtZeit/CLOCKS_PER_SEC)*1000)/runs << " msec" << endl;
    cout << string(40, '-') << endl << endl;
    // }}}

    return 0;
}
