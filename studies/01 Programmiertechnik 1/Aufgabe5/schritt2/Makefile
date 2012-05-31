#
# Makefile
#
# Autor: H.Drachenfels
# Erstellt am: 14.11.2003
#

CXX = g++
CXXFLAGS = -g -W -Wall -Werror -ansi -pedantic

OBJECTS = Aufgabe5.o Notenspiegel.o FachNotenListe.o FachNote.o FalscheNote.o

Aufgabe5: $(OBJECTS)
	$(CXX) $(CXXFLAGS) -o Aufgabe5 $(OBJECTS)

clean:
	rm -f Aufgabe5 $(OBJECTS)

Aufgabe5.o: Aufgabe5.cpp Notenspiegel.h
Notenspiegel.o: Notenspiegel.cpp FachNotenListe.h FachNote.h
FachNotenListe.o: FachNotenListe.cpp FachNotenListe.h FachNote.h
FachNote.o: FachNote.cpp FachNote.h
FalscheNote.o: FalscheNote.cpp FalscheNote.h

