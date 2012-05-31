/*
 * Jan Tammen, os4
 * 
 * BSYS, Versuch 3
 * 
 * Aufgabe 3.1.2.2
 * 
 * Kindprozess erzeugen und Full-Duplex-Pipe aufbauen.
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <wait.h>

int main()
{
    // Fuer Kindprozess
    // In filedes[0]	-> Filedeskriptor zum Lesen
    // In filedes[1]	-> Filedeskriptor zum Schreiben
    int filedes[2];
    
    // Fuer Elternprozess
    // In filedes2[0]	-> Filedeskriptor zum Lesen
    // In filedes2[1]	-> Filedeskriptor zum Schreiben
    int filedes2[2];

    char buffer[30];
    int status;
    char* zahl_string = "23";
    int zahl_int, quadrat;
    
    // Pipes oeffnen
    if (pipe(filedes) == -1 || pipe(filedes2) == -1) {
	perror("pipe");
	exit(EXIT_FAILURE);
    }

    // Nun Kindprozess erstellen
    switch ( fork() ) {
	case -1:    // Fehler
	    perror("fork");
	    exit(EXIT_FAILURE);
	case 0:	    // Im Kindprozess
	    close(filedes[1]);		    // Nicht benoetigte Filedes. schliessen
	    close(filedes2[0]);
	    
	    if ( read(filedes[0], buffer, 30) == -1) { // Aus Pipe lesen
		perror("read");
		exit(EXIT_FAILURE);
	    }

	    zahl_int = atoi(buffer);	    // String in Integer
	    quadrat = (zahl_int*zahl_int);
	    printf("[Kindprozess] Zahl = %d, Quadrat = %d\n",
		    zahl_int, quadrat);

	    // Ergebnis wieder in String wandeln, und dann in Pipe schreiben
	    sprintf(buffer, "%d", quadrat);
	    write(filedes2[1], buffer, 30);
	    
	    close(filedes[0]);		    // Nicht mehr benoetigte Filed. schliessen
	    close(filedes2[1]);
	    break;
	default:    // Im Elternprozess
	    close(filedes[0]);		    // Nicht benoetigte Filed. schliessen
	    close(filedes2[1]);
	    
	    write(filedes[1], zahl_string, 30); // In Pipe schreiben
	    wait(&status);		    // Auf Kind warten

	    read(filedes2[0], buffer, 30);  // Von Kind das Ergebnis lesen
	    quadrat = atoi(buffer);

	    printf("Mein Kindprozess sagt, das Quadrat von %s ist %d\n",
		    zahl_string, quadrat);
	    
	    close(filedes[1]);		    // Nicht mehr benoetigte Filedes. schliessen
	    close(filedes2[0]);
	    exit(EXIT_SUCCESS);
    }
    
    return 0;
}
