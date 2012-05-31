#include <stdio.h>
#include <unistd.h>
#include <string.h>

int main (int argc, char** argv)
{
    char stringarray[3][128];

    strncpy(stringarray[0], "Dies ist ein Test 1", 20);
    strncpy(stringarray[1], "Dies ist ein Test 2", 20);
    strncpy(stringarray[2], "Dies ist ein Test 3", 20);
    /*stringarray[0] = "Dies ist ein kleiner Test";
    stringarray[1] = "fsddffdsfdsl";
    stringarray[2] = "554df";*/

    int i;
    for (i = 0; i < 3; ++i) {
        printf("%s\n", stringarray[i]);
    }
    
    return 0;
}
