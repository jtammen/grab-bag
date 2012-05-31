/*
 * Jan Tammen, os4
 * BSYS, Versuch 3
 *
 * Aufgabe 3.5.1.1, 3.5.1.3
 *
 * Socketserver (Basis: Skript zu den Uebungsaufgaben)
 */ 
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

int main(void)
{
    pid = getpid();
    
    // Signalhandler installieren
    signal(SIGINT, (void*)handle_sig_int);
    signal(SIGCHLD, (void*)handle_sig_child);

    int new_pid;
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
   
    // Socket in Listening-Mode versetzen
    if (listen(sock_server, BACKLOG) == -1) {
        perror("listen");
        exit(EXIT_FAILURE);
    }
    
    for (;;) { /*main accept() loop */
	their_size = sizeof(their_addr);
        
        if ((sock_client = accept(sock_server, (struct sockaddr *)& their_addr, &their_size)) == -1) {
            perror("accept");
            continue;
        }
        
        printf("[Server] Got connection from %s\n", 
		inet_ntoa(their_addr.sin_addr));

	// Kindprozess fuer Client starten
        switch ( new_pid = fork() ) {
	    case -1:
		perror("fork");
		exit(EXIT_FAILURE);
	    case 0:	// Im Kind-Prozess -> Client bedienen
		send_motd(sock_client);
		serve_client(sock_client);
		exit(EXIT_SUCCESS);
	}

        close(sock_client);
    }

    close(sock_server);
}

// Begruessungsnachricht an den Client senden
void send_motd(int c)
{
    char* msg = "###   Welcome to my selfmade server!   ###\n### Allowed Commands: VERSION, ID, END ###\n";
    send(c, msg, strlen(msg), 0);
}

// Befehle des Clients empfangen
// Wird ein nicht bekannter Befehl eingegeben, so wird
// dieser unveraendert an den Client zurueckgeschickt
void serve_client(int c)
{
    char buffer[BUFFER_SIZE];
    memset(&buffer, 0, sizeof(buffer));
    int n_bytes;
   
    while ( (n_bytes = recv(c, buffer, sizeof(buffer), 0)) > 0) {
	//printf("[Server] Client command: %s, Length: %d\n", buffer, n_bytes);
	
	// CRLF entfernen -> Wird bei Telnet mitgesendet!
	if (strtok(buffer, "\r\n")) {
	    buffer[strlen(buffer)] = '\0';
	}

	// Befehl untersuchen
	if ( strncmp(buffer, "VERSION", BUFFER_SIZE) == 0) {   // Kernel-Ver.
	    char procbuf[1024];
	    FILE* procfd = fopen("/proc/version", "r");
	    while ( !feof(procfd) ){
		fread(&procbuf, 1, 1, procfd);
		send(c, procbuf, strlen(procbuf), 0);
	    }
	    memset(&buffer, 0, sizeof(buffer));

	} else if (strncmp(buffer, "ID", BUFFER_SIZE) == 0) {  // Prozess-ID
	    char response[30];
	    sprintf(response, "PID des Servers: %d\n", getpid());
	    send(c, response, strlen(response), 0);
	    memset(&buffer, 0, sizeof(buffer));
	    
	} else if (strncmp(buffer, "END", BUFFER_SIZE) == 0) { // Beenden
	    send(c, "Goodbye.\n", 10, 0);
	    close(c);
	} else {					    // unbekannt
	    printf("[Server] Client sent unknown command: %s\n", buffer);
	    memset(&buffer, 0, sizeof(buffer));
	}
    }
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
