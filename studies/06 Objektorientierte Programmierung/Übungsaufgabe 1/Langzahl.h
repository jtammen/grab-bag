/** 
 * @file       Langzahl.h
 * @synopsis   Langzahl Interface
 * @author     Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @date       2005-03-21
 */

#ifndef LANGZAHL_H
#define LANGZAHL_H

class Langzahl
{
    public:
        /// virtueller Destruktor
        virtual ~Langzahl() {};
           
        /// Ueberladene Operatoren
        Langzahl& operator=  (const Langzahl& z);
        
        Langzahl& operator+  (const Langzahl& z);
        Langzahl& operator-  (const Langzahl& z);
        Langzahl& operator*  (const Langzahl& z);
        Langzahl& operator/  (const Langzahl& z);
        
        Langzahl& operator+= (const Langzahl& z);
        Langzahl& operator-= (const Langzahl& z);
        Langzahl& operator*= (const int x);
        Langzahl& operator/= (const Langzahl& z);

        bool operator>  (const Langzahl& z);
        bool operator>= (const Langzahl& z);
        bool operator<  (const Langzahl& z);
        bool operator<= (const Langzahl& z);

        bool     isNull ()       const { return mIsNull; }
        void     setIsNull (bool x)    { mIsNull = x;    }
        long int getNumber(void) const;
        
    protected:
        /// bool'scher Wert: ist Zahl = 0?
        bool        mIsNull;
        
        /// Vorzeichen: -1: negativ, 1: positiv
        short int   mSign;
};

#endif
