
   	/********************************************************/
   	/*                                          		*/
   	/*            Logical: Die neugierige Nachbarin   	*/
   	/*                                          		*/
   	/********************************************************/



  	% Hilfspraedikate
     	

          
    	position( X, [X|_], 1 ).
     	position( X, [_|T], N )   :-  	position( X, T, N1 ),
                               	    	N is N1 + 1.  


	permutiere( [], [] ).
    	permutiere( L, [H|T] ) 	  :-	append( L1,[H|R2], L ),
            				append( L1, R2, R ),
        	    			permutiere( R, T).




  	% Praedikat loesung( N, F, B, A, T)  



