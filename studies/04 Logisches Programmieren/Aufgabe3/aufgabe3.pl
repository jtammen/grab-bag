/*********************************************************************/
/*                                                                   */
/* Aufgabe 3                                                         */
/*          							     */
/* Jan Tammen, SE                            			     */
/* enthaelt( X, Y ) -> "X enthaelt Y"                                */
/*********************************************************************/

/**
 * Fakten
 */
	enthaelt( wohnzimmer, wohnzimmerschrank ).
	enthaelt( wohnzimmer, hifiRack ).
	enthaelt( wohnzimmer, buecherregal ).
	enthaelt( wohnzimmer, blumenvase ).

	enthaelt( blumenvase, nelken ).

	enthaelt( hifiRack, receiver ).
	enthaelt( hifiRack, cdPlayer ).

	enthaelt( wohnzimmerschrank, cdSammlung ).
	enthaelt( wohnzimmerschrank, schublade ).
	enthaelt( wohnzimmerschrank, geschirr ).
	enthaelt( wohnzimmerschrank, kaffeekanne ).
	enthaelt( wohnzimmerschrank, zuckerdose ).

	enthaelt( zuckerdose, zucker ).
	
	enthaelt( schublade, besteckkasten ).

	enthaelt( besteckkasten, messer ).
	enthaelt( besteckkasten, gabel ).

	enthaelt( buecherregal, beethovensNeunte ).
	enthaelt( buecherregal, fotoalbum ).
	enthaelt( buecherregal, romansammlung ).
	enthaelt( buecherregal, woerterbuecher ).

	enthaelt( romansammlung, dieBlechtrommel ).
	enthaelt( romansammlung, doktorSchiwago ).
	enthaelt( dieBlechtrommel, lesezeichen ).
	
	enthaelt( fotoalbum, hochzeitsfotos ).
	
	enthaelt( woerterbuecher, duden ).
	enthaelt( woerterbuecher, englishDictionary ).
	enthaelt( englishDictionary, englischeWoerter ).
	

/**
 * Regeln 
 */
	/* Stopp-Regel */
	in( Wo, Was )	:-	enthaelt( Wo, Was ).

	/* rechts-rek. Regel */
	in( Wo, Was )	:-	enthaelt( Wo, Container ),
				in( Container, Was ).