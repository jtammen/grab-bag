/*
 * Jan Tammen, os4
 * 
 * BSYS, Versuch 3
 * 
 * SharedMemory Vorbereitung
 */
#define _SVID_SOURCE

#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

int open_segment(key_t, int);
char* attach_segment(int);

int main()
{
    /* Kernel Systemfunktion zum Erzeugen eines SHM-Objekts
     *   int sys_shmget(key_t key, size_t size, int shmflg);
     *   int shmget(key_t key, int size, int shmflg);
     */
   
    // Neuen Schluessel fuer SHM besorgen
    key_t key = ftok("/home/os4/os4/V3/Vorbereitung/shmtest.c", 'A');
        
    // SHM-Segment erstellenSempahor mit Schreibrechten fuer alle erstellen
    int shmid = open_segment(key, 1024);

    // Speichersegmet an Adressraum anhaegen -> Adresse wird zurueckgeliefert
    char* shmadr = attach_segment(shmid);
    printf("Adresse: %p\n", shmadr);
    
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

// Shared-Memory-Segment kreieren/lokalisieren
int open_segment(key_t keyval, int segsize)
{
    int shmid;

    // 660 = rw-rw---
    if ( (shmid = shmget(keyval, segsize, IPC_CREAT | 0660)) == -1) {
	return(-1);
    }

    return shmid;
}

char* attach_segment(int shmid)
{
    return shmat(shmid, 0, 0);
}
