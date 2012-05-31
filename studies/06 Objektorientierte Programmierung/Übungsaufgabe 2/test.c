// Beispiel: Monatsname
// --------------------

char * monatName( int monatNr);     /* Prototyp */

void main( )
{
    for( ; ; )
    {
        printf( "\nZahl ? (<=0 Ende) :" );
        scanf ( "%d", &c);
        if (c <= 0) return;
        printf( "\n%d  %s\n", c, monatName( c) );
    }
}
