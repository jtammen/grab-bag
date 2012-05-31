/*********************************************************************/
/*                                                                   */
/* Der Stammbaum                                                     */
/*            							     */
/* Jan Tammen, SE                                                    */
/*********************************************************************/

/**
 * Fakten
 */

	weiblich( anna ).
	weiblich( berta ).
	weiblich( christa ).
	weiblich( doris ).
	weiblich( else ).
	weiblich( frieda ).
	weiblich( gerda ).
	weiblich( ilse ).
	weiblich( karin ).

	maennlich( anton ).
	maennlich( bernd ).
	maennlich( claus ).
	maennlich( dieter ).
	maennlich( emil ).
	maennlich( franz ).
	maennlich( hans ).
	maennlich( jochen ).
	maennlich( ludwig ).

	ehefrau( anna, anton ).
	ehefrau( berta, bernd ).
	ehefrau( christa, claus ).
	ehefrau( doris, dieter ).
	ehefrau( else, emil ).
	ehefrau( frieda, franz ).


	mutter( anna, bernd ).
	mutter( anna, christa ).
	mutter( anna, doris ).

	mutter( berta, emil ).
	mutter( berta, frieda ).

	mutter( christa, gerda ).

	mutter( doris, hans ).
	mutter( doris, ilse ).
	mutter( doris, jochen ).

	mutter( else, karin ).

	mutter( frieda, ludwig ).

/**
 * Regeln 
 */

	vater( Vater, Kind )			:-	maennlich( Vater ),
							ehefrau( Frau, Vater ),
							mutter( Frau, Kind ).
	kind( Kind, MutterVater )		:-	mutter( MutterVater, Kind ).
	kind( Kind, MutterVater )		:-	vater( MutterVater, Kind ).

	grossmutter( Oma, Enkel )		:-	kind( Enkel, MutterVater ),
							mutter( Oma, MutterVater ).
	grossvater( Opa, Enkel )		:-	kind( Enkel, MutterVater ),
							vater( Opa, MutterVater ).
			


	partner( X, Partner )			:-	ehefrau( X, Partner );
							ehefrau( Partner, X ).
	schwMutter( SchwMama, SchwKind )	:-	partner( Partner, SchwKind ),
							mutter( SchwMama, Partner ).
	schwVater( SchwVater, SchwKind )	:-	partner( Partner, SchwKind ),
							vater( SchwVater, Partner ).

	tochter( Tochter, eltern(M, V) )	:-	weiblich( Tochter ),
                                                        mutter( M, Tochter ),
							vater( V, Tochter ).

	sohn( Sohn, eltern(M, V) )		:-	maennlich( Sohn ),
                                                        mutter( M, Sohn ),
							vater( V, Sohn ).

	schwester( Schwester, Geschwister )	:-	tochter( Schwester, Mutter ),
							mutter( Mutter, Geschwister ),
							\=( Schwester, Geschwister ).
	bruder( Bruder, Geschwister )		:-	sohn( Bruder, Mutter ),
							mutter( Mutter, Geschwister ),
							\=( Bruder, Geschwister ).

	tante( Tante, NichteNeffe )		:-	schwester( Tante, Mutter ),
							mutter( Mutter, NichteNeffe ).
	onkel( Onkel, NichteNeffe )		:-	bruder( Onkel, Vater ),
							vater( Vater, NichteNeffe ).

	/* Stopp-Regel */
	vorfahre( Ahne, Nachkomme )		:-	kind( Nachkomme, Ahne ).

	/* rechts-rek. Regel */
	vorfahre( Ahne, Nachkomme )		:-	kind( Nachkomme, Eltern ),
							vorfahre( Ahne, Eltern ).