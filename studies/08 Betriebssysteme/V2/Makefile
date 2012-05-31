CC=gcc
CFLAGS=-Wall -g

all: ReadProc doFork1 doFork2 myExec createZombie

%.o: %.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f *.o ReadProc doFork1 doFork2 myExec createZombie
