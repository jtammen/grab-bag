	
	/********************************************************/
	/*							*/	
	/*               Definite-Clause Grammar		*/
	/*							*/
	/*       mit Einbeziehung von Personalpronomina		*/
        /*            und Numerus-Person-Kongruenz              */
	/*                                                      */
	/*                                                      */  
	/********************************************************/




	/*** Praedikat zum vereinfachten Aufruf ***/

 	s( S ) :- sentence( S, [] ). 




	/*** Syntaktische Regeln ***/


  	sentence  		-->	nounPhrase( nominative ),								
					verbPhrase.

  	nounPhrase( Case )	--> 	determiner( Gender, Case ),
					noun( Gender, Case ).

  	verbPhrase		--> 	verbTransitive,
					nounPhrase( accusative ).
  	verbPhrase		--> 	verbIntransitive.




	/*** Lexikalische Regeln ***/


  	determiner( masculine, singular, nominative )	-->	[ der ].
	determiner( masculine, singular, accusative )	-->	[ den ].  
  	determiner( masculine, plural,   nominative )	-->	[ die ].
  	determiner( masculine, plural,   accusative )	-->	[ die ].
  	determiner( feminine,  singular, nominative )	-->	[ die ].
  	determiner( feminine,  singular, accusative )	-->	[ die ].  
  	determiner( feminine,  plural,   nominative )	-->	[ die ].
  	determiner( feminine,  plural,   accusative )	-->	[ die ].
  	determiner( neuter,    singular, nominative )	-->	[ das ].
  	determiner( neuter,    singular, accusative )	-->	[ das ].  
  	determiner( neuter,    plural,   nominative )	-->	[ die ].
  	determiner( neuter,    plural,   accusative )	-->	[ die ].

  	noun( masculine, singular, nominative )		-->	[ hund ]. 
  	noun( masculine, singular, accusative ) 	-->	[ hund ]. 
  	noun( masculine, plural,   nominative ) 	-->	[ hunde ]. 
  	noun( masculine, plural,   accusative ) 	-->	[ hunde ]. 
  	noun( feminine,  singular, nominative ) 	-->	[ wurst ]. 
  	noun( feminine,  singular, accusative ) 	-->	[ wurst ]. 
  	noun( feminine,  plural,   nominative ) 	-->	[ wuerste ]. 
  	noun( feminine,  plural,   accusative ) 	-->	[ wuerste ]. 
  	noun( neuter,    singular, nominative ) 	-->	[ kind ]. 
  	noun( neuter,    singular, accusative ) 	-->	[ kind ]. 
  	noun( neuter,    plural,   nominative ) 	-->	[ kinder ]. 
  	noun( neuter,    plural,   accusative ) 	-->	[ kinder ]. 
  	noun( masculine, singular, nominative ) 	-->	[ elefant ]. 
  	noun( masculine, singular, accusative ) 	-->	[ elefanten ]. 
  	noun( masculine, plural,   nominative ) 	-->	[ elefanten ]. 
  	noun( masculine, plural,   accusative ) 	-->	[ elefanten ]. 

  	personalPronoun( singular, 1, nominative ) 	-->	[ ich ]. 
  	personalPronoun( singular, 2, nominative ) 	-->	[ du ]. 
  	personalPronoun( singular, 3, nominative ) 	-->	[ er ]. 
  	personalPronoun( singular, 3, nominative ) 	-->	[ sie ]. 
  	personalPronoun( singular, 3, nominative ) 	-->	[ es ]. 
  	personalPronoun( plural,   1, nominative ) 	-->	[ wir ]. 
  	personalPronoun( plural,   2, nominative ) 	-->	[ ihr ]. 
  	personalPronoun( plural,   3, nominative ) 	-->	[ sie ]. 
  	personalPronoun( singular, 1, accusative ) 	-->	[ mich ]. 
  	personalPronoun( singular, 2, accusative ) 	-->	[ dich ]. 
  	personalPronoun( singular, 3, accusative ) 	-->	[ ihn ]. 
  	personalPronoun( singular, 3, accusative ) 	-->	[ sie ]. 
  	personalPronoun( singular, 3, accusative ) 	-->	[ es ]. 
  	personalPronoun( plural,   1, accusative ) 	-->	[ uns ]. 
  	personalPronoun( plural,   2, accusative ) 	-->	[ euch ]. 
  	personalPronoun( plural,   3, accusative ) 	-->	[ sie ]. 

  	verbTransitive( singular, 1 )  			-->	[ fresse ]. 
  	verbTransitive( singular, 2 )  			-->	[ frisst ]. 
  	verbTransitive( singular, 3 )  			-->	[ frisst ]. 
  	verbTransitive( plural,   1 )  			-->	[ fressen ]. 
  	verbTransitive( plural,   2 )  			-->	[ fresst ]. 
  	verbTransitive( plural,   3 )  			-->	[ fressen ]. 
  	verbTransitive( singular, 1 )  			-->	[ liebe ]. 
  	verbTransitive( singular, 2 )  			-->	[ liebst ]. 
  	verbTransitive( singular, 3 )  			-->	[ liebt ]. 
  	verbTransitive( plural,   1 )  			-->	[ lieben ]. 
  	verbTransitive( plural,   2 )  			-->	[ liebt ]. 
  	verbTransitive( plural,   3 )  			-->	[ lieben ]. 

  	verbIntransitive( singular, 1 )  		-->	[ belle ]. 
  	verbIntransitive( singular, 2 )  		-->	[ bellst ]. 
  	verbIntransitive( singular, 3 )  		-->	[ bellt ]. 
  	verbIntransitive( plural,   1 )  		-->	[ bellen ]. 
  	verbIntransitive( plural,   2 )  		-->	[ bellt ]. 
  	verbIntransitive( plural,   3 )  		-->	[ bellen ]. 
  	verbIntransitive( singular, 1 )  		-->	[ lache ]. 
  	verbIntransitive( singular, 2 )  		-->	[ lachst ]. 
  	verbIntransitive( singular, 3 )  		-->	[ lacht ]. 
  	verbIntransitive( plural,   1 )  		-->	[ lachen ]. 
  	verbIntransitive( plural,   2 )  		-->	[ lacht ]. 
  	verbIntransitive( plural,   3 )  		-->	[ lachen ]. 


 