/*********************************************************************/
/*                                                                   */
/* Aufgabe 5: Fibonacci-Zahlen                                       */
/*                                                                   */
/* Jan Tammen, SE						     */
/*    							             */
/* Das n-te Element der Fibonacci-Folge entsteht durch Addition der  */
/* beiden vorangehenden Zahlen (n > 2)				     */
/* funktioniert bis N = 16	                                     */
/*********************************************************************/

/**
 * Fakten
 */
	fib1( 1, 1 ).
	fib1( 2, 1 ).


/**
 * Regeln 
 */
	fib( N, Res )   :- 	fib1( N, Res ).
	fib( N, Res )	:-	N > 2,
				N1 is (N-1),
				N2 is (N-2),
				fib( N1, R1 ),
				fib( N2, R2 ),
				Res is R1+R2.
