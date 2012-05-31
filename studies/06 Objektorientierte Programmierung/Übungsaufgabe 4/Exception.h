/** 
 * @file        Exception.h
 * @synopsis 
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @date        2005-05-14
 */

#ifndef EXCEPTION_H
#define EXCEPTION_H

#include <iostream>

class Exception
{
    public:
        Exception (std::string text);
        ~Exception (void);
        std::string getMessage(void) const { return mText; }
        
    private:
        std::string mText;
};

class FileNotReadableException : public Exception
{
    public:
        FileNotReadableException (std::string text);
};

class FileNotWriteableException : public Exception
{
    public:
        FileNotWriteableException (std::string text);    
};
#endif
