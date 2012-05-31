% Autor: Jan Tammen, SE
% Datum: 28.05.2005

/* Aufgabe a): _Erstes_ mit X uebereinstimmendes Objekt aus L entfernen */
% Leere Liste: nichts entfernen
deleteFirst( _, [], [] ).

% Element stimmt mit X ueberein: nicht in M kopieren und beenden
deleteFirst( X, [X | T], T ).

% Element stimmt nicht ueberein: kopieren
deleteFirst( X, [Foo | T1], [Foo | T2] )  :- X \= Foo,
                                             deleteFirst( X, T1, T2 ).


/* Aufgabe b): _Alle_ mit X uebereinstimmenden Objekte aus L entfernen */
% Leere Liste: nichts entfernen
deleteAll( _, [], [] ).

% Element stimmt mit X ueberein: nicht in M kopieren
deleteAll( X, [X | T1], M )  :- deleteAll( X, T1, M ).

% Element stimmt nicht ueberein: kopieren
deleteAll( X, [Foo | T1], [Foo | T2] )  :- X \= Foo,
                                           deleteAll( X, T1, T2 ).
