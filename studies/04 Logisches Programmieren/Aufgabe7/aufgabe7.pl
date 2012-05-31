% Aufgabe 7, LOPR
% Autor: Jan Tammen, SE
% Datum: 27.04.2005

  
werkliste( tschaikowsky, 
           [sinfonie(pathetique),ballett(dornroeschen)]    ).

werkliste( chopin,       
           [klavierkonzert(e_moll),klavierkonzert(f_moll)] ).

werkliste( beethoven,    
           [oper(fidelio),sinfonie(eroica),
            sinfonie(pastorale),violinkonzert(d_dur)]      ).

werkliste( mozart,       
           [oper(zauberfloete),oper(figaro),
            sinfonie(haffner),violinkonzert(a_dur)]        ).

werkliste( bach,         
           [violinkonzert(a_moll),klavierkonzert(d_moll)]  ).

werkliste( grieg,        
           [klavierkonzert(a_moll)]                        ).


/* Aufgabe a): z.B. komponist( oper(figaro), K ). */
komponist( Gattung( Titel ), Komponist ) :-  werkliste( Komponist, Liste ),
                                             element( Gattung( Titel ), Liste ), !.

% X ist Element der Liste
element( X, [X | _] ).
element( X, [_ | T] ) :- element( X, T ).

/* Aufgabe b): z.B. werkbezeichnung( klavierkonzert, W ). */
werkbezeichnung( Gattung, Titel( Komponist ) ) :- werkliste( Komponist, Liste ),
                                                  element( Gattung( Titel ), Liste ).
