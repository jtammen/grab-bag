/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 *
 *        Filename:  Event.h
 *
 *     Description:  Event-Struktur Deklaration
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

#ifndef  EVENT_H
#define  EVENT_H

struct Event
{
    char  type;			// Typ: i = Ankunft, o = Abgang
    float time;			// Zeitpunkt des Ereignisses
    float d;			// Bedienzeit, nur bei t = 0
    int nrSchalter;		// Nummer des Schalters, nur bei t = i
};

struct EventNode
{
    EventNode* next;
    Event data;
};

#endif   /* ----- #ifndef EVENT_H  ----- */
