# Definitions:
CPP 	 = g++ -g -Wall -ansi -pedantic

# Rules:
teil1: teil1.o
	$(CPP) teil1.o -o teil1

teil1.o: teil1.cpp
	$(CPP) -c teil1.cpp -o teil1.o

teil2: teil2.o
	$(CPP) teil2.o -o teil2

teil2.o: teil2.cpp
	$(CPP) -c teil2.cpp -o teil2.o

teil3: teil3.o
	$(CPP) teil3.o -o teil3

teil3.o: teil3.cpp
	$(CPP) -c teil3.cpp -o teil3.o

clean:
	rm -f *.o
