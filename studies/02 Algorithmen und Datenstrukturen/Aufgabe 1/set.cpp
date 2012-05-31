/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  set.cpp
 * 
 *     Description:  Implementierung der Set-Klasse
 * 
 *         Version:  1.0
 *         Created:  06.10.2004 14:21:38
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen, jan.tammen@fh-konstanz.de
 *         Company:  FH Konstanz
 * 
 * =====================================================================================
 */

#include    "set.h"
#include    <iostream>
#include    <iomanip>

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Set
 *      Method:  Set
 * Description:  Default-Konstruktor, initialisiert die Member-Variablen
 *--------------------------------------------------------------------------------------
 */
Set::Set(void)
{
    this->__elementCount = 0;
    this->__arrayCount   = BLOCK_SIZE;
    this->__elements     = new int[BLOCK_SIZE];
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Set
 *      Method:  Set(const Set & oSet)
 * Description:  Copy-Konstruktor
 *--------------------------------------------------------------------------------------
 */
Set::Set(const Set & oSet)
{
    this->__elementCount = oSet.__elementCount;
    this->__arrayCount   = oSet.__arrayCount;
    this->__elements     = new int[oSet.__arrayCount];

    // Alle Elemente kopieren
    for (int i = 0; i < oSet.__elementCount; ++i)
    {
        this->__elements[i] = oSet.__elements[i];
    }
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Set
 *      Method:  ~Set
 * Description:  Destruktor, gibt den belegten Speicher frei
 *--------------------------------------------------------------------------------------
 */
Set::~Set(void)
{
    delete [] this->__elements;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Set
 *      Method:  Operator = 
 * Description:  Zuweisungsoperator
 *--------------------------------------------------------------------------------------
 */
Set & Set::operator= (const Set & oSet)
{
    if (this != &oSet)
    {
        delete [] this->__elements;
        
        this->__elementCount = oSet.__elementCount;
        this->__arrayCount   = oSet.__arrayCount;
        this->__elements     = new int[oSet.__arrayCount];
            
        for (int i = 0; i < oSet.__elementCount; ++i)
        {
            this->__elements[i] = oSet.__elements[i];
        }
    }

    return *this;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Set
 *      Method:  isElement
 * Description:  Ueberprueft ob uebergebene Zahl Element der Menge ist
 *--------------------------------------------------------------------------------------
 */
bool Set::isElement(int iElement) const
{
    for (int i = 0; i < this->__elementCount; ++i)
    {
        if (iElement == this->__elements[i])
        {
            return true;
        }
    }

    return false;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Set
 *      Method:  isSubset
 * Description:  Ueberprueft ob uebergebene Menge Teilmenge dieser Menge ist
 *--------------------------------------------------------------------------------------
 */
bool Set::isSubset(const Set & oSet) const
{
    // Leere Menge ist nicht Teilmenge...
    if (oSet.__elementCount == 0)
    {
        return false;
    }

    // Jedes Element ueberpruefen
    for (int i = 0; i < oSet.__elementCount; ++i)
    {
        // Wenn ein Element existiert, welches nicht in der Menge enthalten ist, abbrechen
        if (this->isElement(oSet.__elements[i]) == false)
        {
            return false;
        }
    }
    
    return true;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Set
 *      Method:  insert
 * Description:  
 *--------------------------------------------------------------------------------------
 */
void Set::insert(int newElement)
{
    if (this->isElement(newElement) == true)
    {
        return;
    }

    // Falls Array-Groesse nicht mehr ausreicht, vergroessern
    if (this->__elementCount == this->__arrayCount)
    {
        this->__resize();
    }

    this->__elements[this->__elementCount++] = newElement;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Set
 *      Method:  makeUnion
 * Description:  Vereinigt die uebergebene Menge mit dieser Menge
 *--------------------------------------------------------------------------------------
 */
void Set::makeUnion(const Set & oSet)
{
    if (oSet.__elementCount == 0)
    {
        return;
    }

    // Alle Elemente der uebergebenen Menge in diese Menge einfuegen
    for (int i = 0; i < oSet.__elementCount; ++i)
    {
        this->insert(oSet.__elements[i]);
    }

    return;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Set
 *      Method:  intersect
 * Description:  Schneidet die uebergebene Menge mit dieser Menge
 *--------------------------------------------------------------------------------------
 */
void Set::intersect(const Set & oSet)
{
    // Temporaeres Hilfs-Objekt erstellen
    Set tmpSet;
   
    switch (this->__elementCount > oSet.__elementCount)
    {
        // Diese Menge ist groesser als die uebergebene
        case 0:
            // Werte der uebergebenen Menge untersuchen und ggf. in temp. Objekt einfuegen
            for (int i = 0; i < this->__elementCount; ++i)
            {
                if (oSet.isElement(this->__elements[i]))
                {
                    // Daten in temporaeres Objekt einfuegen
                    tmpSet.insert(this->__elements[i]);
                }
            }

            break;

        // ... oder andersherum
        case 1:
        default:
            for (int i = 0; i < oSet.__elementCount; ++i)
            {
                if (this->isElement(oSet.__elements[i]))
                {
                    // Daten in temporaeres Objekt einfuegen
                    tmpSet.insert(oSet.__elements[i]);
                }
            }
    }

    // Objekt neu setzen
    * this = tmpSet;

    return;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Set
 *      Method:  print
 * Description:  Gibt die einzelnen Elemente der Menge aus
 *--------------------------------------------------------------------------------------
 */
void Set::print(void) const
{
    if (this->__elementCount == 0)
    {
        return;
    }

    std::cout << "{ ";
    
    for (int i = 0; i < this->__elementCount; ++i)
    {
        std::cout << std::setw(3) << this->__elements[i];
        
        if (i+1 < this->__elementCount)
        {
            std::cout << ", ";
        }
            
    }

    std:: cout << "}" << std::endl;

    return;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Set
 *      Method:  __resize
 * Description:  Vergroessert das Feld um einen Block
 *--------------------------------------------------------------------------------------
 */
void Set::__resize()
{
    int newSize      = this->__arrayCount+BLOCK_SIZE;
    int *newElements = new int[newSize];
   
    // Werte aus altem in das neue Array kopieren
    for (int i = 0; i < this->__elementCount; ++i)
    {
        newElements[i] = this->__elements[i];
    }

    // Andere Werte mit NULL initialisieren!?
    
    // Das alte Array loeschen
    delete [] this->__elements;

    this->__arrayCount = newSize;
    this->__elements   = newElements;
}
