% Autor: Jan Tammen, SE
% Datum: 21.05.2005

/* Aufgabe a): Ersetze A durch B in der Liste L, Ergebnis nach M */
% Leere Liste: nichts ersetzen
ersetze( _, _, [], [] ) :- !.

% Wenn erstes Element (Foo) nicht A entspricht...
ersetze( A, B, [Foo | T1], [Foo | T2] )  :- Foo \= A,
                                            ersetze( A, B, T1, T2 ).
                                            
% A steht am Anfang und wird durch B ersetzt; rekursiver Aufruf fuer Restliste
ersetze( A, B, [A | T1], [B | T2] )  :- ersetze( A, B, T1, T2 ),
					!.


/* Aufgabe b): Bestimme Haeufigkeit N des Objekts X in der Liste L */
% Leere Liste: N = 0
haeufigkeit( _, [], 0 ) :- !.

% Erstes Element ist das gesuchte: Zaehler erhoehen
haeufigkeit( X, [X | T], N )  :-  !, haeufigkeit( X, T, NTemp ),
                                  N is NTemp+1.

% Erstes Element ist nicht das gesuchte: Liste reduzieren
haeufigkeit( X, [Foo | T], N )  :-  Foo \= X,
                                    haeufigkeit( X, T, N ).

