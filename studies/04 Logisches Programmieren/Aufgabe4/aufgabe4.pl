/*********************************************************************/
/*                                                                   */
/* Aufgabe 4: Nominalphrasen                                         */
/*        							     */
/* Jan Tammen, SE						     */
/*********************************************************************/

/**
 * Fakten
 */
    artikel( `der`, m ).
    artikel( `die`, f ).
    artikel( `das`, n ).

    adjektiv( `schoene` ).
    adjektiv( `grosse` ).
    adjektiv( `weisse` ).

    substantiv( `haus`, n ).
    substantiv( `frau`, f ).
    substantiv( `hund`, m ).


/**
 * Regeln 
 */
    /* String in Teile aufspalten und 'rest'-Regel aufrufen */
    parser( String )	    :-	fronttoken( String, Front, Rest ),
                                artikel( Front, Gender ),
                                rest( Rest, Gender ).

    /* Stopp-Regel */
    rest( String, Gender )   :-  fronttoken( String, Next, `` ),
                                 substantiv( Next, Gender ).

    /* Rekursive Regel, es gibt noch einen 'Rest' */
    rest( String, Gender )   :-  fronttoken( String, Next, Rest ),
                                 adjektiv( Next ),
                                 rest( Rest, Gender ).
                    
