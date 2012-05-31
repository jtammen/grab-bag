// Damit Warning ueber pthread_setconcurrency verschwindet...
#define _GNU_SOURCE 
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

int n = 0, m = 0, w = 0, c = 0;
pthread_mutex_t lock;	// Mutex

/*
 * to be called as work(i, k) for any two integers i, k
 * in order to spend some time in computing overhead
 */
int work(int a, int b) {
    return a * b;
}

void* PrintHello(void* threadid)
{
    /************************************************/
    /* Print threadid m times                       */
    /*   Between each print, make w calls to work() */
    /* Thread exit code here to pass exit status    */
    /************************************************/
    int i,j;
    for (i = 0; i < m; ++i) {
	pthread_mutex_lock(&lock);	// Mutex locken
	printf("%ld", (pthread_t)threadid);
	pthread_mutex_unlock(&lock);	// Ausgabe fertig, unlocken

	// 'Warteschleife'
	for (j = 0; j < w; ++j) work(i, j);
    }

    pthread_exit((void*)0);
}

int main(int argc, char *argv[])
{
    int t, status;

    if (argc != 5) {
	printf("Usage: %s <#threads> <#prints> <#work> <#concurrent_threads>\n",
		argv[0]);
	exit(1);
    }

    n = atoi(argv[1]);	// Anzahl Threads
    m = atoi(argv[2]);	// Anzahl Ausgabe Thread-ID
    w = atoi(argv[3]);	// Anzahl Aufrufe work
    c = atoi(argv[4]);	// Concurrency level

    /********************************************/
    /* Initialise thread attributes here        */
    /* Set concurrency level to c               */
    /********************************************/   
    int result;
    // Fuer n Threads
    pthread_t threads[n];
    // Attribute
    pthread_attr_t attr;
    // Attribut-Objekt initialisieren
    pthread_attr_init(&attr);
    // Threads sollen im joinable-state sein!
    pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);
    // Concurrency level setzen
    pthread_setconcurrency(c);

    // Threads erzeugen
    for (t = 0; t < n; t++) {
	printf("Creating thread %d\n", t);

	/**********************************************************************/
	/* Your code must create a thread for each call to the following      */
	/* function                                                           */
	/* Replace the following function call with your thread creation code */
	/**********************************************************************/
	// Neuen Thread erstellen
	result = pthread_create(&threads[t], &attr, PrintHello, (void*)t);
	if ( result ) {
	    perror("pthread_create");
	    exit(EXIT_FAILURE);    
	}
    }

    // Auf Threads warten
    for(t = 0; t < atoi(argv[1]); t++) {
	printf("Main joining with thread %d\n", t);

	/**********************************************************************/
	/* Allow the main thread to "join" each of the threads,               */
	/* and wait till the target thread completes                          */
	/**********************************************************************/
	result = pthread_join(threads[t], (void*)&status);
	if ( result ) {
	    perror("pthread_join");
	    exit(EXIT_FAILURE);
	}
	
	printf("\nCompleted joining with thread %d status = %d\n", t, status);
    }

    return 0;
}
