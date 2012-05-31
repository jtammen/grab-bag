/*
 * Jan Tammen, os4
 * BSYS, Versuch 5
 *
 * Aufgabe 5.3.1.3
 *
 * multithreaded Socketserver
 *
 * http://cboard.cprogramming.com/archive/index.php/t-66189.html
 */ 
#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/wait.h>
#include <arpa/inet.h>
#include <ctype.h>
#include <pthread.h>
#define MYPORT 2222 /* Port Number, where the server listens */
#define BACKLOG 10 /* 10 Clients, no more */
#define BUFFER_SIZE 4096 

// Prototypen
void send_motd(int);
void serve_client(int);
void handle_sig_int(int);
void handle_sig_child(int);

// Server-/Client-Socket und PID des Vaterprozesses global machen
int sock_server, pid;
int sock_client;

// Puffer - fuer Aufgabe 5.3.1.5
char globalbuffer[5][128];
int buffercount = 0;

// Mutex und Condition-Variablen
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t items_available = PTHREAD_COND_INITIALIZER;
pthread_cond_t space_available = PTHREAD_COND_INITIALIZER;

int main(void)
{
    pid = getpid();
    
    // Signalhandler installieren
    signal(SIGINT, (void*)handle_sig_int);
    signal(SIGCHLD, (void*)handle_sig_child);

    struct sockaddr_in my_addr;
    struct sockaddr_in their_addr;
    socklen_t their_size;

    if ((sock_server = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        perror("socket");
        exit(EXIT_FAILURE);
    }
    
    /* assign a name to the socket */
    my_addr.sin_family = AF_INET; /*host byte order */
    my_addr.sin_port = htons(MYPORT); /*short network byte order */
    my_addr.sin_addr.s_addr = INADDR_ANY; /* my IP*/
    memset(my_addr.sin_zero,0,8); /*rest of struct = 0 */

    // Socket an Adresse und Port binden
    if (bind(sock_server, (struct sockaddr *)& my_addr, sizeof(my_addr)) == -1) {
        perror("bind");
        exit(EXIT_FAILURE);
    }
   
//    pthread_setconcurrency(4);
    
    // Socket in Listening-Mode versetzen
    if (listen(sock_server, BACKLOG) == -1) {
        perror("listen");
        exit(EXIT_FAILURE);
    }
    
    pthread_t client_thread;
    for (;;) { /*main accept() loop */
	their_size = sizeof(their_addr);
        
        if ((sock_client = accept(sock_server, (struct sockaddr *)& their_addr, &their_size)) == -1) {
            perror("accept");
            continue;
        }
        
        printf("[Server] Got connection from %s\n", 
		inet_ntoa(their_addr.sin_addr));

		// Neuen Thread fuer Client erstellen
		pthread_create(&client_thread, 
				NULL, 
				(void*)serve_client, 
				(void*)sock_client);
		pthread_detach(client_thread);
		//close(sock_client);
    }

	// Aufraeumen
	pthread_mutex_destroy(&mutex);
	pthread_cond_destroy(&items_available);
	pthread_cond_destroy(&space_available);
    return(EXIT_SUCCESS);
}

// Begruessungsnachricht an den Client senden
void send_motd(int c)
{
    char* msg = "###   Welcome to my selfmade server!   "
				"###\n### Allowed Commands: "
				"VERSION, ID, END, SEND, RECEIVE ###\n";
    send(c, msg, strlen(msg), 0);
}

// Befehle des Clients empfangen
void serve_client(int c)
{
    char buffer[BUFFER_SIZE];
    memset(&buffer, 0, sizeof(buffer));
    int n_bytes;

    send_motd(c);
    
    while ( (n_bytes = recv(c, buffer, sizeof(buffer), 0)) > 0) {
		// CRLF entfernen -> Wird bei Telnet mitgesendet!
		if (strtok(buffer, "\r\n")) {
			buffer[strlen(buffer)] = '\0';
		}

		printf("[Server] Got command: %s\n", buffer);
		
		// Befehl untersuchen
		if ( strncmp(buffer, "VERSION", BUFFER_SIZE) == 0) {   // Kernel-Ver.
			char procbuf[1024];
			memset(&procbuf, 0, sizeof(procbuf));
			FILE* procfd = fopen("/proc/version", "r");
			while ( !feof(procfd) ){
				fread(&procbuf, 1, 1, procfd);
				send(c, procbuf, strlen(procbuf), 0);
			}

		} else if (strncmp(buffer, "ID", BUFFER_SIZE) == 0) {  // Prozess-ID
			char response[30];
			memset(&response, 0, sizeof(response));
			sprintf(response, "PID des Servers: %d\n", getpid());
			send(c, response, strlen(response), 0);

		} else if (strncmp(buffer, "END", BUFFER_SIZE) == 0) { // Beenden
			send(c, "Goodbye.\n", 10, 0);
			close(c);

		} else if (strncmp(buffer, "SEND", strlen("SEND")) == 0) { // Daten empfangen
			// Producer

			char* data = strstr(buffer, " ")+1; 		// +1 wegen des Leerzeichens
			if (data == NULL) break;
			printf("[Server] Producer: Position %i, Daten %s\n", buffercount, data);

			pthread_mutex_lock(&mutex);					// Puffer sperren
			while (buffercount == 5) {					// Puffer ist voll, warten auf Consumer
				printf("[Server] Buffer is full, waiting for consumption...\n");
				pthread_cond_wait(&space_available, &mutex);
			}

			int copylen = strlen(data);
			//memset(&globalbuffer[buffercount], 0, sizeof(globalbuffer[buffercount]));	// Leeren
			strncpy(globalbuffer[buffercount++], data, copylen);
			pthread_cond_signal(&items_available);		// Benachrichtigen, dass Inhalt da ist
			pthread_mutex_unlock(&mutex);
			send(c, "OK\n", strlen("OK\n"), 0);
			
		} else if (strncmp(buffer, "RECEIVE", BUFFER_SIZE) == 0) { // Daten senden
			pthread_mutex_lock(&mutex);
			while (buffercount == 0) {					// Puffer ist leer, warten auf Producer
				printf("[Server] Buffer is empty, waiting for production...\n");
				pthread_cond_wait(&items_available, &mutex);
			}
			buffercount--;
			printf("[Server] Consumer: Position %d\n", buffercount);
			send(c, globalbuffer[buffercount], strlen(globalbuffer[buffercount]), 0);
			send(c, "\n", 3, 0);
			pthread_cond_signal(&space_available);		// Benachrichtigen, dass wieder Platz frei ist
			pthread_mutex_unlock(&mutex);
		} else {					    // unbekannt
			printf("[Server] Client sent unknown command: %s\n", buffer);
		}

		memset(&buffer, 0, sizeof(buffer));
	}


    return;
}

// Signal-Handler
void handle_sig_int(int s)
{
    // Von den Clients verabschieden (werden von Kind-Prozess gehandelt)
    if (getpid() != pid) {
		send(sock_client, "Server going down now...\n", 30, 0);
		close(sock_client);
    }
    
    shutdown(sock_server, 0);
    exit(EXIT_SUCCESS);
}

// Signal-Handler fuer Child-Tod -> Damit keine Zombies entstehen
void handle_sig_child(int s)
{
    waitpid(-1, NULL, WNOHANG);
}
