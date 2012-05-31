CPP  = g++.exe
CXXFLAGS  = -g -ansi -pedantic -Wall # -static
OBJ  = main.o MyDouble.o MyNumber.o Exception.o
LINKOBJ  = main.o MyDouble.o MyNumber.o Exception.o
BIN = Aufgabe1
RM = rm -f

.PHONY: all all-before all-after clean clean-custom

all: all-before Aufgabe1 all-after


clean: clean-custom
	${RM} $(OBJ) $(BIN)

$(BIN): $(OBJ)
	$(CPP) $(LINKOBJ) -o "Aufgabe1" $(LIBS)

main.o: main.cpp
	$(CPP) -c main.cpp -o main.o $(CXXFLAGS)

MyDouble.o: MyDouble.cpp
	$(CPP) -c MyDouble.cpp -o MyDouble.o $(CXXFLAGS)

MyNumber.o: MyNumber.cpp
	$(CPP) -c MyNumber.cpp -o MyNumber.o $(CXXFLAGS)

Exception.o: Exception.cpp
	$(CPP) -c Exception.cpp -o Exception.o $(CXXFLAGS)
