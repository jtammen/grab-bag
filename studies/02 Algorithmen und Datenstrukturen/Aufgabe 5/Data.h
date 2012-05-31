/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 *
 *        Filename:  Data.h
 *
 *     Description:  Deklaration & Definition der Data-Klasse
 *
 *         Version:  1.0
 *         Created:  14.01.2005
 *        Revision:  none
 *        Compiler:  g++
 *
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 *
 * =====================================================================================
 */

#include <iostream>
#include <fstream>
#include <time.h>
using namespace std;

#ifndef  DATA_H
#define  DATA_H

// {{{ Deklaration Data
template <class T>
class Data
{
    public:
        Data(void);								// Default-Konstruktor
        Data(const char* filename);             // Konstruktor
        ~Data(void);                            // Destruktor, u.a. Daten zurueckspeichern

        void display(void) const;				// Ausgabe aller Elemente
        float sort(bool useMedian);				// Sortieren der Elemente
        void setM(int m);

    private:
        void quickSort(T a[], int li, int re, bool useMedian);
        int partition(T a[], int li, int re);
        int partition_median(T a[], int li, int re);
        void insertionSort(T a[], int n);

        int M;
        T* dataArray;
        int numElements;
        const char* filename;
};
// }}}

// {{{
template <class T>
void Data<T>::setM(int m)
{
    this->M = m;
    return;
}
// }}}

// {{{ Konstruktor, Daten aus Datei einlesen
template <class T>
Data<T>::Data(const char* filename)
{
    this->M = 0;
    this->numElements = 0;
    this->filename = filename;

	// Dateistrom oeffnen
    ifstream fin(filename, ios::in);

	if (fin.is_open())
	{
        T currentElement;

        // Zunaechst Anzahl der Element aus Datei lesen
        fin >> this->numElements;

        // Nun entsprechend grosses Feld erstellen...
        this->dataArray = new T [this->numElements];

        // .. dann Daten einlesen und in Feld speichern
        int i = 0;
        while (!fin.eof() && fin >> currentElement)
        {
            if (i < this->numElements)
            {
                this->dataArray[i] = currentElement;
            }
            i++;
        }

        fin.close();
    } else {
        cout << "Konnte Datei nicht oeffnen: " << filename << endl;
    }
}
// }}}

// {{{ Ausgabe aller Elemente
template <class T>
void Data<T>::display(void) const
{
    if (this->numElements == 0)
    {
        return;
    }

    cout << string(40, '-') <<  endl << "Anzahl der Elemente: " << this->numElements << endl;
    
    for (int i = 0; i < this->numElements; ++i)
    {
        cout << this->dataArray[i] << endl;
    }

    cout << string(40, '-') <<  endl;

    return;
}
// }}}

// {{{ Sortieren der Elemente
template <class T>
float Data<T>::sort(bool useMedian = false)
{
    if (this->numElements == 0)
    {
        return 0.0;
    }

    float start = clock();
    this->quickSort(this->dataArray, 0, this->numElements-1, useMedian);
    float end = clock();
    float duration = (end-start); //((end-start)/CLOCKS_PER_SEC)*1000;
    return duration;
}
// }}}

// {{{ QuickSort: Sortiert Teilfeld a[li], a[li+1], ..., a[re].
template <class T>
void Data<T>::quickSort(T a[], int li, int re, bool useMedian = false)
{
    // Teilfeld enthält mehr als 1 Element und ist laenger als M
	if (re > li && (re-li+1) > this->M)
	{
        int i;

        // Teileschritt:
        if (useMedian)
        {
            i = this->partition_median(a,li,re);
        }
        else 
        {
            i = this->partition(a,li,re);
        }

        // Herrscheschritt:
        this->quickSort(a,li,i-1);
        this->quickSort(a,i+1,re);
	} 
    else 
    {
        this->insertionSort(a+li, (re-li+1));
    }

    return;
}
// }}}

// {{{ Partition-Funktion fuer QuickSort
template <class T>
int Data<T>::partition_median(T a[], int li, int re)
{
    int m = li+(re-li)/2;
//    int m = (li+re)/2;
    T tmp;
   
    // Median bestimmen aus {a[li], a[(li + re)/2], a[re]} 
    if (a[li] > a[m])
    {
        // Vertauschen 
        tmp = a[li];
        a[li] = a[m];
        a[m] = tmp;
    }
    if (a[li] > a[re])
    {
        // Vertauschen
        tmp = a[li];
        a[li] = a[re];
        a[re] = tmp;
    } 
    else if (a[re] > a[m])
    {
        // Vertauschen
        tmp = a[re];
        a[re] = a[m];
        a[m] = tmp;
    }
    
	T v = a[re]; 	// Pivotelement
	int i = li-1;
	int j = re;

	while (1)
	{
		do i++; while (a[i] < v);
        do j--; while (j >= li);
		//do j--; while (j >= li && a[j] > v);	
		// Bedg. a[j] > v kann bei 3-Median-Strategie entfallen
		if (i >= j)
			break;
		// a[i] und a[j] vertauschen:
		T t = a[i]; a[i] = a[j]; a[j] = t;
	}
	// Pivotelement a[re] und a[i] vertauschen:
	a[re] = a[i]; a[i] = v;

	return i;
}
// }}}

// {{{ Partition-Funktion fuer QuickSort
template <class T>
int Data<T>::partition(T a[], int li, int re)
{
    T v = a[re];    // Pivotelement
    int i = li-1;
    int j = re;

    while (1)
    {
        do i++; while (a[i] < v);
        do j--; while (j >= li && a[j] > v);  
        if (i >= j)
            break;
        // a[i] und a[j] vertauschen:
        T t = a[i]; a[i] = a[j]; a[j] = t;
    }
    // Pivotelement a[re] und a[i] vertauschen:
    a[re] = a[i]; a[i] = v;

    return i;
}
// }}}

// {{{ Sortieren durch Einfuegen
template <class T> 
void Data<T>::insertionSort(T a[], int n)
{
	for (int i = 1; i < n; i++)
	{
		// Fuege a[i] in a[0]... a[i-1] an der richtigen Stelle ein:
		T v = a[i];
		int j = i-1;
		while (j >= 0 && a[j] > v)
		{
			a[j+1] = a[j];
			j--;
		}
		a[j+1] = v;
	}
}
// }}}

// {{{ Destruktor, Datei zurueckschreiben und Datenfeld loeschen
template <class T>
Data<T>::~Data(void)
{
    if (this->numElements > 0 && false)
    {
	    // Datei zurueckschreiben...
        ofstream fout(this->filename, ios::trunc);

        fout << this->numElements << endl;
        
        for (int i = 0; i < this->numElements; ++i)
        {
            fout << this->dataArray[i] << endl;
        }
    }
     
    delete [] this->dataArray;
}
// }}}

#endif   /* ----- #ifndef DATA_H  ----- */
