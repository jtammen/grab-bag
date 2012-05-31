/*
 * Jan Tammen, os4
 * 
 * BSYS, Versuch 4
 * Aufgaben 4.1.2.2, 4.1.2.3
 * 
 * Shared Memory
 */

#include <sys/shm.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SHM_SIZE 1024
#define DELAY 100000
#define MAIN_LOOP 1000000000

// Prototypen
int open_segment(key_t, int);
char* attach_segment(int);
void write_to_shm(char*, const char*);

// Fuer Peterson-Algorithmus
void enter(int);
void leave(int);
int* turn;
int* processes;

int main(int argc, char** argv)
{
    // Neuen Schluessel fuer SHM-Segment besorgen: String
    key_t key_s = ftok("/home/os4/os4/V3/shmsem.c", 'S');
        
    // SHM-Segment erstellen: String
    int shmid_s = open_segment(key_s, SHM_SIZE);

    // Neuen Schluessel fuer SHM-Segment besorgen: Peterson
    key_t key_p = ftok("/home/os4/os4/V4/shmsem.c", 'P');
        
    int* myshm;	    // Pointer auf Beginn des Shared-Memory
    
    // SHM-Segment erstellen: Peterson
    int shmid_p = open_segment(key_p, SHM_SIZE);
   
    // Speichersegmente an Adressraum anhaegen 
    // -> Adresse wird zurueckgeliefert
    char* shmadr_s	= attach_segment(shmid_s);
    myshm = (int*) shmat(shmid_p, (void*)0, 0);
    
    // Initialisierung
    turn = &myshm[0];
    processes = &myshm[1];
    processes[0] = 0;
    processes[1] = 0;
    
    int i;
    for (i = 0; i < MAIN_LOOP; i++) {
    
        if ( (i%(MAIN_LOOP/100)) == 0 ) {
	    if (argc == 2) {
		
		// Producer, soll seltener schreiben
		if ( (i%(MAIN_LOOP/10)) == 0 ) {
		    enter(0);
		    write_to_shm(shmadr_s, argv[1]);	// KB
		    leave(0);
		}

	    } else {
		// Consumer
		enter(1);
		printf("SHM-Segment-Inhalt: %s\n", shmadr_s);	// KB
		leave(1);
	    }
	}
    }
   
    return 0;
}

// Shared-Memory-Segment kreieren/lokalisieren
int open_segment(key_t keyval, int size)
{
    int shmid;

    // 0666 = rw-rw-rw
    if ( (shmid = shmget(keyval, size, (SHM_R | SHM_W | IPC_CREAT) | 0666)) == -1) {
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
    int i = 0, j = 0, end = 0;

    // Shared Memory 'loeschen'
    end = (int)(SHM_SIZE/sizeof(' '));
    for (i=0; i < end; i++) {
	shmdata[i] = ' ';
    }
    printf("schreibe in das Speichersegment %x: %s\n", (int)shmdata, str);
    end = (int)(SHM_SIZE/sizeof(str));
    for (i = 0; i < end; i++) {
	// vor jedem 'Byte-Transfer' warten
	for(j = 0; j < DELAY; j++);
	shmdata[i] = str[i];
    }

    return;
    
    //printf("Schreibe nach %p folgenden String: %s\n", shmdata, str);
    //strncpy(shmdata, str, 1024);   // String in Speicher kopieren
}

// Peterson-Algorithmus: Kritischen Bereich (KB) betreten
void enter(int process_nr)
{
    int other_process_nr = 1-process_nr;    // Der "andere" Prozess
    processes[process_nr] = 1;		    // process_nr moechte KB betreten
    *turn = process_nr;			    // process_nr ist nun dran

    while ( (*turn == process_nr) && 
	    (processes[other_process_nr] == 1) );   // Warten
}

// Peterson-Algorithmus: Kritischen Bereich (KB) verlassen
void leave(int process_nr)
{
    processes[process_nr] = 0;		    // process_nr verlaesst den KB
}
