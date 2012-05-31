/*
 * Jan Tammen, os4
 * BSYS, Versuch 3
 *
 * Aufgabe 3.5.1.4
 *
 * Socketclient
 */ 
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netdb.h>
#define SRVPORT 2222 
#define BUFFER_SIZE 4096

int send_request(const int, const char*);
int dump_response(const int);

int main(int argc, char** argv)
{
    int sockfd;
    struct sockaddr_in srv_addr;

    if (argc < 2) {
	fputs("Aufruf: socketclient <Server-IP>\n", stderr);
	exit(EXIT_FAILURE);
    }
    
    if ( (inet_aton(argv[1], &srv_addr.sin_addr)) == 0) {
	// IP ueber gethostbyname() ermitteln
	struct hostent* host;
	if ( !(host = gethostbyname(argv[1])) ) {
	    perror("host");
	    exit(EXIT_FAILURE);
	}

	srv_addr.sin_addr = *(struct in_addr*)host->h_addr;
    }
    
    if ((sockfd = socket(PF_INET, SOCK_STREAM, 0)) == -1) {
        perror("socket");
        exit(EXIT_FAILURE);
    }

    srv_addr.sin_port = htons(SRVPORT);
    srv_addr.sin_family = AF_INET;

    printf("[Client] Connecting to %s:%i... ", argv[1], SRVPORT);

    // Zu Socket verbinden
    if ( connect(sockfd, (struct sockaddr*)& srv_addr, sizeof(srv_addr)) == -1) {
	perror("connect");
	exit(EXIT_FAILURE);
    }

    puts("ok!");

    // Requests senden
    send_request(sockfd, "VERSION");
    sleep(1);
    send_request(sockfd, "ID");
    sleep(1);
    send_request(sockfd, "END");

    // Antwort lesen und ausgeben
    dump_response(sockfd);

    close(sockfd);
    return 0;
}

int send_request(const int s, const char* r)
{
    if ( send(s, r, strlen(r), 0) == -1) {
	return -1;
    }

    return 0;
}

int dump_response(const int s)
{
    char response[BUFFER_SIZE];
    int n_bytes;

    while ( (n_bytes = recv(s, response, sizeof(response), 0)) > 0) {
	fwrite(response, 1, n_bytes, stdout);
    }
    
    if (n_bytes == -1) {
	perror("recv");
	return -1;
    }

    return 0;
}
