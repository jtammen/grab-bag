# File: Makefile fuer Aufgabe1
# Author: Oliver Bittel
# Date: 6.2.2003

# Definitions:
CC = g++ -g   # -g wird nur für Debugger benötigt!
OBJ = mainAufgabe1.o set.o
BIN = aufg1

# Rules:
$(BIN): $(OBJ)
	$(CC) -o $(BIN) $(OBJ)
	
mainAufgabe1.o: mainAufgabe1.cpp set.h
	$(CC) -c mainAufgabe1.cpp

set.o: set.cpp set.h
	$(CC) -c set.cpp
