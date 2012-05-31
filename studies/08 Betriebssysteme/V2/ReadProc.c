/*
 * Programmnamen und Eltern-Programmname ausgeben.
 *
 * Jan Tammen, os4
 */

#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main (void)
{
    pid_t pid, ppid;
    FILE* proc_cmdline;	    // Mein Prozess
    FILE* proc_pcmdline;    // Elternprozess
    
    // Informationen zum aktuellen Prozess ermitteln
    pid = getpid();

    // Buffer-Overflow-anfaellig...
    char filename_cmdline[512];
    sprintf(filename_cmdline, "/proc/%d/cmdline", pid);
    proc_cmdline = fopen(filename_cmdline, "r");

    char buffer[1024];
    while ( !feof(proc_cmdline) ) {
	fread(&buffer, 1, 1, proc_cmdline);
	printf(buffer);
    }
    printf("\n");
    fclose(proc_cmdline);

    // Informationen zum Elternprozess ermitteln
    ppid = getppid();

    char filename_pcmdline[512];
    sprintf(filename_pcmdline, "/proc/%d/cmdline", ppid);
    proc_pcmdline = fopen(filename_pcmdline, "r");

    char pbuffer[1024];
    while ( !feof(proc_pcmdline) ) {
	fread(&pbuffer, 1, 1, proc_pcmdline);
	printf(pbuffer);
    }
    printf("\n");
    fclose(proc_pcmdline);

    return 0;
}
