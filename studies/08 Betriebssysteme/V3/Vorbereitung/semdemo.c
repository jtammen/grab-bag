/*
 * Jan Tammen, os4
 * 
 * BSYS, Versuch 3
 *
 * Semaphor setzen und freigeben.
 *
 * Auflisten der Sempahoren: ipcs -s
 */

#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <stdio.h>
#include <stdlib.h>

/* So sieht die Struktur sembuf aus:
 *
 *	struct sembuf {
 *	    unsigned short sem_num; // Nummer des Semaphors in der Semaphoren-Menge: 0 = erste
 *	    short sem_op;           // Änderung des Semaphorenwerts (>0: V-Operation; <0: P<-Operation)
 *	    short sem_flg;          // IPC_NOWAIT und/oder SEM_UNDO
 *	};
 */

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

    // Auf Eingabe mit getchar() warten
	puts("Enter druecken, um kritischen Bereich zu betreten\n");
	getchar();
	
	// Wert neu setzen, dazu zunaechst Datenstruktur 
	// fuer Parameter der Operation anlegen
	struct sembuf operations[1];
	operations[0].sem_num = 0;
	operations[0].sem_op = -1;	// -> P()
	operations[0].sem_flg = 0;

    int res_op = semop(semid, operations, 1);	// sperren

	if (res_op == -1) {
		perror("semop");
		exit(EXIT_FAILURE);
	}

	
    // Erfolgsmeldung ausgeben
	puts("Erfolgreich belegt.\n");

	// Auf Eingabe mit getchar() warten
	printf("Enter druecken, um Semaphor freizugeben\n");
	getchar();

    // Semaphor freigeben
	operations[0].sem_op = 1;	// -> V()
	res_op = semop(semid, operations, 1);		// freigeben
	
	if (res_op == -1) {
		perror("semop");
		exit(EXIT_FAILURE);
	}

    // Erfolgsmeldung ausgeben
	puts("Erfolgreich freigegeben.\n");

	return 0;
}
