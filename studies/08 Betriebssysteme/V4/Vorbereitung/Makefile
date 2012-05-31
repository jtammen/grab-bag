CC=gcc
CFLAGS=-Wall -D_REENTRANT -lpthread -g

all: ex1 thread
	
%.o: %.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f *.o ex1 thread
