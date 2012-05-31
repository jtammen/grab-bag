/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 *
 *        Filename:  EventList.h
 *
 *     Description:  EventList-Klasse Deklaration
 *
 *         Version:  1.0
 *         Created:  04.11.2004 11:02:09 CET
 *        Revision:  none
 *        Compiler:  gcc
 *
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 *
 * =====================================================================================
 */

#ifndef  EVENTLIST_H
#define  EVENTLIST_H

#include "Event.h"

class EventList
{
    public:
        EventList();                            // Konstruktor, leere Liste anlegen
        ~EventList(void);						// Destruktor

        void insert(Event newEvent);		// Event einfuegen
        int next(Event &Event);		    	// Naechstes Event holen und entfernen

    private:
        EventNode* head;
        int numElements;                       // Anzahl Elemente
};

#endif   /* ----- #ifndef EVENTLIST_H  ----- */

