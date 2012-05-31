/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  set.h
 * 
 *     Description:  Definition der Set-Klasse
 * 
 *         Version:  1.0
 *         Created:  06.10.2004 14:22:33
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 * 
 * =====================================================================================
 */


#ifndef  SET_H
#define  SET_H

const int BLOCK_SIZE = 8;

class Set
{
    public:
        Set(void);                            // Default-Konstruktor
        Set(const Set &);                     // Copy-Konstruktor
        ~Set(void);                           // Destruktor
        Set & operator= (const Set &);        // Zuweisungs-Operator

        bool isElement(int element) const;
        bool isSubset(const Set & oSet) const;
        void insert(int newElement);
        void makeUnion(const Set & oSet);
        void intersect(const Set & oSet);
        void print(void) const;

    private:
        void __resize();
        
        int * __elements;
        int __elementCount;
        int __arrayCount;
};

#endif   /* ----- #ifndef SET_H  ----- */
