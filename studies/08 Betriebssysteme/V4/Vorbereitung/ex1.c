#include <stddef.h>
#include <stdio.h>
#include <unistd.h>
#include <pthread.h>

void* process(void * arg)
{
    int i;
    fprintf(stderr, "Starte Prozess %s\n", (char *) arg);
    for (i = 0; i < 10000; i++) {
        write(1, (char *) arg, 1);
    }
    printf("%s ist fertig\n", (char *) arg);
    return NULL;
}

int main()
{
    int erg;
    pthread_t thread1, thread2;
    void * status; /* Exit Status wird hierein geschrieben */
    erg = pthread_create(&thread1, NULL, process, "a");
    if (erg != 0) fprintf(stderr, "create a failed %d\n", erg);
    erg = pthread_create(&thread2, NULL, process, "b");
    if (erg != 0) fprintf(stderr, "create b failed %d\n", erg);
    erg = pthread_join(thread1,&status );
    if (erg != 0) fprintf(stderr, "join a failed %d\n", erg);
    erg = pthread_join(thread2,&status );
    if (erg != 0) fprintf(stderr, "join b failed %d\n", erg);
    return 0;
}
