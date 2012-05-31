/*
 * Zombie-Prozess anlegen und mittels ps t anzeigen.
 *
 * Jan Tammen, os4
 */

#include <unistd.h>
#include <stdlib.h>

int main (void)
{
    long int i;
    
    if (fork()) {
	for (i = 0; i < 1000000; ++i);
    }

    system("ps t");
    return 0;
}
