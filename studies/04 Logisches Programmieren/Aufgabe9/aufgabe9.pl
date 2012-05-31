% Aufgabe 9, LOPR
% Autor: Jan Tammen, SE
% Datum: 09.05.2005

/* Fakten */
direktflug( lufthansa, stuttgart, frankfurt,   75 ).
direktflug( lufthansa, frankfurt, zuerich,    125 ).
direktflug( lufthansa, frankfurt, newYork,    900 ).
direktflug( swiss,     frankfurt, zuerich,    130 ).
direktflug( swiss,     zuerich,   newYork,    950 ).
direktflug( deltaAir,  frankfurt, newYork,    900 ).
direktflug( deltaAir,  newYork,   losAngeles, 475 ).

/* Aufgabe a): Liste der Flughaefen erstellen */
flug( Start, Ziel, [Start, Ziel] )      :- direktflug( _, Start, Ziel, _ ).
flug( Start, Ziel, [Start | T] )        :- direktflug( _, Start, ZwZiel, _ ),
                                           flug( ZwZiel, Ziel, T ).
                                   
/* Aufgabe b) */
flugB( Start, Ziel, [Fluggesellschaft(Start, Ziel)] )		:- direktflug( Fluggesellschaft, Start, Ziel, _ ).
flugB( Start, Ziel, [Fluggesellschaft(Start, ZwZiel) | T])	:- direktflug( Fluggesellschaft, Start, ZwZiel, _ ),
                                  				   flugB( ZwZiel, Ziel, T ).
