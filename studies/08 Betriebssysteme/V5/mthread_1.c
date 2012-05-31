/**
 * Jan Tammen, os4
 *
 * BSYS, Versuch 4
 * Aufgabe 4.3.2.1
 *
 * "Was passiert, wenn Sie die pthread_join Aufrufe nicht machen?"
 *   Die erstellten Threads können möglicherweise "PrintHello" 
 *   nicht aufrufen, da der erstellende Prozess nicht auf sie
 *   wartet und vorher beendet wird.
 */

#include <pthread.h>
#include <stdio.h>
#include <error.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;

void* PrintHello(void* threadid)
{
    pthread_mutex_lock(&lock);
    printf("%ld: Hello World!\n", (pthread_t)threadid);
    pthread_mutex_unlock(&lock);
    sleep((int) (rand() % 10) + 1);
    pthread_mutex_lock(&lock);
    printf("%ld: Thread is done\n", (pthread_t)threadid);
    pthread_mutex_unlock(&lock);

    /*********************************************/
    /* Thread exit code here to pass exit status */
    /*********************************************/
    pthread_exit((void*)0);
}

int main(int argc, char *argv[])
{
    int t; void* status;
    if (argc != 2) {
	printf("Usage: %s <#threads>\n", argv[0]);
	exit(1);
    }

    /********************************************/
    /* Initialise thread attributes here        */
    /********************************************/   
    int result;
    // Fuer m Threads
    pthread_t threads[atoi(argv[1])];
    // Attribute
    pthread_attr_t attr;
    // Attribut-Objekt initialisieren
    pthread_attr_init(&attr);
    // Threads sollen im joinable-state sein!
    pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);
    
    // Threads erstellen
    for (t = 0; t < atoi(argv[1]); t++) {
	pthread_mutex_lock(&lock);
	printf("Creating thread %d\n", t);
	pthread_mutex_unlock(&lock);
	
	/**********************************************************************/
	/* Your code must create a thread for each call to the following      */
	/* function                                                           */
	/* Replace the following function call with your thread creation code */
	/**********************************************************************/
	// Neuen Thread erstellen
	// pthread_create(Thread-ID, Attribute, Funktion, Argument)
        result = pthread_create(&threads[t], &attr, PrintHello, (void*)t);
	if ( result ) {
	    perror("pthread_create");
	    exit(EXIT_FAILURE);    
	}
    }

    // Auf Threads warten
    for (t = 0; t < atoi(argv[1]); t++) {
	pthread_mutex_lock(&lock);
	printf("Main joining with thread %d\n", t);
	pthread_mutex_unlock(&lock);

	/**********************************************************************/
	/* Allow the main thread to "join" each of the threads,               */
	/* and wait till the target thread completes                          */
	/**********************************************************************/
	// pthread_join(Thread-ID, Wert der vom Thread ueber pthread_exit uebergeben wurde)
	result = pthread_join(threads[t], &status);
	if ( result ) {
	    perror("pthread_join");
	    exit(EXIT_FAILURE);
	}
    
	pthread_mutex_lock(&lock);
	printf("Completed joining with thread %d status = %d\n", t, (int)status);
	pthread_mutex_unlock(&lock);
    }

    return 0;
}

