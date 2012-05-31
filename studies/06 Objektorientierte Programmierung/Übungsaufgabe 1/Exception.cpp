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

InputException::InputException (std::string text) :
                Exception::Exception(text)
{
    // foo
}

OverflowException::OverflowException (std::string text) :
                   Exception::Exception(text)
{
    // foo
}
