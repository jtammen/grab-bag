#include <stdio.h>
#include <pthread.h>
#include <time.h>
#include <unistd.h>
#include <stdlib.h>
void print_message_function(void *ptr);
int main(void)
{
    pthread_t thread1, thread2;
    char *message1 = "Ich bin Thread 1\n\n";
    char *message2 = "Ich bin Thread 2\n\n";
    pthread_create(&thread1, NULL,
            (void*)print_message_function, (void*) message1);
    pthread_create(&thread2, NULL,
            (void*)print_message_function, (void*) message2);
    sleep(1);
    exit(0);
}
void print_message_function(void *ptr)
{
    char *message;
    message = (char *) ptr;
    printf("%s", message);
}
