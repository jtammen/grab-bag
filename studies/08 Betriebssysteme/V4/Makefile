CC=gcc
CFLAGS=-Wall -g -O2
LDFLAGS=-lpthread -D_REENTRANT

all: shmsem mthread_1 mthread_2
	
mthread_1: mthread_1.o
	$(CC) $(CFLAGS) $(LDFLAGS) $< -o $@
	
mthread_2: mthread_2.o
	$(CC) $(CFLAGS) $(LDFLAGS) $< -o $@

shmsem: shmsem.o
	$(CC) $(CFLAGS) $< -o $@
clean:
	rm -f *.o shmsem mthread_1 mthread_2
