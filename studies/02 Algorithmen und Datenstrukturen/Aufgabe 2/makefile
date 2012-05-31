# File: Makefile fuer Aufgabe1
# Author: Oliver Bittel
# Date: 6.2.2003

# Definitions:
CC = g++ -g   # -g wird nur für Debugger benötigt!
OBJ = main.o Bibliothek.o
BIN = aufg2

# Rules:
$(BIN): $(OBJ)
	$(CC) -o $(BIN) $(OBJ)
	
main.o: main.cpp Bibliothek.h
	$(CC) -c main.cpp

Bibliothek.o: Bibliothek.cpp Bibliothek.h
	$(CC) -c Bibliothek.cpp

Benutzer.o: Benutzer.cpp Benutzer.h
	$(CC) -c Benutzer.cpp

Buch.o: Buch.cpp Buch.h
	$(CC) -c Buch.cpp
