# Definitions:
CC  = g++ -g
OBJ = main.o Dictionary.o
BIN = aufg4

# Rules:
$(BIN): $(OBJ)
	$(CC) -o $(BIN) $(OBJ)

main.o: main.cpp Dictionary.h
	$(CC) -c main.cpp

Dictionary.o: Dictionary.cpp Dictionary.h
	$(CC) -c Dictionary.cpp
