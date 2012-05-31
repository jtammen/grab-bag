/*
 * Surprise
 */
#include <pthread.h>
#include <sched.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include "errors.h"

#define ITERATIONS 10

/*
 * Initialize a static array 
 */
#define NUM_MUTEX 10 
pthread_mutex_t mutex[NUM_MUTEX] = {
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER
    };

#define DELAY 1000000
unsigned long delay=0;

/*
 * This is a thread start routine 
 */
void *lock_A (void *arg)
{
    int i, iterate, backoffs;
    int status;
    pthread_t tid = pthread_self();

    for (iterate = 0; iterate < ITERATIONS; iterate++) {
	printf("for-loop: %d in thread %d", iterate, tid);
        backoffs = 0;
        for (i = 0; i < NUM_MUTEX; i++) {
	    // Delay
	    for (delay=0; delay<= DELAY;delay++) {
	    }
            if (i == 0) {
                status = pthread_mutex_lock (&mutex[i]);
                if (status != 0)
                    err_abort (status, "First lock");
            } 
	    else {
            status = pthread_mutex_lock (&mutex[i]);
            if (status != 0)
                err_abort (status, "Lock mutex");
            DPRINTF ((" A locker got %d\n", i));
            }
            sleep (1);
                      
        }
        /*
         * Report that we got 'em, and unlock to try again.
         */
        printf ("A got all locks, \n");
	for (i=0; i<NUM_MUTEX;i++) {
        	pthread_mutex_unlock (&mutex[i]);
	}
        sched_yield ();
    }
    return NULL;
}

/*
 * This is a thread start routine 
 * 
 */
void *lock_B (void *arg)
{
    int i, iterate;
    int status;

    for (iterate = 0; iterate < ITERATIONS; iterate++) {
        for (i = NUM_MUTEX-1; i >= 0; i--) {
            if (i == NUM_MUTEX-1) {
                status = pthread_mutex_lock (&mutex[i]);
                if (status != 0)
                    err_abort (status, "First lock");
            } else {
                status = pthread_mutex_lock (&mutex[i]);
                if (status != 0)
                        err_abort (status, "Lock mutex");
                DPRINTF ((" B locker got %d\n", i));
            }
            sleep (1);
                       
        }
        /*
         * Report that we got 'em, and unlock to try again.
         */
        printf("B got all locks\n");
	for (i=0; i<NUM_MUTEX;i++) {
        	pthread_mutex_unlock (&mutex[i]);
	}
        sched_yield ();
    }
    return NULL;
}

int main (int argc, char *argv[])
{
    pthread_t A, B;
    int status;

    status = pthread_create (&A, NULL, lock_A, NULL);
    if (status != 0)
        err_abort (status, "Create A");
    status = pthread_create (&B, NULL, lock_B, NULL);
    if (status != 0)
        err_abort (status, "Create B");
    pthread_exit (NULL);
}
