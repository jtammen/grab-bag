/*********************************************************************/
/*                                                                   */
/* Die Ehevermittlung                                                */
/*                     						     */
/* Jan Tammen, SE	                                             */
/*********************************************************************/

/**
 * Fakten 
 */

	frauen( luise, gross, braun, reif	).
	frauen( maria, gross, blond, jung       ).
	frauen( margrit, klein, grau, reif      ).
	frauen( helga, mittel, blond, jung      ).

	maenner( heinz, klein, braun, jung      ).
	maenner( peter, gross, braun, alt       ).
	maenner( urs, gross, blond, jung        ).
	maenner( chris, mittel, braun, jung     ).

	
	interessen( luise, klassik, krimi, jogging              ).
	interessen( maria, pop, krimi, jogging                  ).
	interessen( margrit, jazz, scienceFiction, schwimmen    ).
	interessen( helga, pop, liebesroman, jogging            ).

	interessen( heinz, klassik, liebesroman, schwimmen      ).
	interessen( peter, jazz, krimi, tennis                  ).
	interessen( urs, jazz, scienceFiction, schwimmen        ).
	interessen( chris, pop, krimi, jogging                  ).


	wunschpartner( luise, gross, schwarz, reif  ).
	wunschpartner( maria, mittel, braun, jung   ).
	wunschpartner( margrit, gross, blond, jung  ).
	wunschpartner( helga, klein, blond, jung    ).

	wunschpartner( heinz, mittel, schwarz, reif ).
	wunschpartner( peter, klein, blond, jung    ).
	wunschpartner( urs, klein, grau, reif       ).
	wunschpartner( chris, gross, blond, jung    ).

/**
 * Regeln 
 */

    /* passen die Interessen zusammen? X=Frau, Y=Mann */
    gleicheInteressen( X, Y )   :- interessen( X, M, L, S ),
                                   interessen( Y, M, L, S ).

    /* passen die Merkmale? X=Frau, Y=Mann */
    passendeMerkmale( X, Y )    :- wunschpartner( Y, G, H, A ),
                                   frauen( X, G, H, A ).

    /* alle passenden Paare suchen? X=Frau, Y=Mann */                               
    passenZusammen( X, Y )      :- frauen( X, _, _, _ ),
                                   maenner( Y, _, _, _ ),
                                   gleicheInteressen( X, Y ),
                                   passendeMerkmale( X, Y ).


    /* Alle reifen Frauen */
    reifeFrau( X )		:- frauen( X, _, _, reif).

    /* Alle schwimmenden Maenner */
    schwimmenderMann( Y )	:- maenner( Y, _, _, _ ),
				   interessen( Y, _, _, schwimmen).
 
    /* Alle Frauen, die Liebesromane lesen und junge, blonde Maenner bevorzugen */
    besondereFrau( X )		:- frauen( X, _, _, _ ),
				   interessen( X, _, liebesroman, _ ),
				   wunschpartner( X, _, blond, jung ).