/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 *
 *        Filename:  EventList.cpp
 *
 *     Description:  EventList-Klasse Definition
 *
 *         Version:  1.0
 *         Created:  04.11.2004 11:02:49 CET
 *        Revision:  none
 *        Compiler:  gcc
 *
 *          Author:  Jan Tammen (jt), jan.tammen@fh-konstanz.de
 *         Company:  FH Konstanz
 *
 * =====================================================================================
 */

#include "EventList.h"
#include <iostream>

// Konstruktor
EventList::EventList()
{
    // Hilfskopfknoten anlegen
    this->head        = new EventNode;
    this->head->next  = 0;
    this->numElements = 0;
}

// Destruktor
EventList::~EventList(void)
{
    while (this->head != 0)
    {
        EventNode* p = this->head;
        this->head   = this->head->next;
        delete p;
    }
}

// Event einfuegen
void EventList::insert(Event newEvent)
{
    // Richtige Position finden
    EventNode* p = this->head;
    while (p->next != 0 && p->next->data.time < newEvent.time)
    {
        p = p->next;
    }

    EventNode* d = new EventNode;
    d->data = newEvent;
    d->next = p->next;
    p->next = d;
    
    this->numElements++;
    return;
}

// Naechstes Event holen und entfernen
int EventList::next(Event &Event)
{
    if (this->numElements == 0)
    {
        return 0;
    }

    // Element in Variable speichern
    EventNode* p = this->head;
    Event        = p->next->data;

    // Element aus Liste entfernen
    EventNode* q = p->next;
    p->next      = q->next;

    this->numElements--;
    return 1;
}
