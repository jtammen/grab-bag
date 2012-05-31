CPP  = bcc32.exe
CXXFLAGS  = -g -ansi -pedantic -Wall # -static
OBJ  = main.obj Exception.obj BitFileIO.obj Huffman.obj Knoten.obj
LINKOBJ  = main.obj Exception.obj BitFileIO.obj Huffman.obj Knoten.obj
BIN = main
RM = del

.PHONY: all all-before all-after clean clean-custom

all: main


clean: 
	${RM} $(OBJ) $(BIN)

$(BIN): $(OBJ)
	$(CPP) $(LINKOBJ) -o "main" $(LIBS)

main.obj: main.cpp
	$(CPP) -c main.cpp -o main.obj $(CXXFLAGS)

Huffman.obj: Huffman.cpp
	$(CPP) -c Huffman.cpp -o Huffman.obj $(CXXFLAGS)

Knoten.obj: Knoten.cpp
	$(CPP) -c Knoten.cpp -o Knoten.obj $(CXXFLAGS)

bitio.obj: bitio.cpp
	$(CPP) -c bitio.cpp -o bitio.obj $(CXXFLAGS)

BitFileIO.obj: BitFileIO.cpp
	$(CPP) -c BitFileIO.cpp -o BitFileIO.obj $(CXXFLAGS)

Exception.obj: Exception.cpp
	$(CPP) -c Exception.cpp -o Exception.obj $(CXXFLAGS)
