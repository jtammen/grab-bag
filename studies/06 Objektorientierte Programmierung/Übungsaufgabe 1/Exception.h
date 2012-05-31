/** 
 * @file        Exceptions.h
 * @synopsis 
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @date        2005-03-24
 */

#ifndef EXCEPTION_H
#define EXCEPTION_H

#include <iostream>

class Exception
{
    public:
        Exception (std::string text);
        ~Exception (void);
        std::string getMessage(void) { return mText; }
        
    private:
        std::string mText;
};

class InputException : public Exception
{
    public:
        InputException (std::string text);
};

class OverflowException : public Exception
{
    public:
        OverflowException (std::string text);    
};

#endif
