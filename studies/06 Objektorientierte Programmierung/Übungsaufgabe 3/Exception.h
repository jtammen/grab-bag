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

class FactoryException : public Exception
{
    public:
        FactoryException (std::string text);    
};

class InitException : public Exception
{
    public:
        InitException (std::string text);    
};

class UnknownArticleException : public Exception
{
    public:
        UnknownArticleException (std::string text);      
};

#endif
