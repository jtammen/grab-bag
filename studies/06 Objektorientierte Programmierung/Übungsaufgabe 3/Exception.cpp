/** 
 * @file        Exceptions.cpp
 * @synopsis 
 * @author      Jan Tammen (FH Konstanz), <jan.tammen@fh-konstanz.de>
 * @date        2005-03-24
 */

#include "Exception.h"

Exception::Exception (std::string text)
{
    mText = text;
}

Exception::~Exception (void)
{
    // foo
}

FileNotReadableException::FileNotReadableException (std::string text) :
                          Exception(text)
{
    // foo
}

FileNotWriteableException::FileNotWriteableException (std::string text) :
                           Exception(text)
{
    // foo
}

FactoryException::FactoryException (std::string text) :
                  Exception(text)
{
    // foo
}

InitException::InitException (std::string text) :
               Exception(text)
{
    // foo
}

UnknownArticleException::UnknownArticleException (std::string text) :
                         Exception(text)
{
    // foo
}
