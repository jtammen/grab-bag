/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 *
 *        Filename:  Dictionary.cpp
 *
 *     Description:  Definition der Dictionary-Klasse
 *
 *         Version:  1.0
 *         Created:  20.12.2004
 *        Revision:  none
 *        Compiler:  g++
 *
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 *
 * =====================================================================================
 */

#include "Data.h"
#include <iostream>
#include <fstream>

// Konstruktor, Daten aus Datei einlesen
template <class T>
Dictionary<T>::Dictionary(string filename)
{
	// Dateistrom oeffnen
    ifstream fin(filename);

	if (!fin)
	{
		return false;
	}

	int numElements;
	<T> currentElement;

	// Zunaechst Anzahl der Element aus Datei lesen
	fin.readline(fin, numElements);

	// Dann entsprechend grosses Feld erstellen...
	this->dataArray = new <T> [numElements];

	// .. dann Daten einlesen und in Feld speichern
	int i = 0;
    while (getline(fin, currentElement))
    {
		this->dataArray[i] = currentElement;
		i++;
    }
    fin.close();
}

// Destruktor, Datei zurueckschreiben und Datenfeld loeschen
template <class T>
Dictionary<T>::~Data(void)
{
	// Datei zurueckschreiben...
	// TODO
    delete [] this->dataArray;
}

//template <class T>
//bool Dictionary<T>::readDataFromFile(string filename)
//{
//}