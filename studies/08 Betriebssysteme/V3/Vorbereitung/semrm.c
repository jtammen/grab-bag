/*
 * Jan Tammen, os4
 * 
 * BSYS, Versuch 3
 *
 * Semaphor aus Kernel loeschen.
 *
 * Manuelles Loeschen: ipcrm -s <semid>
 */

#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <stdio.h>
#include <stdlib.h>

int main()
{
    // Neuen Schluessel erstellen
    key_t key = ftok("/home/os4/os4/V3/Vorbereitung/seminit.c", 'A');

	// ID des existierenden Semaphors besorgen
	int semid = semget(key, 1, 0);

	if (semid == -1) {
		perror("semget");
		exit(EXIT_FAILURE);
	}

	// Loeschen - wozu union semun benoetigt?! gar nicht!?
	int res_ctl = semctl(semid, 0, IPC_RMID);

	if (res_ctl == -1) {
		perror("semctl");
		exit(EXIT_FAILURE);
	}

	return 0;
}
