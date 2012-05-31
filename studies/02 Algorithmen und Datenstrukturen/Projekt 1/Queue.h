/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 *
 *        Filename:  Queue.h
 *
 *     Description:  Klassenschablone Queue / Methodenschablonen
 *
 *         Version:  1.0
 *         Created:  04.11.2004 11:12:21 CET
 *        Revision:  none
 *        Compiler:  gcc
 *
 *          Author:  Jan Tammen (jt), Vorlage:
 *					 http://www-home.fh-konstanz.de/~bittel/alda/queueAsCircularArray.[h|cpp]
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 *
 * =====================================================================================
 */

#ifndef  QUEUE_H
#define  QUEUE_H

static const int defaultLength = 100;

template <class T>
class Queue
{
    public:
        Queue(int n = defaultLength);			// Konstruktor, erzeugt leere Queue
        ~Queue();					            // Destruktor

        void add(T newElement);			        // Fuegt newElement am Ende der Queue ein
        int  front(T &nextElement) const;		// Liefert erstes Queue-Element; return: 0 falls leer, sonst 1
        int  remove();					        // Erstes Element entfernen

        int length() const { return number; }		// Gibt die Laenge der Queue zurueck
        bool isEmpty() const { return number == 0; }	// Ist die Queue leer?

    private:
        void resize(void);				        // Vergroessert das Array

        T* aElements;					        // zirkulaeres Array
        int start;
        int end;
        int size;					            // Groesse des Arrays
        int number;					            // Anzahl der Elemente
};

// Konstruktor
template <class T>
Queue<T>::Queue(int n)
{
    this->aElements = new T[n];
    this->size      = n;
    this->start     = 0;
    this->end       = n-1;
    this->number    = 0;
}

// Destruktor
template <class T>
Queue<T>::~Queue()
{
    delete [] this->aElements;
}

// Element einfuegen
template <class T>
void Queue<T>::add(T newElement)
{
    if (this->number == this->size)
    {
        this->resize();
    }

    this->end = (this->end+1) % this->size;
    this->aElements[this->end] = newElement;
    this->number++;
    return;
}

// Erstes Element liefern
template <class T>
int Queue<T>::front(T& nextElement) const
{
    if (this->isEmpty())
    {
        return 0;
    }

    nextElement = this->aElements[this->start];
    return 1;
}

// Erstes Element entfernen
template <class T>
int Queue<T>::remove()
{
    if (this->isEmpty())
    {
        return 0;
    }

    this->start = (this->start+1) % this->size;
    this->number--;
    return 1;
}

// Queue vergroessern
template <class T>
void Queue<T>::resize(void)
{
    T* tmpArray = new T[this->size+defaultLength];

    // Alle Elemente in neues Array kopieren
    for (int i = 0; i < this->number; ++i)
    {
        tmpArray[i] = this->aElements[i];
    }

    delete [] this->aElements;
    this->aElements = tmpArray;
    this->size += defaultLength;

    return;
}

#endif   /* ----- #ifndef QUEUE_H  ----- */

