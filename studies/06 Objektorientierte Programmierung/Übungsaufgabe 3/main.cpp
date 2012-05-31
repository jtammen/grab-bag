/** 
 * @file        main.cpp
 * @synopsis    Hauptprogramm Uebungsaufgabe 3, OOP
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#include <string>
#include <fstream>
#include <iostream>
#include <sstream>
#include <stdlib.h>
using namespace std;

#include "Anwendung.h"

int main ()
{
    try {
        Anwendung pizzaService = Anwendung();
    	pizzaService.init();
        pizzaService.run();
    }
    catch (Exception& e)
    {
        clog << e.getMessage() << endl;
    }        

    system ("pause");
    return 0;
}
