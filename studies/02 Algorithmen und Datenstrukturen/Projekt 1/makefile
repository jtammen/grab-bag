# File: Makefile fuer Aufgabe1
# Author: Oliver Bittel
# Date: 6.2.2003

# Definitions:
CPP 	= g++ -g -Wall -ansi -pedantic
OBJ 	= EventList.o SimulationMain.o 
LINKOBJ = EventList.o SimulationMain.o
BIN 	= projekt1

# Rules:
$(BIN): $(OBJ)
	$(CPP) $(LINKOBJ) -o projekt1

EventList.o: EventList.cpp
	$(CPP) -c EventList.cpp -o EventList.o

SimulationMain.o: SimulationMain.cpp
	$(CPP) -c SimulationMain.cpp -o SimulationMain.o

clean:
	rm -f $(OBJ) $(BIN)
