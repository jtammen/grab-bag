% Aufgabe 12, LOPR
% Autor: Jan Tammen + Christoph Eck, SE
% Datum: 27.04.2005

/********************************************************/
/*                                                      */
/*            Logical: Die neugierige Nachbarin         */
/*                                                      */
/********************************************************/

% Hilfspraedikate       
position( X, [X|_], 1 ).
position( X, [_|T], N )   :-    position( X, T, N1 ),
                                        N is N1 + 1.  

permutiere( [], [] ).
permutiere( L, [H|T] )    :-    append( L1,[H|R2], L ),
                                append( L1, R2, R ),
                                permutiere( R, T).

wohntNeben( X, Y )              :-     X-Y =:= 1.
wohntNeben( X, Y )              :-     Y-X =:= 1.

% Praedikat loesung
loesung( N, F, B, A, T )        :-      permutiere( [norwegen, spanien, england, ukraine, japan], N ),
                                        position( norwegen, N, 1 ),              % 9: Norweger wohnen ganz links
                                        position( england,  N, PosEngland ),
                                        position( spanien,  N, PosSpanien ),
                                        position( ukraine,  N, PosUkraine ),
                                        position( japan,    N, PosJapan ),

                                        permutiere( [gelb, gruen, rot, weiss, blau], F ),
                                        position( blau,  F, 2 ),                 % 14: Neben den Norwegern steht das blaue Haus
                                        position( gelb,  F, PosGelb ),
                                        position( rot,   F, PosEngland ),        % 1:  Die Englaender haben das rote Haus
                                        position( weiss, F, PosWeiss ),
                                        position( gruen, F, PosGruen ),
                                        PosGruen =:= PosWeiss+1,                 % 5:  Das gruene Haus steht rechts vom weissen
                                                                                 % Praedikat =:= evaluiert die linke Seite und
                                                                                 % vergleicht dann mit der rechten Seite

                                        permutiere( [friseur, programmierer, maler, lehrer, kaufmann], B ),
                                        position( friseur,       B, PosFriseur ),
                                        position( programmierer, B, PosGelb ),   % 7: Der Programmierer hat das gelbe Haus
                                        position( maler,         B, PosMaler ),
                                        position( lehrer,        B, PosLehrer ),
                                        position( kaufmann,      B, PosJapan ),  % 13: Der Kaufmann ist japanisch
                                        
                                        permutiere( [fiat, mercedes, opel, audi, porsche], A ),
                                        position( opel,     A, 3 ),              % 8:  Der Opel ist in der Mitte
                                        position( fiat,     A, PosGruen ),       % 3:  Die Familie mit dem Fiat wohnt im gruenen Haus
                                        position( mercedes, A, PosUkraine ),     % 4:  Die ukrainische Familie hat den Mercedes
                                        position( audi,     A, PosMaler ),       % 12: Der Maler faehrt den Audi
                                        %position( porsche,  A, PosPorsche )     % unbekannt!
                                        
                                        permutiere( [hund, schlange, hamster, katze, papagei], T ),
                                        position( hund,     T, PosSpanien ),     % 2:  Der Hund gehoert den Spaniern
                                        position( schlange, T, PosFriseur ),     % 6:  Die Friseur-Familie hat die Schlange
                                        position( hamster,  T, PosHamster ),
                                        wohntNeben( PosHamster, PosLehrer ),     % 10: Hamster wohnt _neben_ (links oder rechts) Lehrer
                                        position( katze,    T, PosKatze),
                                        wohntNeben( PosKatze, PosGelb ).         % 11: Katze wohnt _neben_ (links oder rechts) Programmierer == PosGelb
                                        %position( papagei,  T, PosPapagei )     % unbekannt!

                                        
% Der Dialog
start :-    loesung(N, F, B, A, T),     % Loesung suchen
            repeat,
            write( 'Bitte eine Frage (Stichwort eingeben): '), nl, read( Frage ),

            % Position des Stichwortes ermitteln
            (
              (position(Frage, F, Pos);
               position(Frage, B, Pos);
               position(Frage, A, Pos);
               position(Frage, T, Pos);
               position(Frage, N, Pos)
              ),
              
              % Nationalitaet ermitteln
              position(Nat, N, Pos)
            ),
              
            write('Die gesuchte Nationalität ist: '),
            write( Nat ), nl,
            write('Weiter? (j/n)' ), nl,
            read( W ),
            W = n.           % Beenden bei n
