% LOPR, Aufgabe 13
% Autor: Jan Tammen + Christoph Eck, SE
% Datum: 13.06.2005

:- dynamic fib/2.
fib( 2, 1 ) :-  !.
fib( 1, 1 ) :-  !.

fib( N, F ) :-  N1 is N-1,
                N2 is N-2,
                fib( N1, F1 ),
                fib( N2, F2 ),
                F is F1 + F2,
                asserta( fib(N, F) ).  % Wert in Datenbasis einfuegen
