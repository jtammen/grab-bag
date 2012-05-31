% Aufgabe 8, LOPR
% Autor: Jan Tammen, SE
% Datum: 09.05.2005

/* Aufgabe a) */
% Erstes Element einer Liste L bestimmen. Es gibt nur ein Ergebnis => !
erstes( A, [A | _] )   :- !.

% Letztes Element einer Liste L bestimmen. Es gibt nur ein Ergenis => !
letztes( H, [H] )       :- !.
letztes( Z, [_ | T] )   :- letztes( Z, T ).

% Laenge einer Liste L bestimmen. Es gibt nur ein Ergebnis => !
laenge( 0, [])          :- !.
laenge( N, [H | T] )    :- laenge( Ntmp, T ),
                           N is Ntmp+1,
                           !.


/* Aufgabe b) */
mutter( anna, [bernd, christa, doris] ).
mutter( berta, [emil, frieda] ).
mutter( christa, [gerda] ).
mutter( doris, [hans, ilse, jochen] ).
mutter( else, [karin] ).
mutter( frieda, [ludwig] ).

/* Aufgabe c) */
% K ist das erste Kind von M
erstesKind( Mutter, ErstesKind ) :- mutter( Mutter, KinderListe ),
                                    erstes( ErstesKind, KinderListe ).

% K ist das letzte Kind von M
letztesKind( Mutter, LetztesKind ) :- mutter( Mutter, KinderListe ),
                                      letztes( LetztesKind, KinderListe ).

% Mutter besitzt N Kinder
anzahlKinder( Mutter, N ) :- mutter( Mutter, KinderListe ),
                             laenge( N, KinderListe ).
