# Definitions:
CC  = g++ -g
OBJ = main.o #quickSort.o
BIN = aufg5

# Rules:
$(BIN): $(OBJ)
	$(CC) -o $(BIN) $(OBJ)

main.o: main.cpp Data.h
	$(CC) -c main.cpp

#quicksort.o: quickSort.cpp
#	$(CC) -c quickSort.cpp

clean: 
	rm -rf *.o aufg5.exe
