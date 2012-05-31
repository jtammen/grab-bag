#include <stdio.h>
#define MAXSTRING 5
int main(void)
{
    int counter;
    FILE *pipe_fp;
    char *strings[MAXSTRING] = {"echo","bravo","alpha","charlie","delta"};
    if ((pipe_fp = popen("sort", "w")) == NULL)
    {
	perror("popen");
	exit(1);
    }
    for(counter=0; counter < MAXSTRING; counter++) {
	fputs(strings[counter], pipe_fp);
	fputc('\n', pipe_fp);
    }
    pclose(pipe_fp);
    return(0);
}
