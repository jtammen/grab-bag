// mainAufgabe1.cpp
// Hauptprogramm zu Aufgabe1
//
// O.Bittel; 21.9.04
//

// HINWEIS:
// Die Reihenfolge der Elemente zwischen Soll- und Ist-Ausgabe
// darf selbstverständlich unterschiedlich sein.
// Beispielsweise wäre folgende Ausgabe korrekt:
// Soll: {   1,   2,   3}
// Ist:  {   3,   1,   2}


#include <iostream>
#include "set.h"

using namespace std;

int main()
{
	// Menge m1:
	Set m1;
	m1.insert(1);
	m1.insert(2);
	m1.insert(3);
	cout << "Soll: {   1,   2,   3}" << endl;
	cout << "Ist:  "; m1.print();
	cout << endl;


	// Menge m2:
	Set m2;
	m2.insert(2);
	m2.insert(4);
	m2.insert(2);
	cout << "Soll: {   2,   4}" << endl;
	cout << "Ist:  "; m2.print();
	cout << endl;


	// Menge m3:
	Set m3=m2;
	m3.insert(3);
	cout << "Soll: {   2,   3,   4}" << endl;
	cout << "Ist:  "; m3.print();
	cout << endl;


	// Menge m4:
	Set m4;
    m4.insert(2);
	m4.insert(3);
	m4.insert(7);
	cout << "Soll: {   2,   3,   7}" << endl;
	cout << "Ist:  "; m4.print();
	cout << endl;


	// isElement:
	cout << "Soll: 1" << endl;
	cout << "Ist:  " << m1.isElement(1) << endl;
	cout << "Soll: 0" << endl;
	cout << "Ist:  " << m1.isElement(7) << endl;
	cout << endl;


	// Menge m5, Zuweisung und makeUnion:
	Set m5;
	m5 = m1;
	m5.makeUnion(m2);
	cout << "Soll: {   1,   2,   3,   4}" << endl;
	cout << "Ist:  "; m5.print();
	cout << endl;


	// isSubset:
	cout << "Soll: 1" << endl;
	cout << "Ist:  " << m5.isSubset(m3) << endl;
	cout << "Soll: 0" << endl;
	cout << "Ist:  " << m5.isSubset(m4) << endl;
	cout << endl;


	// Menge m6, resize und intersect:
	for (int i = 1; i < 1000; i++)
		m1.insert(i*i);
	Set m6;
	for (int i = 100; i < 200; i++)
		m6.insert(i);
	m1.intersect(m6);
	cout << "Soll: { 100, 121, 144, 169, 196}" << endl;
	cout << "Ist:  "; m1.print();
}
