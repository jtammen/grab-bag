/*
 * Jan Tammen, os4
 * 
 * BSYS, Versuch 3
 * 
 * Ein Semaphor erzeugen.
 */

#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <stdio.h>
#include <stdlib.h>

int main()
{
	// Datenstruktur anlegen
	union semun {
		int val;
		struct semid_ds* buf;
		ushort* array;
	} arg;

    // Neuen Schluessel fuer Semaphor besorgen
    key_t key = ftok("/home/os4/os4/V3/Vorbereitung/seminit.c", 'A');
        
    // Sempahor mit Schreibrechten fuer alle erstellen
    int semid = semget(key, 1, 0666 | IPC_CREAT);
    
    // Semaphor (an Array-Pos. 0) auf 1 setzen
    arg.val = 1;
    int res_ctl = semctl(semid, 0, SETVAL, arg);
	if (res_ctl == -1) {
		perror("semctl");
		exit(EXIT_FAILURE);
	}

    /*
     * IPC_RMID: Löschen des Semaphorenvektors
     *  semctl(semsemid, 0, IPC_RMID);
     * GETVAL: Abfragen des Wertes einer Semaphore
     * SETVAL: Setzen einer Semaphore
     * GETALL: Abfragen der Werte aller Semaphoren
     * SETALL: Setzen aller Semaphoren
     */

    return 0;
}
