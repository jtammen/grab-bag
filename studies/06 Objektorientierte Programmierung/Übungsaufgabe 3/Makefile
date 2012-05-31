CPP  = bcc32.exe
#CPP = g++
CXXFLAGS  = -ansi -pedantic -Wall -g #-static
OBJ  = main.obj Exception.obj Daten.obj Anwendung.obj Bestellung.obj Kunde.obj Position.obj Artikel.obj Pizza.obj Eis.obj Eiskugel.obj Nudeln.obj Limo.obj Bier.obj Artikelliste.obj
LINKOBJ  = main.obj Exception.obj Daten.obj Anwendung.obj Bestellung.obj Kunde.obj Position.obj Artikel.obj Pizza.obj Eis.obj Eiskugel.obj Nudeln.obj Limo.obj Bier.obj Artikelliste.obj
BIN = main
RM = rm

.PHONY: all all-before all-after clean clean-custom

all: main


clean: 
	${RM} $(OBJ) $(BIN).exe

$(BIN): $(OBJ)
	$(CPP) $(LINKOBJ) -o "main" $(LIBS)

main.obj: main.cpp
	$(CPP) -c main.cpp -o main.obj $(CXXFLAGS)

Exception.obj: Exception.cpp
	$(CPP) -c Exception.cpp -o Exception.obj $(CXXFLAGS)

Daten.obj: Daten.cpp
	$(CPP) -c Daten.cpp -o Daten.obj $(CXXFLAGS)

Anwendung.obj: Anwendung.cpp
	$(CPP) -c Anwendung.cpp -o Anwendung.obj $(CXXFLAGS)

Bestellung.obj: Bestellung.cpp
	$(CPP) -c Bestellung.cpp -o Bestellung.obj $(CXXFLAGS)

Kunde.obj: Kunde.cpp
	$(CPP) -c Kunde.cpp -o Kunde.obj $(CXXFLAGS)

Position.obj: Position.cpp
	$(CPP) -c Position.cpp -o Position.obj $(CXXFLAGS)

Artikel.obj: Artikel.cpp
	$(CPP) -c Artikel.cpp -o Artikel.obj $(CXXFLAGS)

Pizza.obj: Pizza.cpp
	$(CPP) -c Pizza.cpp -o Pizza.obj $(CXXFLAGS)

Eis.obj: Eis.cpp
	$(CPP) -c Eis.cpp -o Eis.obj $(CXXFLAGS)

Eiskugel.obj: Eiskugel.cpp
	$(CPP) -c Eiskugel.cpp -o Eiskugel.obj $(CXXFLAGS)

Nudeln.obj: Nudeln.cpp
	$(CPP) -c Nudeln.cpp -o Nudeln.obj $(CXXFLAGS)

Limo.obj: Limo.cpp
	$(CPP) -c Limo.cpp -o Limo.obj $(CXXFLAGS)

Bier.obj: Bier.cpp
	$(CPP) -c Bier.cpp -o Bier.obj $(CXXFLAGS)

Artikelliste.obj: Artikelliste.cpp
	$(CPP) -c Artikelliste.cpp -o Artikelliste.obj $(CXXFLAGS)
