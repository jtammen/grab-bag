CC=gcc
CFLAGS=-Wall -g

all: hadupipe fudupipe shmsem socketserver socketclient
	
%.o: %.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f *.o hadupipe fudupipe shmsem socketserver socketclient
