/********************************************************/
/*                                                      */
/*                    Im Flughafen                      */
/*  Jan Tammen, SE                                      */
/********************************************************/


/*              Interpretation des 1.Fakts:             */   
/*        "Die Lufthansa bietet einen Direktflug        */
/*         von Stuttgart nach Frankfurt an;             */
/*         der Flugpreis betraegt 75 Euro."             */

/* Fuer Teil e) */
  direktflug( lufthansa, zuerich,   frankfurt,  125 ).

  direktflug( lufthansa, stuttgart, frankfurt,   75 ).
  direktflug( lufthansa, frankfurt, zuerich,    125 ).
  direktflug( lufthansa, frankfurt, newYork,    900 ).
  direktflug( swiss,     frankfurt, zuerich,    130 ).
  direktflug( swiss,     zuerich,   newYork,    950 ).
  direktflug( deltaAir,  frankfurt, newYork,    900 ).
  direktflug( deltaAir,  newYork,   losAngeles, 475 ).


/**
 * Regeln
 */
     /* Teil a) */
     goalA1( Ziel )                :-      direktflug( lufthansa, frankfurt, Ziel, P ),
                                           P =< 250.
     goalA2( Start )               :-      direktflug( _, Start, newYork, _),
                                           Start \= frankfurt.

     /* Teil b) */
     flug( Start, Ziel )           :-      direktflug( _, Start, Ziel, _ ).
     flug( Start, Ziel )           :-      direktflug( _, Start, ZwZiel, _ ),
                                           flug( ZwZiel, Ziel ).

     /* Teil c) */
     flug( Start, Ziel, Anzahl )   :-      direktflug( _, Start, Ziel, _ ),
                                           Anzahl is 1.
     flug( Start, Ziel, Anzahl )   :-      direktflug( _, Start, ZwZiel, _ ),
                                           flug( ZwZiel, Ziel, AnzahlTmp ),
                                           Anzahl is AnzahlTmp+1.
                                           
     /* Teil d) */
     flug( Start, Ziel, Anzahl, Preis )   :-   direktflug( _, Start, Ziel, Preis ),
                                               Anzahl is 1.
     flug( Start, Ziel, Anzahl, Preis )   :-   direktflug( _, Start, ZwZiel, PreisAkt ),
                                               flug( ZwZiel, Ziel, AnzahlTmp, PreisTmp ),
                                               Anzahl is AnzahlTmp+1,
                                               Preis is PreisAkt+PreisTmp.
                                               
     /* Teil e) */
     /* Auswirkung: Endlos-Schleife!
        Die neue Regel und Regel Nr. 2 sind "komplementaer" zueinander
        und deswegen wird dort immer wieder ein "ZwischenZiel" gefunden.
     */
