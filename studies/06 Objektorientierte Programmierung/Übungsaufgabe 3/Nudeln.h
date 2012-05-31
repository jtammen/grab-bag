/** 
 * @file        Nudeln.h
 * @synopsis    Deklaration Klasse Nudeln
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @author		Christoph Eck (FH Konstanz), <christoph.eck@fh-konstanz.de>
 * @date        2005-05-10
 */

#ifndef NUDELN_H
#define NUDELN_H

#include "Artikel.h"

class Nudeln : public Artikel
{
    public:
        Nudeln () : mArt("undef"), mZubereitung("undef") {}
        virtual ~Nudeln () {}

        virtual void init ();

    private:
        string mArt;
        string mZubereitung;

    protected:
        virtual ostream& display (ostream& out);
};
#endif
