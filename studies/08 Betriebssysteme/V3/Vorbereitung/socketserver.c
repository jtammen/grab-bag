#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/wait.h>
#include <arpa/inet.h>
# define MYPORT 3490 /* Port Number, where the server listens */
# define BACKLOG 10 /* 10 Clients, no more */

/*
 * Offene Sockets anzeigen: 
 *   netstat --listening -p | grep socketserver
 */

int main(void)
{
    int sockfd, new_fd;
    struct sockaddr_in my_addr;
    struct sockaddr_in their_addr;
    int sin_size;

    if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
        perror("socket");
        exit(EXIT_FAILURE);
    }
    
    bzero(&my_addr, sizeof(my_addr));   // Leeren

    /* assign a name to the socket */
    my_addr.sin_family = AF_INET; /*host byte order */
    my_addr.sin_port = htons(MYPORT); /*short network byte order */
    my_addr.sin_addr.s_addr = INADDR_ANY; /* my IP*/
    //memset(my_addr.sin_zero,0,8); /*rest of struct = 0 */

    if (bind(sockfd, (struct sockaddr *)& my_addr, sizeof(my_addr)) == -1) {
        perror("bind");
        exit(EXIT_FAILURE);
    }
   
    // Socket in Listening-Mode versetzen
    if (listen(sockfd, BACKLOG) == -1) {
        perror("listen");
        exit(EXIT_FAILURE);
    }
    
    while(1) { /*main accept() loop */
        socklen_t their_size;
	their_size = sizeof(their_addr);
        
        if ((new_fd = accept(sockfd, (struct sockaddr *)& their_addr, &sin_size)) == -1) {
            perror("accept");
            continue;
        }
        
        printf("server: got connection from %s\n", inet_ntoa(their_addr.sin_addr));

        if ( !fork() ) {
            sleep (10);
            if ( send(new_fd, "Hello, you are on my self made Server!\r\n", 50, 0) == -1)
                perror("send");
		
            close(new_fd);
            exit(0);
        }
        
        close(new_fd);

        while( waitpid(-1,NULL,WNOHANG) > 0);
    }

    close(sockfd);
}
