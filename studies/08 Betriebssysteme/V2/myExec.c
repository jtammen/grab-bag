/*
 * Prozess erzeugen und Befehl ausfuehren lassen.
 *
 * Jan Tammen, os4
 */

#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
//#include <errno.h>
//#include <string.h>

int main (int argc, char* argv[])
{
    int status;
    
    if (argc < 2) {
	perror("Bitte auszufuehrendes Programm uebergeben");
	exit(0);
    }

    pid_t new_pid;
    switch ( new_pid = fork() ) {     // Kind-Prozess erzeugen
	case -1:            // Fehler
	    perror("Konnte mittels fork() keinen neuen Kind-Prozess erzeugen");
	    break;

	case  0:            // Im Kind-Prozess
	    // Programm ausfuehren
	    execvp(argv[1], &argv[1]);
	    exit(EXIT_FAILURE);

	default:            // Im Eltern-Prozess
	    // Auf Kind-Prozess warten
	    waitpid(new_pid, &status, 0);
	    
	    // Fehlermeldung ausgeben
	    if (status != 0)
		printf("Exec fehlgeschlagen. Programm %s beendet mit Status %d\n", 
//			strerror(errno),
		        argv[1], 
			status);

	    break;
    }

    return 0;
}
