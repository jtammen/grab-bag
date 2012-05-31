/*
 * 4 Kind-Prozesse mithilfe von fork() erzeugen und PID
 * des Erzeugers ausgeben.
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
    int num_fork;
    pid_t new_pid;

    printf("PID Vater: \t\t\t %d\n\n", getpid());
    
    for (num_fork = 1; num_fork < 5; ++num_fork)
    {
	switch ( new_pid = fork() ) {     // Kind-Prozess erzeugen
	    case -1:            // Fehler
		perror("Konnte mittels fork() keinen neuen Kind-Prozess erzeugen");
		break;

	    case  0:            // Im Kind-Prozess
		printf("PID Erzeuger von Kind Nr.%d: \t %d\n", num_fork, getppid());
		printf("PID Kind Nr.%d: \t\t\t %d\n", num_fork, getpid());
		exit(0);

	    default:            // Im Eltern-Prozess
		break;
	}

	printf("\n");
    }

    return 0;
}
