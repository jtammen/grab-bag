CC=gcc
CPP=g++
CFLAGS=-Wall -g

all: aufgabe6cppstatic aufgabe6cppdynamic aufgabe6cstatic aufgabe6cdynamic filedd dllist

aufgabe6cstatic: aufgabe6.c
	$(CC) $(CFLAGS) -static $< -o aufgabe6-c-static

aufgabe6cdynamic: aufgabe6.c
	$(CC) $(CFLAGS) $< -o aufgabe6-c-dynamic

aufgabe6cppstatic : aufgabe6.cpp
	$(CPP) $(CFLAGS) -static $< -o aufgabe6-cpp-static

aufgabe6cppdynamic: aufgabe6.cpp
	$(CPP) $(CFLAGS) $< -o aufgabe6-cpp-dynamic

filedd: filedd.c
	$(CC) $(CFLAGS) $< -o $@

dllist: dllist.c
	$(CC) $(CFLAGS) $< -o $@

clean:
	rm -f *.o aufgabe6 aufgabe6-* filedd dllist
