#
# Makefile
#
# Autor: H.Drachenfels
# Erstellt am: 14.11.2003
#

CXX = g++
CXXFLAGS = -g -Wall -ansi -pedantic

OBJECTS = Aufgabe4.o FachNote.o FachNotenListe.o

Aufgabe4: $(OBJECTS)
	$(CXX) $(CXXFLAGS) -o Aufgabe4 $(OBJECTS)

clean:
	rm -f Aufgabe4 $(OBJECTS)

Aufgabe4.o: Aufgabe4.cpp FachNote.h FachNotenListe.h
FachNote.o: FachNote.cpp FachNote.h
FachNotenListe.o: FachNotenListe.cpp FachNote.h FachNotenListe.h

