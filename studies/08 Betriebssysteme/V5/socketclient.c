/*
 * Jan Tammen, os4
 * BSYS, Versuch 5
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
#include <time.h>
#define SRVPORT 2222 
#define BUFFER_SIZE 128

int send_request(const int, const char*);
int dump_response(const int);
char* get_random_string(size_t max_len);

int main(int argc, char** argv)
{
    int sockfd;
    struct sockaddr_in srv_addr;
    int num_requests;
    int modus = 0;
    struct timespec sleeptime;
    sleeptime.tv_sec = 0;
    sleeptime.tv_nsec = 100000000L;

    if (argc < 4) {
	    fputs("Aufruf: socketclient <Server-IP> <Anzahl-Requests> <Modus: 0|1>\n", stderr);
    	exit(EXIT_FAILURE);
    }
  
	// Parameter setzen
	num_requests = atoi(argv[2]);
	modus = atoi(argv[3]);
	
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

	size_t data_length = 0;
    while (num_requests > 0) {
		srand(time(0));
		
		// Modus zufaellig setzen
		//modus = (rand() % 2);
			
        switch (modus) {
            case 0:     // SEND
				data_length = (rand() % 31)+1;
				char* data = get_random_string(data_length);
                char* cmdstring;
				cmdstring = malloc(5 + strlen(data));
                sprintf(cmdstring, "SEND %s", data);
                send_request(sockfd, cmdstring);
                break;

            case 1:     // RECEIVE
                send_request(sockfd, "RECEIVE\r\n");
                dump_response(sockfd);
				send_request(sockfd, "END\r\n");
                break;
        }

		nanosleep(&sleeptime, NULL);
        num_requests--;
    }

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
		//printf("read %i bytes...\n", n_bytes);
		//printf("%s", response);
    }
    
    if (n_bytes == -1) {
		perror("recv");
		return EXIT_FAILURE;
    }

    return 0;
}

char* get_random_string(size_t max_len)
{
	int i;
	char *res;
	char alphabet[] = "abcdefghijklmnopqrstuvwxyz"
					  "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                  "1234567890";
	size_t alphabet_size = sizeof(alphabet);
	unsigned int random_int = 0;

	res = malloc(max_len + 1);
	if (res) {
		for (i = 0; i < max_len; i++) {
			// Zufallszahl zw. 0 und alphabet_size-1 erstellen
			random_int = rand() % (1+alphabet_size-1);
			res[i] = alphabet[random_int];
		}
		res[i] = 0;
	}
	return res;
} 
