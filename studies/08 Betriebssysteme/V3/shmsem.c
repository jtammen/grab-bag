/*
 * Jan Tammen, os4
 * 
 * BSYS, Versuch 3
 * Aufgabe 3.4.2.1, 3.4.2.5
 * 
 * Shared Memory
 */

#include <sys/shm.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Prototypen
int open_segment(key_t, int);
char* attach_segment(int);
void write_to_shm(char*, const char*);
void clear_shm(char*);

int main(int argc, char** argv)
{
    /* Kernel Systemfunktion zum Erzeugen eines SHM-Objekts
     *   int sys_shmget(key_t key, size_t size, int shmflg);
     *   int shmget(key_t key, int size, int shmflg);
     */
   
    // Neuen Schluessel fuer SHM-Segment besorgen
    key_t key = ftok("/home/os4/os4/V3/sharedmem.c", 'A');
        
    // 1k grosses SHM-Segment erstellen
    int shmid = open_segment(key, 1024);

    // Aufgabe 3.4.2.5
    // <Neue Semaphore anlegen bzw. bestehende holen>
    // <Semaphore initialisieren>
    
    // Speichersegment an Adressraum anhaegen 
    // -> Adresse wird zurueckgeliefert
    char* shmadr = attach_segment(shmid);
    printf("Adresse: %p\n", shmadr);

    if (argc < 2) { // Ohne Argument -> Ausgeben des Inhalts
	// Aufgabe 3.4.2.5
	// <Semaphorenoperation: wait()> (Consumer wartet auf Producer)
	
	printf("SHM-Segment-Inhalt: %s\n", shmadr);
	
	// Aufgabe 3.4.2.5
	// <Semaphorenoperation: signal()> (Consumer gibt frei)
    } else {	    // Mit Argument -> String im Speicher ablegen
	// Aufgabe 3.4.2.5
	// <Semaphorenoperation: wait()> (Producer sperrt)
	
	write_to_shm(shmadr, argv[1]);
	
	// Aufgabe 3.4.2.5
	// <Semaphorenoperation: signal()> (Producer gibt frei)
    }
   
    return 0;
}

// Shared-Memory-Segment kreieren/lokalisieren
int open_segment(key_t keyval, int size)
{
    int shmid;

    // 0666 = rw-rw-rw
    if ( (shmid = shmget(keyval, size, IPC_CREAT | 0666)) == -1) {
	return(-1);
    }

    return shmid;
}

// Speichersegment an Adressraum anhaengen
char* attach_segment(int shmid)
{
    return shmat(shmid, 0, 0);
}

// In SHM-Segment schreiben
void write_to_shm(char* shmdata, const char* str)
{
    //printf("Schreibe nach %p folgenden String: %s\n", shmdata, str);
    strncpy(shmdata, str, 1024);   // String in Speicher kopieren
}

// Den String aus dem SHM-Segment ueberschreiben
void clear_shm(char* shmdata)
{
    memset(shmdata, 0, 1024);
}
