/*
 * Kind-, Enkel- und Urenkelprozess erzeugen und
 * eigene sowie Erzeuger-PID ausgeben.
 *
 * Jan Tammen, os4
 */

#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

int main (void)
{
    printf("PID Vater: \t\t\t\t %d\n\n", getpid());
    
    switch ( fork() ) {     // Kind-Prozess erzeugen
	case -1:            // Fehler
	    perror("Konnte mittels fork() keinen neuen Kind-Prozess erzeugen");
	    break;

	case  0:            // Im Kind-Prozess
	    printf("PID Erzeuger von Subprozess Nr.1: \t %d\n", getppid());
	    printf("PID Subprozess Nr.1: \t\t\t %d\n\n", getpid());
	    
	    // Enkel-Prozess (Kindes-Kind) erzeugen
	    switch ( fork() ) {
		case -1:            // Fehler
		    perror("Konnte mittels fork() keinen neuen Kind-Prozess erzeugen");
		    break;

		case  0:            // Im Enkel-Prozess (Kindes-Kind)
		    printf("PID Erzeuger von Subprozess Nr.2: \t %d\n", getppid());
		    printf("PID Subprozess Nr.2: \t\t\t %d\n", getpid());
		    printf("\n");

		    // Urenkel-Prozess (Kindes-Kindes-Kind) erzeugen
		    switch ( fork() ) {
			case -1:            // Fehler
			    perror("Konnte mittels fork() keinen neuen Kind-Prozess erzeugen");
			    break;

			case  0:            // Im Urenkel-Prozess (Kindes-Kindes-Kind)
			    printf("PID Erzeuger von Subprozess Nr.3: \t %d\n", getppid());
			    printf("PID Subprozess Nr.3: \t\t\t %d\n", getpid());
			    exit(0);

			default:            // Im Eltern-Prozess
			    break;
		    }
		    exit(0);

		default:            // Im Eltern-Prozess
		    break;
	    }
	    exit(0);

	default:            // Im Eltern-Prozess
	    break;
    }

    return 0;
}
