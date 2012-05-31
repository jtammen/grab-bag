            
   	/*********************************************************/
   	/*							 */
     	/* 		fronttoken (Source-Code)		 */
     	/*							 */
     	/*********************************************************/

				

	fronttoken( S, E, R ) 	  :- 	string_chars( S, L ),
					front( L, E1, R1),
					string_chars( E, E1 ),
					string_chars( R, R1 ).

	front( [], [], [] )	  :-	!.	
	front( [32|T], [], T )	  :-	!.	
	front( [H|T], [H|T1], R ) :-	H \= ` `, 
					front( T, T1, R ).   		
	
					


