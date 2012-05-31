CC=gcc
CFLAGS=-Wall -g -O1
LDFLAGS=-lpthread -D_REENTRANT

all: mthread_1 mthread_2 surprise socketserver
	
mthread_1: mthread_1.o
	$(CC) $(CFLAGS) $(LDFLAGS) $< -o $@
	
mthread_2: mthread_2.o
	$(CC) $(CFLAGS) $(LDFLAGS) $< -o $@

surprise: surprise.o
	$(CC) $(CFLAGS) $(LDFLAGS) -DDEBUG $< -o $@

socketserver: socketserver.o
	$(CC) $(CFLAGS) $(LDFLAGS) $< -o $@

clean:
	rm -f *.o mthread_1 mthread_2 surprise socketserver
