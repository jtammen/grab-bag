/*
 * Jan Tammen, os4
 * 
 * BSYS, Versuch 3
 * 
 * Aufgabe 3.1.2.1
 * 
 * Kindprozess erzeugen und Half-Duplex-Pipe aufbauen.
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <wait.h>

int main(int argc, char** argv)
{
    int filedes[2];
    char buffer[30];
    int status;
    
    // Pipe oeffnen
    if (pipe(filedes) == -1) {
	perror("pipe");
	exit(EXIT_FAILURE);
    }

    // In filedes[0] -> Filedeskriptor zum Lesen
    // In filedes[1] -> Filedeskriptor zum Schreiben

    // Nun Kindprozess erstellen
    switch ( fork() ) {
	case -1:    // Fehler
	    perror("fork");
	    exit(EXIT_FAILURE);
	case 0:	    // Im Kindprozess
	    close(filedes[1]);			    // Schreibenden Filedes. schliessen
	    
	    if ( read(filedes[0], buffer, 30) == -1) { // Aus Pipe lesen
		perror("read");
		exit(EXIT_FAILURE);
	    }

	    printf("%s", buffer);		    // erhaltenen Text ausgeben
	    close(filedes[0]);			    // Lesenden Filed. schliessen
	    break;
	default:    // Im Elternprozess
	    close(filedes[0]);			    // Lesenden Filed. schliessen
	    write(filedes[1], 
		    "Hallo neuer Prozess\n", 30);   // In Pipe schreiben
	    wait(&status);
	    close(filedes[1]);			    // Schreibenden Filedes. schliessen
	    exit(EXIT_SUCCESS);
    }
    
    return 0;
}
