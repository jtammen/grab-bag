CPP  = bcc32.exe
CXXFLAGS  = -g -ansi -pedantic -Wall # -static
OBJ  = main.obj SourceCompressor.obj Exception.obj
LINKOBJ  = main.obj SourceCompressor.obj Exception.obj
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

SourceCompressor.obj: SourceCompressor.cpp
	$(CPP) -c SourceCompressor.cpp -o SourceCompressor.obj $(CXXFLAGS)

Exception.obj: Exception.cpp
	$(CPP) -c Exception.cpp -o Exception.obj $(CXXFLAGS)
