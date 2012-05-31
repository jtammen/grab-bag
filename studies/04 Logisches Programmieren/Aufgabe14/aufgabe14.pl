% LOPR, Aufgabe 14
% Autor: Jan Tammen + Christoph Eck, SE
% Datum: 13.06.2005

:- dynamic knelt/1.
:- dynamic manuseln/1.
:- dynamic loepsen/1.
:- dynamic nopeln/1.

stamm( cedis )	:-	knelt(n),
			loepsen(j),
			nopeln(n),
			manuseln(j).

stamm( cedis )	:-	knelt(n),
			loepsen(n),
			nopeln(n),
			manuseln(j).

stamm( drudis )	:-	knelt(n),
			loepsen(j),
			nopeln(n),
			manuseln(n).

stamm( drudis )	:-	knelt(n),
			loepsen(n),
			nopeln(n),
			manuseln(j).

stamm( drudis )	:-	manuseln(n),
			nopeln(n),
			knelt(j).

stamm( cedis )	:-	knelt(j),
			manuseln(n),
			nopeln(j).

stamm( asis )	:-	knelt(j),
			manuseln(j).

stamm( belas )	:-	knelt(n),
			manuseln(n).



finde( S )	:-	write( 'knelt' ), nl, read( K ), assert( knelt(K) ),
			write( 'manuseln' ), nl, read( M ), assert( manuseln(M) ),
			write( 'loepsen' ), nl, read( L ), assert( loepsen(L) ),
			write( 'nopeln' ), nl, read( N ), assert( nopeln(N) ),
			stamm( S ),
			loeschealle.

loeschealle	:-	(retract( knelt(j) );
			 retract( knelt(n) )),
			(retract( manuseln(j) );
			 retract( manuseln(n) )),
			(retract( loepsen(j) );
			 retract( loepsen(n) )),
			(retract( nopeln(j) );
			 retract( nopeln(n) )).