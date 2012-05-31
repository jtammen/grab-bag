CC=gcc
CFLAGS=-Wall -g

all: socketserver seminit semdemo semrm pipe2 shmtest
	
%.o: %.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f *.o socketserver seminit semdemo semrm pipe2 shmtest
