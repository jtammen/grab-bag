/*
 * Kopiert eine Datei in eine andere.
 *
 * Aufruf:
 *   filedd <quelldatei> <zieldatei> [<blockanzahl>]
 *
 * Jan Tammen, os4
 */

#include <unistd.h>	// Systemcalls
#include <fcntl.h>	// FILE

int main (int argc, char* argv[])
{
	// Mindestens Quell- und Zieldatei sind erforderlich
	if (argc < 3)
	{
		perror("Aufruf: filedd <quelldatei> <zieldatei> [<blockanzahl>]");
		return -1;
	}

	// Anzahl zu kopierender Bloecke
	int copyblocks = 0;
	if ( argc > 3 ) copyblocks = atoi(argv[3]);
	
	// Zieldatei zum Lesen oeffnen
	int src;
	if ( (src = open(argv[1], O_RDONLY)) == -1 )
	{
		perror("Konnte Quelldatei nicht oeffnen");
		return -1;
	}

	// Quelldatei zum Schreiben oeffnen, ggf. anlegen
	int dest;
	if ( (dest = open(argv[2], O_WRONLY | O_TRUNC | O_CREAT, 0644)) == -1)
	{
		perror("Konnte Zieldatei nicht oeffnen");
		return -1;
	}

	// Lesepuffer
	char buffer[1024];

	// Datei lesen und in Zieldatei schreiben, ggf. Blockbeschraenkung
	int count = 0;
	int current_block = 0;
	while ( ( count = read(src, buffer, 1024) ) )
	{
		if (copyblocks != 0 && current_block > copyblocks) break;
		write(dest, buffer, count);
		current_block++;
	}

	return 0;
}
